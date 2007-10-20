--
-- SQL script to migrate the data from a v28 schema to the next revision.
--
-- NO WARRANTIES OF WHATEVER KIND, NEITHER EXPRESS NOR IMPLIED. This script
-- migrates your schema. It may irreparably damage your data. Back up your
-- database as you would otherwise, and run the script in its own transaction
-- so you can roll it back.
--
-- This migration accomplishes the following:
-- * fold politlocation into geolocation
-- * creates tables crossexperiment_individual and individual_relationship
-- * populates both using male_id and female_id from crossexperiment
-- * drops male_id and female_id from crossexperiment
-- * makes crossexperiment.type_id non-nullable
-- * add comments to some tables and columns

-- 1) Migrate politlocation
ALTER TABLE geolocation ADD COLUMN postalcode character varying(32);
ALTER TABLE geolocation ALTER COLUMN county TYPE character varying(64);
ALTER TABLE geolocation ALTER COLUMN province TYPE character varying(64);
ALTER TABLE geolocation ALTER COLUMN country TYPE character varying(64);

UPDATE geolocation SET
       postalcode = p.postalcode 
FROM politlocation p 
WHERE p.politlocation_id = geolocation.politlocation_id;

UPDATE geolocation SET
       county = t.name
FROM politlocation p, cvterm t 
WHERE p.politlocation_id = geolocation.politlocation_id
AND   p.county_id = t.cvterm_id;

UPDATE geolocation SET
       province = t.name
FROM politlocation p, cvterm t 
WHERE p.politlocation_id = geolocation.politlocation_id
AND   p.province_id = t.cvterm_id;

UPDATE geolocation SET
       country = t.name
FROM politlocation p, cvterm t 
WHERE p.politlocation_id = geolocation.politlocation_id
AND   p.country_id = t.cvterm_id;

ALTER TABLE geolocation DROP COLUMN politlocation_id;
DROP TABLE politlocation;

COMMENT ON TABLE geolocation IS 'The geo-referencable location of the stock. NOTE: This entity is subject to change as a more general and possibly more OpenGIS-compliant geolocation module may be introduced into Chado.';

COMMENT ON COLUMN geolocation.postalcode IS 'The postal code, or zipcode in the US, within which the georeference falls.';

COMMENT ON COLUMN geolocation.county IS 'The county (or equivalent local government unit) whithin which the georeference falls. This should probably rather be a foreign key to a cvterm, but there is an unresolved problem about the univocality constraint with location name ontologies, such as the Gazetteer.';

COMMENT ON COLUMN geolocation.province IS 'The province, or state, within which the georeference falls. This should probably rather be a foreign key to a cvterm, but there is an unresolved problem about the univocality constraint with location name ontologies, such as the Gazetteer.';

COMMENT ON COLUMN geolocation.country IS 'The country within which the georeference falls. This should probably rather be a foreign key to a cvterm, but there is an unresolved problem about the univocality constraint with location name ontologies, such as the Gazetteer.';

-- 2) table crossexperiment_individual
--

CREATE TABLE crossexperiment_individual (
        crossexperiment_individual_id serial NOT NULL,
        PRIMARY KEY (crossexperiment_individual_id),
	crossexperiment_id integer NOT NULL,
        FOREIGN KEY (crossexperiment_id) REFERENCES crossexperiment (crossexperiment_id) 
                ON DELETE CASCADE,
	individual_id integer NOT NULL,
        FOREIGN KEY (individual_id) REFERENCES individual (individual_id) 
                ON DELETE CASCADE,
        type_id integer NOT NULL,
        FOREIGN KEY (type_id) REFERENCES cvterm (cvterm_id)
                ON DELETE RESTRICT,
        CONSTRAINT crossexperiment_individual_c1 UNIQUE (crossexperiment_id, individual_id)
);

COMMENT ON TABLE crossexperiment_individual IS 'The parental individual(s) used in a crossexperiment. Some cross experiments are carried out by pairing multiple males to one or multiple female(s) so that the actual parent individuals of offspring may not necessarily be known a-priori. ';

COMMENT ON COLUMN crossexperiment_individual.crossexperiment_id IS 'The cross experiment in which the individual is used as a (possible) parent.';

COMMENT ON COLUMN crossexperiment_individual.individual_id IS 'The parental individual being used in the cross experiment. A specific individual can be associated with a crossexperiment only once. There may be multiple parental individuals of the same type in a cross experiment.';

COMMENT ON COLUMN crossexperiment_individual.type_id IS 'The type of the association of the individual, such as ''maternal parent'', or ''paternal parent''. Note that this is not necessarily redundant with the gender of the individual, for example consider plants.';

-- 3) table individual_relationship

CREATE TABLE individual_relationship (
        individual_relationship_id serial NOT NULL,
        PRIMARY KEY (individual_relationship_id),
        parent_id integer NOT NULL,
        FOREIGN KEY (parent_id) REFERENCES individual (individual_id)
                ON DELETE CASCADE,
        offspring_id integer NOT NULL,
        FOREIGN KEY (offspring_id) REFERENCES individual (individual_id)
                ON DELETE CASCADE,
        type_id integer NOT NULL,
        FOREIGN KEY (type_id) REFERENCES cvterm (cvterm_id)
                ON DELETE RESTRICT,
        crossexperiment_id integer,
        FOREIGN KEY (crossexperiment_id) REFERENCES crossexperiment (crossexperiment_id)
                ON DELETE SET NULL,
        CONSTRAINT individual_relationship_c1 UNIQUE (offspring_id, parent_id, type_id)
);

