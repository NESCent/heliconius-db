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
-- * creates table crossexperiment_male, populates content, and removes male_id
--   from crossexperiment.
-- * makes crossexperiment.type_id non-nullable
-- * add comments to some tables and columns

-- 1) Migrate politlocation
ALTER TABLE geolocation ADD COLUMN postalcode character varying(32);
ALTER TABLE geolocation ADD COLUMN county character varying(64);
ALTER TABLE geolocation ADD COLUMN province character varying(64);
ALTER TABLE geolocation ADD COLUMN country character varying(64);

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

-- 2) table crossexperiment_male
--

CREATE TABLE crossexperiment_male (
        crossexperiment_male_id serial NOT NULL,
        PRIMARY KEY (crossexperiment_male_id),
	crossexperiment_id integer NOT NULL,
        FOREIGN KEY (crossexperiment_id) REFERENCES crossexperiment (crossexperiment_id) 
                ON DELETE CASCADE,
	male_id integer NOT NULL,
        FOREIGN KEY (male_id) REFERENCES individual (individual_id) 
                ON DELETE CASCADE,
        CONSTRAINT crossexperiment_male_c1 UNIQUE (crossexperiment_id, male_id)
);

COMMENT ON TABLE crossexperiment_male IS 'The male individual(s) used in a crossexperiment. Certain cross experiments are carried out by pairing multiple males to a female, so that the actual father is not known a-priori until the genetic experiment is carried out that determines paternity. It is expected that once paternity has been determined other possible fathers for the cross be removed from this association. Also, though it is theoretically possible to conduct cross experiments in a way that the female could also one out of multiple individuals, there isn''t a use case for this right now and we therefore defer accommodating this until there is a requirement to do so.';

COMMENT ON COLUMN crossexperiment_male.crossexperiment_id IS 'The cross experiment in which the individual could be the father.';

COMMENT ON COLUMN crossexperiment_male.male_id IS 'The (presumably male) individual that was the sole or one of multiple possible fathers in the cross experiment. If this is the only individual associated with the cross, it is assumed that it is known, either by experimental set-up or by means of a paternity test, to be the father. A specific individual can be associated with a crossexperiment only once.';

-- populate from existing data
INSERT INTO crossexperiment_male (crossexperiment_id, male_id)
SELECT c.crossexperiment_id, c.male_id
FROM crossexperiment c
WHERE c.male_id IS NOT NULL;

-- drop column from crossexperiment
ALTER TABLE crossexperiment DROP COLUMN male_id;

-- 3) Make crossexperiment.type_id not nullable
ALTER TABLE crossexperiment ALTER COLUMN type_id SET NOT NULL;

-- comments added to table crossexperiment
COMMENT ON COLUMN crossexperiment.experimenter_id IS 'The person who conducted the cross experiment.';

COMMENT ON COLUMN crossexperiment.female_id IS 'The female individual used in the cross experiment.';
