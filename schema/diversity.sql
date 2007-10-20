--
-- The natural diversity extension module for Chado.
--
-- This schema is an extension (a 'module') to the Chado schema, the
-- common data model of the GMOD project. See
-- http://www.gmod.org/chado for further information. You will likely
-- need other modules of the chado schema installed first (see
-- dependencies). You can construct a DDL scripts containing all
-- dependencies and this schema in the right order using the script
-- chado/modules/bin/makedep.pl in the gmod-schema source tree (for
-- example, on sourceforge).
--
-- The initial version (evo.psql) was created by Owen McMillan and
-- Nassib Nassar. The revisions since the renaming to diversity module
-- in January 2007 are by Hilmar Lapp (hlapp at gmx.net).
--

--
-- Copyright (c) 2006-2007, Nassib Nassar, nassar at etymon.com
-- Copyright (c) 2006-2007, Owen McMillan, womcmill at ncsu.edu
-- Copyright (c) 2006-2007, National Evolutionary Synthesis Center
-- 
-- Permission is hereby granted, free of charge, to any person obtaining
-- a copy of this software and associated documentation files (the
-- "Software"), to deal in the Software without restriction, including
-- without limitation the rights to use, copy, modify, merge, publish,
-- distribute, sublicense, and/or sell copies of the Software, and to
-- permit persons to whom the Software is furnished to do so, subject to
-- the following conditions:
-- 
-- The above copyright notice and this permission notice shall be
-- included in all copies or substantial portions of the Software.
-- 
-- THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
-- EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
-- MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
-- NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
-- LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
-- OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
-- WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
--

-- =================================================================
-- Dependencies:
--
-- :import feature from sequence
-- :import cvterm from cv
-- :import pub from pub
-- :import phenotype from phenotype
-- :import organism from organism
-- :import genotype from genetic
-- :import contact from contact
-- :import project from general
-- =================================================================

-- create table biotype

CREATE TABLE biotype (
	biotype_id SERIAL NOT NULL,
        PRIMARY KEY (biotype_id),
        name VARCHAR(64) NOT NULL,
        CONSTRAINT biotype_c1 UNIQUE (name)
);

COMMENT ON TABLE biotype IS 'Biotype is essentially a named container of species. Typically, this will be the species that hybridized to give rise to a hybrid individual, and the name will usually be the species part of the binomial names, concatenated by "x". The biotype may also be used to designate race, beyond just a species; there may not be a taxonomic node for this, for example for races defined by host preference.';

COMMENT ON COLUMN biotype.name IS 'The unique name of the biotype. Typically the name of a species hybrid, with for example the species parts of the binomial names concatenated by "x".';

-- table biotype_organism
--
-- Allows grouping of several or more organisms to allow for the
-- presence of hybrid individuals

CREATE TABLE biotype_organism (
	biotype_organism_id SERIAL NOT NULL,
        PRIMARY KEY (biotype_organism_id),
	biotype_id INTEGER NOT NULL,
        FOREIGN KEY (biotype_id) REFERENCES biotype (biotype_id) 
                ON DELETE CASCADE,
	organism_id INTEGER NOT NULL,
        FOREIGN KEY (organism_id) REFERENCES organism (organism_id) 
                ON DELETE CASCADE,
	CONSTRAINT biotype_organism_c1 UNIQUE (biotype_id, organism_id)
);

COMMENT ON TABLE biotype_organism IS 'Allows grouping of two or more organisms to allow for the presence of hybrid individuals';

-- table geolocation:
--
-- The geo-referencable location of the stock, experiment, or cross.

CREATE TABLE geolocation (
	geolocation_id serial NOT NULL,
        PRIMARY KEY (geolocation_id),
        description character varying(255),
	coordinate_xml character varying(1024),
	latitude real,
	longitude real,
        geodetic_datum character varying(32),
	altitude real,
	altitude_dev real,
        postalcode character varying(32),
	county character varying(64),
	province character varying(64),
	country character varying(64)
);

COMMENT ON TABLE geolocation IS 'The geo-referencable location of the stock. NOTE: This entity is subject to change as a more general and possibly more OpenGIS-compliant geolocation module may be introduced into Chado.';

COMMENT ON COLUMN geolocation.description IS 'A textual representation of the location, if this is the original georeference. Optional if the original georeference is available in lat/long coordinates.';

COMMENT ON COLUMN geolocation.coordinate_xml IS 'The georeference in XML format, preferably in GML.';

COMMENT ON COLUMN geolocation.latitude IS 'The decimal latitude coordinate of the georeference, using positive and negative sign to indicate N and S, respectively.';

COMMENT ON COLUMN geolocation.longitude IS 'The decimal longitude coordinate of the georeference, using positive and negative sign to indicate E and W, respectively.';

COMMENT ON COLUMN geolocation.geodetic_datum IS 'The geodetic system on which the geo-reference coordinates are based. For geo-references measured between 1984 and 2010, this will typically be WGS84.';
   
COMMENT ON COLUMN geolocation.altitude IS 'The altitude (elevation) of the location in meters. If the altitude is only known as a range, this is the average, and altitude_dev will hold half of the width of the range.';

COMMENT ON COLUMN geolocation.altitude_dev IS 'The possible deviation in altitude, in meters, from the average altitude for collected individuals. Will be empty (null) if the altitude is exact.';

COMMENT ON COLUMN geolocation.postalcode IS 'The postal code, or zipcode in the US, within which the georeference falls.';