COMMENT ON TABLE individual_relationship IS 'The parental relationship between two individuals, either of paternal or maternal type. There may be multiple parental relationships of the same type for an individual among the progeny if the exact maternal or paternal individuals have not (yet) been determined.';

COMMENT ON COLUMN individual_relationship.parent_id IS 'The individual that is, possibly or unequivocally, a parent to the offspring individual.';

COMMENT ON COLUMN individual_relationship.offspring_id IS 'The offspring individual for which a parental individual is being associated.';

COMMENT ON COLUMN individual_relationship.type_id IS 'The type of parental relationship being stated, such as ''maternal parent'' and ''paternal parent''.';

COMMENT ON COLUMN individual_relationship.crossexperiment_id IS 'The cross experiment from which the offspring arose.';

-- 4) migrate the data regarding male and female to the two tables
-- crossexperiment_individual and individual_relationship

-- before we can populate this we need to create the cvterm entries
INSERT INTO cv (name, definition) 
VALUES ('individual relationship','relationship types between individuals');
INSERT INTO db (name) VALUES ('NESCent');

-- ID for 'maternal parent'
INSERT INTO dbxref (accession, db_id)
SELECT 'IRO:000000001', db.db_id
FROM db WHERE db.name = 'NESCent';
-- term for 'maternal parent'
INSERT INTO cvterm (name, dbxref_id, cv_id)
SELECT 'maternal parent', dbxref.dbxref_id, cv.cv_id
FROM dbxref JOIN db ON (dbxref.db_id = db.db_id), cv
WHERE db.name = 'NESCent' AND dbxref.accession = 'IRO:000000001'
AND cv.name = 'individual relationship';

-- ID for 'paternal parent'
INSERT INTO dbxref (accession, db_id)
SELECT 'IRO:000000002', db.db_id
FROM db WHERE db.name = 'NESCent';
-- term for 'paternal parent'
INSERT INTO cvterm (name, dbxref_id, cv_id)
SELECT 'paternal parent', dbxref.dbxref_id, cv.cv_id
FROM dbxref JOIN db ON (dbxref.db_id = db.db_id), cv
WHERE db.name = 'NESCent' AND dbxref.accession = 'IRO:000000002'
AND cv.name = 'individual relationship';

-- now populate crossexperiment_individual from male and female data
INSERT INTO crossexperiment_individual (crossexperiment_id, 
                                        individual_id,
                                        type_id)
SELECT c.crossexperiment_id, c.male_id, t.cvterm_id
FROM crossexperiment c, cvterm t JOIN cv ON (t.cv_id = cv.cv_id)
WHERE c.male_id IS NOT NULL
AND t.name = 'paternal parent' AND cv.name = 'individual relationship';
INSERT INTO crossexperiment_individual (crossexperiment_id, 
                                        individual_id,
                                        type_id)
SELECT c.crossexperiment_id, c.female_id, t.cvterm_id
FROM crossexperiment c, cvterm t JOIN cv ON (t.cv_id = cv.cv_id)
WHERE c.female_id IS NOT NULL
AND t.name = 'maternal parent' AND cv.name = 'individual relationship';

-- populate individual_relationship from crossexperiment male and female data
INSERT INTO individual_relationship (parent_id,
                                     offspring_id,
                                     type_id,
                                     crossexperiment_id)
SELECT c.male_id, i.individual_id, t.cvterm_id, c.crossexperiment_id
FROM individual i JOIN crossexperiment c 
                  ON (i.crossexperiment_id = c.crossexperiment_id), 
     cvterm t JOIN cv ON (t.cv_id = cv.cv_id)
WHERE c.male_id IS NOT NULL
AND t.name = 'paternal parent' AND cv.name = 'individual relationship';
INSERT INTO individual_relationship (parent_id,
                                     offspring_id,
                                     type_id,
                                     crossexperiment_id)
SELECT c.female_id, i.individual_id, t.cvterm_id, c.crossexperiment_id
FROM individual i JOIN crossexperiment c 
                  ON (i.crossexperiment_id = c.crossexperiment_id), 
     cvterm t JOIN cv ON (t.cv_id = cv.cv_id)
WHERE c.female_id IS NOT NULL
AND t.name = 'maternal parent' AND cv.name = 'individual relationship';

-- drop male and female references from crossexperiment
ALTER TABLE crossexperiment DROP COLUMN male_id;
ALTER TABLE crossexperiment DROP COLUMN female_id;

-- drop crossexperiment reference from individual
ALTER TABLE individual DROP COLUMN crossexperiment_id;

-- 5) Make crossexperiment.type_id not nullable
ALTER TABLE crossexperiment ALTER COLUMN type_id SET NOT NULL;

-- comments added to table crossexperiment
COMMENT ON COLUMN crossexperiment.experimenter_id IS 'The person who conducted the cross experiment.';