COMMENT ON COLUMN geolocation.county IS 'The county (or equivalent local government unit) whithin which the georeference falls. This should probably rather be a foreign key to a cvterm, but there is an unresolved problem about the univocality constraint with location name ontologies, such as the Gazetteer.';

COMMENT ON COLUMN geolocation.province IS 'The province, or state, within which the georeference falls. This should probably rather be a foreign key to a cvterm, but there is an unresolved problem about the univocality constraint with location name ontologies, such as the Gazetteer.';

COMMENT ON COLUMN geolocation.country IS 'The country within which the georeference falls. This should probably rather be a foreign key to a cvterm, but there is an unresolved problem about the univocality constraint with location name ontologies, such as the Gazetteer.';

-- table image (link out to a file name)
--
-- Link to an external image file

CREATE TABLE image (
	image_id serial NOT NULL,
        PRIMARY KEY (image_id),
        identifier character varying(255),
	uri character varying(1024) NOT NULL,
        CONSTRAINT image_c1 UNIQUE (identifier),
        CONSTRAINT image_c2 UNIQUE (uri)
);

COMMENT ON TABLE image IS 'Link to an external image';

COMMENT ON COLUMN image.identifier IS 'Unique identifier for the image, such as a LSID, or any other GUID';

COMMENT ON COLUMN image.uri IS 'URL or local file path to image';

-- table stock
--
-- Keeps track of information related to stocks or lines kept for
-- experimental purposes

CREATE TABLE stock (
	stock_id SERIAL NOT NULL,
        PRIMARY KEY (stock_id),
	name character varying(255) NOT NULL UNIQUE,
	description character varying(255),
	biotype_id INTEGER NOT NULL,
        FOREIGN KEY (biotype_id) REFERENCES biotype (biotype_id)
                ON DELETE RESTRICT,
	maternal_biotype_id INTEGER,
        FOREIGN KEY (maternal_biotype_id) REFERENCES biotype (biotype_id)
                ON DELETE RESTRICT,
	paternal_biotype_id INTEGER,
        FOREIGN KEY (paternal_biotype_id) REFERENCES biotype (biotype_id)
                ON DELETE RESTRICT,
	geolocation_id INTEGER,
        FOREIGN KEY (geolocation_id) REFERENCES geolocation (geolocation_id)
                ON DELETE RESTRICT,
	experimenter_id INTEGER,
        FOREIGN KEY (experimenter_id) REFERENCES contact (contact_id)
                ON DELETE RESTRICT,
	date_created date
);

COMMENT ON TABLE stock IS 'A stock is a line raised from an individual that was either collected in the wild or bred in the laboratory.';

COMMENT ON COLUMN stock.name IS 'Reference name of the stock';

COMMENT ON COLUMN stock.description IS 'Information on how the stock was created or other relevant information describing stock';

COMMENT ON COLUMN stock.biotype_id IS 'Taxonomic designation of the stock. This is assumed to be a rather certain designation, although sometimes, such as for distinguishing between races, it may need to be verified through an assay.';

COMMENT ON COLUMN stock.maternal_biotype_id IS 'Maternal source of the stock if known';

COMMENT ON COLUMN stock.paternal_biotype_id IS 'Paternal source of the stock if known';

COMMENT ON COLUMN stock.geolocation_id IS 'Location where the stock was originally collected; should probably be null if the stock was generated in a lab';

COMMENT ON COLUMN stock.experimenter_id IS 'Experimenter who started the stock';

COMMENT ON COLUMN stock.date_created IS 'When the stock was created';

-- table individual
--
-- An individual of a population, collected in the wild, or resulting
-- from a cross in the laboratory.

CREATE TABLE individual (
	individual_id serial NOT NULL,
        PRIMARY KEY (individual_id),
	name character varying(255) NOT NULL,
	description character varying(255),
	is_captivity_reared boolean NOT NULL,
	collection_date date,
        gender_id integer,
        FOREIGN KEY (gender_id) REFERENCES cvterm (cvterm_id)
                ON DELETE RESTRICT,
	stock_id integer,
        FOREIGN KEY (stock_id) REFERENCES stock (stock_id)
                ON DELETE RESTRICT,
	geolocation_id integer,
        FOREIGN KEY (geolocation_id) REFERENCES geolocation (geolocation_id) 
                ON DELETE RESTRICT,
        host_organism_id integer,
        FOREIGN KEY (host_organism_id) REFERENCES organism (organism_id)
                ON DELETE RESTRICT,
	experimenter_id integer,
        FOREIGN KEY (experimenter_id) REFERENCES contact (contact_id) 
                ON DELETE RESTRICT,
        CONSTRAINT individual_c1 UNIQUE (name)
);

COMMENT ON TABLE individual IS 'An individual of a population, collected in the wild, or resulting from a cross in the laboratory.';

COMMENT ON COLUMN individual.name IS 'Reference name of the individual';

COMMENT ON COLUMN individual.description IS 'Description of the individual, as far as not covered in other attributes already.';

COMMENT ON COLUMN individual.is_captivity_reared IS 'True if the individual was reared in captivity, and false otherwise. Individuals collected in the field would have false here.';

COMMENT ON COLUMN individual.collection_date IS 'Date when the individual was collected';

COMMENT ON COLUMN individual.stock_id IS 'The stock the individual was raised from if it was not collected in the wild. Note that if a stock is assigned to an individual, the individual implicitly inherits the biotype designation of the stock.';

COMMENT ON COLUMN individual.geolocation_id IS 'Geographic site where individual was collected or raised';

COMMENT ON COLUMN individual.host_organism_id IS 'The host organism from which the individual was collected. This will typically only apply if the individual was collected in the wild. Note that this does not necessarily imply an ecological relationship between the host and the individual (although it often will if the data is collected).';

COMMENT ON COLUMN individual.gender_id IS 'The gender of the individual. This comes from a controlled vocabulary.';

COMMENT ON COLUMN individual.experimenter_id IS 'Person or institution that collected or raised the individual';

-- table individual_biotype
CREATE TABLE individual_biotype (
        individual_biotype_id serial NOT NULL,
        PRIMARY KEY (individual_biotype_id),
        individual_id integer NOT NULL,
        FOREIGN KEY (individual_id) REFERENCES individual (individual_id)
                ON DELETE CASCADE,
        biotype_id integer NOT NULL,
        FOREIGN KEY (biotype_id) REFERENCES biotype (biotype_id)
                ON DELETE RESTRICT,
        certainty_type_id integer NOT NULL,
        FOREIGN KEY (certainty_type_id) REFERENCES cvterm (cvterm_id)
                ON DELETE RESTRICT,
        CONSTRAINT individual_biotype_c1 UNIQUE (individual_id, biotype_id, certainty_type_id)
);

COMMENT ON TABLE individual_biotype IS 'Designation of one or more possible biotypes to an individual. There should be more than one designation for an individual iff none of the designations are certain, not if the individual is a hybrid (use the biotype itself to represent the hybrid). Individuals raised from stocks have an implicit biotype (that of the stock), and do not need an explicit biotype designation.';

COMMENT ON COLUMN individual_biotype.individual_id IS 'The individual to which the biotype is being designated.';

COMMENT ON COLUMN individual_biotype.biotype_id IS 'The biotype being designated to the individual.';

COMMENT ON COLUMN individual_biotype.certainty_type_id IS 'The certainty of the designation, as a term from a controlled vocabulary. There cannot be more than one biotype designation if the designation is to be considered certain.'; 

-- table individualprop
--
-- Property/value pairs of individuals, for example the generation the
-- individual is sampled from.

CREATE TABLE individualprop (
        individualprop_id serial NOT NULL,
        PRIMARY KEY (individualprop_id),
        individual_id integer not null,
        FOREIGN KEY (individual_id) REFERENCES individual (individual_id)
                ON DELETE CASCADE,
        cvterm_id integer not null,
        FOREIGN KEY (cvterm_id) REFERENCES cvterm (cvterm_id)
                ON DELETE CASCADE,
        value text,
        rank integer not null default 0,
        CONSTRAINT individualprop_c1 UNIQUE (individual_id,cvterm_id,rank)
);

COMMENT ON TABLE individualprop IS 'Property/value associations for individuals.';

COMMENT ON COLUMN individualprop.individual_id IS 'The individual to which the property applies.';

COMMENT ON COLUMN individualprop.cvterm_id IS 'The name of the property as a reference to a controlled vocabulary term.';

COMMENT ON COLUMN individualprop.value IS 'The value of the property.';

COMMENT ON COLUMN individualprop.rank IS 'The rank of the property value, if the property has an array of values.';

-- table crossexperiment
--
-- An experiment crossing two individuals. The individuals may be from
-- the same or different species, or the same or different biotypes.

CREATE TABLE crossexperiment (
	crossexperiment_id serial NOT NULL,
        PRIMARY KEY (crossexperiment_id),
	name character varying(255) NOT NULL,
        expdate date,
	experimenter_id integer,
        FOREIGN KEY (experimenter_id) REFERENCES contact (contact_id)
                ON DELETE RESTRICT,
	geolocation_id integer,
        FOREIGN KEY (geolocation_id) REFERENCES geolocation (geolocation_id) 
                ON DELETE RESTRICT,
	type_id integer NOT NULL,
        FOREIGN KEY (type_id) REFERENCES cvterm (cvterm_id) 
                ON DELETE RESTRICT,
        CONSTRAINT crossexperiment_c1 UNIQUE (name)
);

COMMENT ON TABLE crossexperiment IS 'An experiment crossing two individuals. The individuals may be from the same or different species, or the same or different biotypes.';

COMMENT ON COLUMN crossexperiment.name IS 'Reference name for the cross, also known as the "brood name." Existing conventions for naming the cross use the "stock" type and "biotype" information, listing the female "type" first. Thus, a backcross of a female F1 individual generated from a cross between a female H. erato cyrbia and a male H. himera by a male H. himera would be (HecHh)xHh_001, where 001 is the first replicate of this type of cross.';

COMMENT ON COLUMN crossexperiment.geolocation_id IS 'The geo-reference for where the experimental cross was conducted.'; 

COMMENT ON COLUMN crossexperiment.expdate IS 'The date of the cross experiment, typically the mating date.';

COMMENT ON COLUMN crossexperiment.experimenter_id IS 'The person who conducted the cross experiment.';

COMMENT ON COLUMN crossexperiment.type_id IS 'The type of cross, for example, F1, or F2, or backcross.';


-- table crossexperimentprop
--
-- examples for attribute/value pairs:
--      date_female_died,
--	days_mated,
--	days_laying,
--	num_eggs_hatched,
--	num_pupa,
--	num_adults,
--	num_females

CREATE TABLE crossexperimentprop (
	crossexperimentprop_id serial NOT NULL,
        PRIMARY KEY (crossexperimentprop_id),
	crossexperiment_id integer NOT NULL,
        FOREIGN KEY (crossexperiment_id) REFERENCES crossexperiment (crossexperiment_id) 
                ON DELETE CASCADE,
	cvterm_id integer NOT NULL,
        FOREIGN KEY (cvterm_id) REFERENCES cvterm (cvterm_id)
                ON DELETE CASCADE,
        value text,
        rank integer not null default 0,
	CONSTRAINT crossexperimentprop_c1 UNIQUE (crossexperiment_id, cvterm_id, rank)
);

COMMENT ON TABLE crossexperimentprop IS 'Property/value associations for cross experiments.';

COMMENT ON COLUMN crossexperimentprop.crossexperiment_id IS 'The cross experiment to which the property applies.';

COMMENT ON COLUMN crossexperimentprop.cvterm_id IS 'The name of the property as a reference to a controlled vocabulary term.';

COMMENT ON COLUMN crossexperimentprop.value IS 'The value of the property.';

COMMENT ON COLUMN crossexperimentprop.rank IS 'The rank of the property value, if the property has an array of values.';

-- table crossexperiment_individual
--
-- Note: Though it is theoretically possible to conduct cross
-- experiments in a way that the female could also one out of multiple
-- individuals, there isn't a use case for this right now and we
-- therefore defer accommodating this until there is a requirement to
-- do so.

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

-- table individual_relationship
--
-- The parental relationship between two individuals, either of
-- paternal or maternal type. There may be multiple parental
-- relationships of the same type for an individual among the progeny
-- if the exact maternal or paternal individuals have not (yet) been
-- determined.

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


-- table gtassay
--
-- Genotyping assay, or method of polymorphism detection
--
-- TO BE REVIEWED: If an assay has been validated under different
-- conditions, such as for different species, we can't express that
-- right now. We may need to introduce a gtassayvalidation (or
-- something similar) table for that purpose.

CREATE TABLE gtassay (
	gtassay_id serial NOT NULL,
        PRIMARY KEY (gtassay_id),
	name character varying(255) NOT NULL,
	species_id integer,
        FOREIGN KEY (species_id) REFERENCES organism(organism_id) 
                ON DELETE RESTRICT,
	image_id integer,
        FOREIGN KEY (image_id) REFERENCES image (image_id) 
                ON DELETE RESTRICT,
	locus_id integer,
        FOREIGN KEY (locus_id) REFERENCES feature (feature_id)
                ON DELETE RESTRICT,
	type_id integer,
        FOREIGN KEY (type_id) REFERENCES cvterm (cvterm_id)
                ON DELETE RESTRICT,
        CONSTRAINT gtassay_c1 UNIQUE (name)
);

COMMENT ON TABLE gtassay IS 'Genotyping assay, or method of polymorphism detection.';

COMMENT ON COLUMN gtassay.name IS 'Reference name of the genotyping assay';

COMMENT ON COLUMN gtassay.species_id IS 'The species on which the assay was validated; this need not be the same as the one from which the specimen was obtained that is used in the genotyping experiment.';

COMMENT ON COLUMN gtassay.image_id IS 'The image documenting the quality and/or success of the assay; typically this will be a gel image, for example showing the purity of bands.';

COMMENT ON COLUMN gtassay.locus_id IS 'The locus that is being assayed for polymorphisms';

COMMENT ON COLUMN gtassay.type_id IS 'The type of the assay. Usually this is the method of scoring the polymorphism (e.g., AFLP, SSCP, RFLP, size polymorphism).';

-- create table gtassayprop
-- 
-- examples for attribute/value pairs:
--	specific_pcr_conditions,
--	annealing_temp,
--	microsat_repeat_type,
--	snp_type,     (note: shouldn't this be a ref. to a feature?)
--	snp_position, (note: shouldn't this be a ref. to a feature?)
--	expected_length (of PCR product)
--	amplicon_contains_intron,
--	outcome_success

CREATE TABLE gtassayprop (
	gtassayprop_id serial NOT NULL,
        PRIMARY KEY (gtassayprop_id),
	gtassay_id integer NOT NULL,
        FOREIGN KEY (gtassay_id) REFERENCES gtassay (gtassay_id) 
                ON DELETE CASCADE,
	cvterm_id integer NOT NULL,
        FOREIGN KEY (cvterm_id) REFERENCES cvterm (cvterm_id)
                ON DELETE CASCADE,
        value text,
        rank integer not null default 0,
	CONSTRAINT gtassayprop_c1 UNIQUE (gtassay_id, cvterm_id, rank)
);

COMMENT ON TABLE gtassayprop IS 'Property/value associations for genotyping assays.';

COMMENT ON COLUMN gtassayprop.gtassay_id IS 'The genotyping assay to which the property applies.';

COMMENT ON COLUMN gtassayprop.cvterm_id IS 'The name of the property as a reference to a controlled vocabulary term.';

COMMENT ON COLUMN gtassayprop.value IS 'The value of the property.';

COMMENT ON COLUMN gtassayprop.rank IS 'The rank of the property value, if the property has an array of values.';


--
-- table reagent
-- 
-- A reagent used in an assay, for example in a genotyping assay. The
-- most often used reagents will be PCR primers, but there are other
-- reagents used in genotyping assays too that characterize the
-- assay. For example,
--	rflp_enzyme,
--	aflp_adapte,
--	aflp_adapter_enzyme,
--	aflp_overhang_linker.

CREATE TABLE reagent (
        reagent_id serial NOT NULL,
        PRIMARY KEY (reagent_id),
        name character varying (80) NOT NULL,
        type_id integer NOT NULL,
        FOREIGN KEY (type_id) REFERENCES cvterm (cvterm_id)
                ON DELETE RESTRICT,
        feature_id integer,
        FOREIGN KEY (feature_id) REFERENCES feature (feature_id)
                ON DELETE CASCADE,
        CONSTRAINT reagent_c1 UNIQUE (name, type_id)
);

COMMENT ON TABLE reagent IS 'A reagent such as a primer, an enzyme, an adapter oligo, a linker oligo. Reagents are used in genotyping assays, or in any other kind of assay.';

COMMENT ON COLUMN reagent.name IS 'The name of the reagent. The name should be unique for a given type.';

COMMENT ON COLUMN reagent.type_id IS 'The type of the reagent, for example linker oligomer, or forward primer.';

COMMENT ON COLUMN reagent.feature_id IS 'If the reagent is a primer, the feature that it corresponds to. More generally, the corresponding feature for any reagent that has a sequence that maps to another sequence.';

--
-- table reagentprop
--
-- Property/value associations for reagents, such as Tm, Km, optimal
-- concentration or buffer, etc

CREATE TABLE reagentprop (
	reagentprop_id serial NOT NULL,
        PRIMARY KEY (reagentprop_id),
	reagent_id integer NOT NULL,
        FOREIGN KEY (reagent_id) REFERENCES reagent (reagent_id) 
                ON DELETE CASCADE,
	cvterm_id integer NOT NULL,
        FOREIGN KEY (cvterm_id) REFERENCES cvterm (cvterm_id)
                ON DELETE CASCADE,
        value text,
        rank integer not null default 0,
	CONSTRAINT reagentprop_c1 UNIQUE (reagent_id, cvterm_id, rank)
);

COMMENT ON TABLE reagentprop IS 'Property/value associations for reagents.';

COMMENT ON COLUMN reagentprop.reagent_id IS 'The reagent to which the property applies.';

COMMENT ON COLUMN reagentprop.cvterm_id IS 'The name of the property as a reference to a controlled vocabulary term.';

COMMENT ON COLUMN reagentprop.value IS 'The value of the property.';

COMMENT ON COLUMN reagentprop.rank IS 'The rank of the property value, if the property has an array of values.';

--
-- table reagent_relationship
--
-- Relationships between reagents. Some reagents form a group; i.e.,
-- they are used all together or not at all. Examples are
-- adapter/linker/enzyme assay reagents.
CREATE TABLE reagent_relationship (
        reagent_relationship_id serial NOT NULL,
        PRIMARY KEY (reagent_relationship_id),
        subject_reagent_id integer NOT NULL,
        FOREIGN KEY (subject_reagent_id) REFERENCES reagent (reagent_id)
                ON DELETE CASCADE,
        object_reagent_id integer NOT NULL,
        FOREIGN KEY (object_reagent_id) REFERENCES reagent (reagent_id)
                ON DELETE CASCADE,
        type_id integer NOT NULL,
        FOREIGN KEY (type_id) REFERENCES cvterm (cvterm_id)
                ON DELETE RESTRICT,
        CONSTRAINT reagent_relationship_c1 UNIQUE (subject_reagent_id, object_reagent_id, type_id)
);

COMMENT ON TABLE reagent_relationship IS 'Relationships between reagents. Some reagents form a group; i.e., they are used all together or not at all. Examples are adapter/linker/enzyme assay reagents.';

COMMENT ON COLUMN reagent_relationship.subject_reagent_id IS 'The subject reagent in the relationship. In parent/child terminology, the subject is the child. For example, in "linkerA 3prime-overhang-linker enzymeA" linkerA is the subject, 3prime-overhand-linker is the type, and enzymeA is the object.'; 

COMMENT ON COLUMN reagent_relationship.object_reagent_id IS 'The object reagent in the relationship. In parent/child terminology, the object is the parent. For example, in "linkerA 3prime-overhang-linker enzymeA" linkerA is the subject, 3prime-overhand-linker is the type, and enzymeA is the object.'; 

COMMENT ON COLUMN reagent_relationship.type_id IS 'The type (or predicate) of the relationship. For example, in "linkerA 3prime-overhang-linker enzymeA" linkerA is the subject, 3prime-overhand-linker is the type, and enzymeA is the object.'; 

-- 
-- table gtassay_reagent
--
-- Reagents used by a genotyping assay. An assay may use multiple
-- reagents.

CREATE TABLE gtassay_reagent (
        gtassay_reagent_id serial NOT NULL,
        PRIMARY KEY (gtassay_reagent_id),
        gtassay_id integer NOT NULL,
        FOREIGN KEY (gtassay_id) REFERENCES gtassay (gtassay_id)
                ON DELETE CASCADE,
        reagent_id integer NOT NULL,
        FOREIGN KEY (reagent_id) REFERENCES reagent (reagent_id)
                ON DELETE CASCADE,
        type_id integer NOT NULL,
        CONSTRAINT gtassay_reagent_c1 UNIQUE (gtassay_id, reagent_id, type_id)
);

COMMENT ON TABLE gtassay_reagent IS 'Reagents used by a genotyping assay. An assay may use multiple reagents.';

COMMENT ON COLUMN gtassay_reagent.gtassay_id IS 'The genotyping assay using the reagent.';

COMMENT ON COLUMN gtassay_reagent.reagent_id IS 'The reagent used by the genotyping assay.';

--COMMENT ON COLUMN gtassay_reagent.type_id IS 'The type or role in which the reagent is being used. For example, a primer may be used as a forward primer or a reverse primer, or a linker oligonucleotide may be used 3'' or 5''. Oftentimes, the type may be identical to the type of the reagent, though. A reagent can be used by one assay in the same role only once.';

-- table specimen
--
-- Part of an individual that is used in an experiment

CREATE TABLE specimen (
	specimen_id serial NOT NULL,
        PRIMARY KEY (specimen_id),
	name character varying(255) NOT NULL,
	description character varying(255),
	identifier character varying(255),
	storage_location character varying(255),
	extract_type_id integer NOT NULL,
        FOREIGN KEY (extract_type_id) REFERENCES cvterm (cvterm_id)
                ON DELETE RESTRICT,
	tissue_type_id integer NOT NULL,
        FOREIGN KEY (tissue_type_id) REFERENCES cvterm (cvterm_id)
                ON DELETE RESTRICT,
	individual_id integer NOT NULL,
        FOREIGN KEY (individual_id) REFERENCES individual (individual_id)
                ON DELETE CASCADE,
	experimenter_id integer,
        FOREIGN KEY (experimenter_id) REFERENCES contact (contact_id)
                ON DELETE RESTRICT,
        CONSTRAINT specimen_c1 UNIQUE (name)
);

COMMENT ON TABLE specimen IS 'Part of an individual that is used in an experiment';

COMMENT ON COLUMN specimen.name IS 'Reference name for the specimen';

COMMENT ON COLUMN specimen.description IS 'Description of the specimen including information on specimen quality, e.g. concentration, purity, etc.';

COMMENT ON COLUMN specimen.identifier IS 'Identifier, for example a barcode, of the specimen';

COMMENT ON COLUMN specimen.extract_type_id IS 'Type of extraction used, or how the specimen was processed to produce distinctive molecules';

COMMENT ON COLUMN specimen.tissue_type_id IS 'Tissue or morphological type that was used in the specimen extraction';

COMMENT ON COLUMN specimen.individual_id IS 'Individual used in the extraction';

COMMENT ON COLUMN specimen.storage_location IS 'Location, e.g. freezer, lab, etc., where specimen was stored';

COMMENT ON COLUMN specimen.experimenter_id IS 'Researcher that extracted the specimen';

-- table gtexperiment
--
-- Experiment to assign the genotype of an individual at a particular
-- locus

CREATE TABLE gtexperiment (
	gtexperiment_id serial NOT NULL,
        PRIMARY KEY (gtexperiment_id),
	specimen_id integer NOT NULL,
        FOREIGN KEY (specimen_id) REFERENCES specimen (specimen_id) 
                ON DELETE RESTRICT,
	gtassay_id integer NOT NULL,
        FOREIGN KEY (gtassay_id) REFERENCES gtassay (gtassay_id) 
                ON DELETE RESTRICT,
	genotype_id integer,
        FOREIGN KEY (genotype_id) REFERENCES genotype (genotype_id)
                ON DELETE RESTRICT,
	experimenter_id integer,
        FOREIGN KEY (experimenter_id) REFERENCES contact (contact_id)
                ON DELETE RESTRICT,
	experiment_date date,
	pub_id integer,
        FOREIGN KEY (pub_id) REFERENCES pub (pub_id)
                ON DELETE RESTRICT,
	notes character varying(255)
);

COMMENT ON TABLE gtexperiment IS 'Genotyping experiment; an experiment to assign the genotype of an individual at a particular locus.';

COMMENT ON COLUMN gtexperiment.specimen_id IS 'Specimen used in the experiment';

COMMENT ON COLUMN gtexperiment.gtassay_id IS 'The genotyping assay used to determine the genotype.';

COMMENT ON COLUMN gtexperiment.genotype_id IS 'The genotype determined by the experiment.';

COMMENT ON COLUMN gtexperiment.pub_id IS 'Publication reference where the genotype experiment was published';

COMMENT ON COLUMN gtexperiment.experimenter_id IS 'Person performing the experiment';

COMMENT ON COLUMN gtexperiment.experiment_date IS 'Date that the experiment was performed';

COMMENT ON COLUMN gtexperiment.notes IS 'Notes on the genotype assignement';

-- create table gtexperiment_project
--
-- Associates a genotype experiment with a project (e.g., an
-- experimental study)

CREATE TABLE gtexperiment_project (
	gtexperiment_project_id serial NOT NULL,
        PRIMARY KEY (gtexperiment_project_id),
	gtexperiment_id integer NOT NULL,
        FOREIGN KEY (gtexperiment_id) REFERENCES gtexperiment (gtexperiment_id)
		ON DELETE CASCADE,
	project_id integer NOT NULL,
        FOREIGN KEY (project_id) REFERENCES project (project_id)
		ON DELETE CASCADE,
	CONSTRAINT gtexperiment_project_c1 UNIQUE (gtexperiment_id, project_id)
);

COMMENT ON TABLE gtexperiment_project IS 'Associates a genotype experiment with a project (e.g., an experimental study)';

-- create table individual_image

CREATE TABLE individual_image (
	individual_image_id serial NOT NULL,
        PRIMARY KEY (individual_image_id),
	individual_id integer NOT NULL REFERENCES individual(individual_id) ON DELETE RESTRICT,
	image_id integer NOT NULL REFERENCES image(image_id) ON DELETE RESTRICT,
	UNIQUE (individual_id, image_id)
);

-- create table specimen_image

CREATE TABLE specimen_image (
	specimen_image_id serial NOT NULL,
        PRIMARY KEY (specimen_image_id),
	specimen_id integer NOT NULL REFERENCES specimen(specimen_id) ON DELETE RESTRICT,
	image_id integer NOT NULL REFERENCES image(image_id) ON DELETE RESTRICT,
	UNIQUE (specimen_id, image_id)
);

-- table ptassay
--
-- Method of polymorphism detection

CREATE TABLE ptassay (
	ptassay_id serial NOT NULL,
        PRIMARY KEY (ptassay_id),
	name character varying(255) NOT NULL UNIQUE
);

COMMENT ON TABLE ptassay IS 'Phenotype determination assay';

COMMENT ON COLUMN ptassay.name IS 'Reference name of the phenotyping assay';

-- create table ptassayprop

CREATE TABLE ptassayprop (
	ptassayprop_id serial NOT NULL,
        PRIMARY KEY (ptassayprop_id),
	ptassay_id integer NOT NULL,
        FOREIGN KEY (ptassay_id) REFERENCES ptassay (ptassay_id) 
                ON DELETE CASCADE,
	cvterm_id integer NOT NULL,
        FOREIGN KEY (cvterm_id) REFERENCES cvterm (cvterm_id)
                ON DELETE CASCADE,
        value text,
        rank integer not null default 0,
	CONSTRAINT ptassayprop_c1 UNIQUE (ptassay_id, cvterm_id, rank)
);

COMMENT ON TABLE ptassayprop IS 'Property/value associations for phenotyping assays.';

COMMENT ON COLUMN ptassayprop.ptassay_id IS 'The phenotyping assay to which the property applies.';

COMMENT ON COLUMN ptassayprop.cvterm_id IS 'The name of the property as a reference to a controlled vocabulary term.';

COMMENT ON COLUMN ptassayprop.value IS 'The value of the property.';

COMMENT ON COLUMN ptassayprop.rank IS 'The rank of the property value, if the property has an array of values.';


-- table individual_phenotype
--
-- Experimental result or observation associating an individual with a
-- phenotype

CREATE TABLE individual_phenotype (
	individual_phenotype_id serial NOT NULL,
        PRIMARY KEY (individual_phenotype_id),
	individual_id integer NOT NULL,
        FOREIGN KEY (individual_id) REFERENCES individual (individual_id)
		ON DELETE CASCADE,
	ptassay_id integer,
        FOREIGN KEY (ptassay_id) REFERENCES ptassay (ptassay_id)
		ON DELETE SET NULL,
	phenotype_id integer,
        FOREIGN KEY (phenotype_id) REFERENCES phenotype (phenotype_id)
                ON DELETE CASCADE,
	experimenter_id integer,
        FOREIGN KEY (experimenter_id) REFERENCES contact (contact_id)
                ON DELETE SET NULL,
	assay_date date,
	pub_id integer,
        FOREIGN KEY (pub_id) REFERENCES pub (pub_id)
                ON DELETE SET NULL,
	notes character varying(255)
);

COMMENT ON TABLE individual_phenotype IS 'Experimental result or observation associating an individual with a phenotype';

COMMENT ON COLUMN individual_phenotype.individual_id IS 'Individual used in the experiment';

COMMENT ON COLUMN individual_phenotype.ptassay_id IS 'Phenotyping assay used to determine the phenotype';

COMMENT ON COLUMN individual_phenotype.phenotype_id IS 'The phenotype determined in the experiment.';

COMMENT ON COLUMN individual_phenotype.experimenter_id IS 'Person performing the experiment';

COMMENT ON COLUMN individual_phenotype.assay_date IS 'Date that the experiment was performed';

COMMENT ON COLUMN individual_phenotype.pub_id IS 'Publication reference where the phenotype experiment resulting in the phenotype assignment was published';

COMMENT ON COLUMN individual_phenotype.notes IS 'Notes on the phenotype assignment';

-- create table individual_phenotype_project
--
-- Assigns the individual-phenotype association to a project (e.g., an
-- experimental study)

CREATE TABLE individual_phenotype_project (
	individual_phenotype_project_id serial NOT NULL,
        PRIMARY KEY (individual_phenotype_project_id),
	individual_phenotype_id integer NOT NULL, 
	FOREIGN KEY (individual_phenotype_id) REFERENCES individual_phenotype (individual_phenotype_id)
		ON DELETE CASCADE,
	project_id integer NOT NULL,
        FOREIGN KEY (project_id) REFERENCES project (project_id)
		ON DELETE CASCADE,
	CONSTRAINT individual_phenotype_project_c1 UNIQUE (individual_phenotype_id, project_id)
);

COMMENT ON TABLE individual_phenotype_project IS 'Assigns the individual-phenotype association to a project (e.g., an experimental study)';

-- table stock_phenotype
--
-- Experimental result or observation associating a stock with a
-- phenotype

CREATE TABLE stock_phenotype (
	stock_phenotype_id serial NOT NULL,
        PRIMARY KEY (stock_phenotype_id),
	stock_id integer NOT NULL,
        FOREIGN KEY (stock_id) REFERENCES stock (stock_id)
		ON DELETE CASCADE,
	ptassay_id integer,
        FOREIGN KEY (ptassay_id) REFERENCES ptassay (ptassay_id)
		ON DELETE SET NULL,
	phenotype_id integer,
        FOREIGN KEY (phenotype_id) REFERENCES phenotype (phenotype_id)
                ON DELETE CASCADE,
	experimenter_id integer,
        FOREIGN KEY (experimenter_id) REFERENCES contact (contact_id)
                ON DELETE RESTRICT,
	assay_date date,
	pub_id integer,
        FOREIGN KEY (pub_id) REFERENCES pub (pub_id)
                ON DELETE SET NULL,
	notes character varying(255)
);

COMMENT ON TABLE stock_phenotype IS 'Experimental result or observation associating a stock with a phenotype';

COMMENT ON COLUMN stock_phenotype.stock_id IS 'Stock used in the experiment';

COMMENT ON COLUMN stock_phenotype.ptassay_id IS 'Phenotype assay used to assign the phenotype';

COMMENT ON COLUMN stock_phenotype.phenotype_id IS 'The phenotype determined by the experiment';

COMMENT ON COLUMN stock_phenotype.experimenter_id IS 'Person performing the experiment';

COMMENT ON COLUMN stock_phenotype.assay_date IS 'Date that the experiment was performed';

COMMENT ON COLUMN stock_phenotype.pub_id IS 'Publication reference where the phenotype experiment resulting in the phenotype assignment was published';

COMMENT ON COLUMN stock_phenotype.notes IS 'Notes on the phenotype assignment';

-- create table stock_phenotype_project
--
-- Assigns the stock-phenotype association to a project (e.g., an
-- experimental study)

CREATE TABLE stock_phenotype_project (
	stock_phenotype_project_id serial NOT NULL,
        PRIMARY KEY (stock_phenotype_project_id),
	stock_phenotype_id integer NOT NULL, 
	FOREIGN KEY (stock_phenotype_id) REFERENCES stock_phenotype (stock_phenotype_id)
		ON DELETE CASCADE,
	project_id integer NOT NULL,
        FOREIGN KEY (project_id) REFERENCES project (project_id)
		ON DELETE CASCADE,
	CONSTRAINT stock_phenotype_project_c1 UNIQUE (stock_phenotype_id, project_id)
);

COMMENT ON TABLE stock_phenotype_project IS 'Assigns the stock-phenotype association to a project (e.g., an experimental study)';

-- table biotype_phenotype
--
-- Experimental result or observation associating a biotype with a
-- phenotype

CREATE TABLE biotype_phenotype (
	biotype_phenotype_id serial NOT NULL,
        PRIMARY KEY (biotype_phenotype_id),
	biotype_id integer NOT NULL,
        FOREIGN KEY (biotype_id) REFERENCES biotype (biotype_id)
		ON DELETE CASCADE,
	ptassay_id integer,
        FOREIGN KEY (ptassay_id) REFERENCES ptassay (ptassay_id)
		ON DELETE SET NULL,
	phenotype_id integer,
        FOREIGN KEY (phenotype_id) REFERENCES phenotype (phenotype_id)
                ON DELETE CASCADE,
	experimenter_id integer,
        FOREIGN KEY (experimenter_id) REFERENCES contact (contact_id)
                ON DELETE SET NULL,
	assay_date date,
	pub_id integer,
        FOREIGN KEY (pub_id) REFERENCES pub (pub_id)
                ON DELETE SET NULL,
	notes character varying(255)
);

COMMENT ON TABLE biotype_phenotype IS 'Experimental result or observation associating a biotype with a phenotype';

COMMENT ON COLUMN biotype_phenotype.biotype_id IS 'Biotype used in the experiment';

COMMENT ON COLUMN biotype_phenotype.ptassay_id IS 'Phenotyping assay used to assign the phenotype';

COMMENT ON COLUMN biotype_phenotype.phenotype_id IS 'Phenotype determined by the experiment';

COMMENT ON COLUMN biotype_phenotype.experimenter_id IS 'Person performing the experiment';

COMMENT ON COLUMN biotype_phenotype.assay_date IS 'Date that the experiment was performed';

COMMENT ON COLUMN biotype_phenotype.pub_id IS 'Publication reference where the phenotype experiment resulting in the phenotype assignment was published';

COMMENT ON COLUMN biotype_phenotype.notes IS 'Notes on the phenotype assignment';

-- create table biotype_phenotype_project
--
-- Assigns the biotype-phenotype association to a project (e.g., an
-- experimental study)

CREATE TABLE biotype_phenotype_project (
	biotype_phenotype_project_id serial NOT NULL,
        PRIMARY KEY (biotype_phenotype_project_id),
	biotype_phenotype_id integer NOT NULL,
        FOREIGN KEY (biotype_phenotype_id) REFERENCES biotype_phenotype (biotype_phenotype_id)
		ON DELETE CASCADE,
	project_id integer NOT NULL,
        FOREIGN KEY (project_id) REFERENCES project (project_id)
		ON DELETE CASCADE,
	CONSTRAINT biotype_phenotype_project_c1 UNIQUE (biotype_phenotype_id, project_id)
);

COMMENT ON TABLE biotype_phenotype_project IS 'Assigns the biotype-phenotype association to a project (e.g., an experimental study)';

--
-- Add foreign keys for entities that have a circular entity reference
-- with others, and for which the foreign key reference therefore
-- can't be added in-line.
--

-- none currently.