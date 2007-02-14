--
-- The natural diversity extension module for Chado.
--
-- This schema is an extension (a 'module') to the Chado schema, the common
-- data model of the GMOD project. See http://www.gmod.org/chado for further
-- information. You will likely need other modules of the chado schema
-- installed first (see dependencies).
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

COMMENT ON TABLE biotype IS 'Biotype is essentially a named container of species. Typically, this will be the species that hybridized to give rise to a hybrid individual, and the name will usually be the species part of the binomial names, concatenated by "x".'

COMMENT ON COLUMN biotype.name IS 'The unique name of the biotype. Typically the name of a species hybrid, with for example the species parts of the binomial names concatenated by "x".'

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

CREATE TABLE geolocation (
	geolocation_id serial PRIMARY KEY,
	description character varying(255),
	latitude real,
	longitude real,
	east boolean,
	north boolean,
	altitude_min smallint,
	altitude_max smallint,
	county character varying(255),
	province character varying(255),
	country character varying(255)
);

COMMENT ON TABLE geolocation IS 'The geo-referencable location of the stock';

COMMENT ON COLUMN geolocation.east IS 'East if true, west if false';

COMMENT ON COLUMN geolocation.north IS 'North if true, south if false';

COMMENT ON COLUMN geolocation.altitude_min IS 'Lower altitude range in meters';

COMMENT ON COLUMN geolocation.altitude_max IS 'Upper altitude range in meters';

-- table stock
--
-- Keeps track of information related to stocks or lines kept for
-- experimental purposes

CREATE TABLE stock (
	stock_id SERIAL PRIMARY KEY,
	stock_name character varying(255) NOT NULL UNIQUE,
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

COMMENT ON COLUMN stock.stock_name IS 'Reference name of the stock';

COMMENT ON COLUMN stock.description IS 'Information on how the stock was created or other relevant information describing stock';

COMMENT ON COLUMN stock.biotype_id IS 'Taxonomic designation of the stock';

COMMENT ON COLUMN stock.maternal_biotype_id IS 'Maternal source of the stock if known';

COMMENT ON COLUMN stock.paternal_biotype_id IS 'Paternal source of the stock if known';

COMMENT ON COLUMN stock.geolocation_id IS 'Location where the stock was originally collected; should probably be null if the stock was generated in a lab';

COMMENT ON COLUMN stock.experimenter_id IS 'Experimenter who started the stock';

COMMENT ON COLUMN stock.date_created IS 'When the stock was created';

-- table individual

CREATE TABLE individual (
	individual_id serial PRIMARY KEY,
	individual_name character varying(255) NOT NULL UNIQUE,
	wild boolean NOT NULL,
	pedigree_id integer,
        FOREIGN KEY (pedigree_id) REFERENCES pedigree (pedigree_id) 
                ON DELETE RESTRICT,
	stock_id integer,
        FOREIGN KEY (stock_id) REFERENCES stock (stock_id)
                ON DELETE RESTRICT,
	geolocation_id integer,
        FOREIGN KEY (geolocation_id) REFERENCES geolocation (geolocation_id) 
                ON DELETE RESTRICT,
	collection_date date,
	male boolean,
	biotype_id integer NOT NULL,
        FOREIGN KEY (biotype_id) REFERENCES biotype (biotype_id)
                ON DELETE RESTRICT,
	taxonomic_confidence real,
	experimenter_id integer,
        FOREIGN KEY (experimenter_id) REFERENCES contact (contact_id) 
                ON DELETE RESTRICT,
	notes character varying(255)
);

COMMENT ON TABLE individual IS 'An individual of a population, collected in the wild, or resulting from a cross in the laboratory.';

COMMENT ON COLUMN individual.individual_name IS 'Reference name of the individual';

COMMENT ON COLUMN individual.wild IS 'True if field collected specimen; false if reared in captivity';

COMMENT ON COLUMN individual.geolocation_id IS 'Geographic site where individual was collected or raised';

COMMENT ON COLUMN individual.collection_date IS 'Date when the individual was collected';

COMMENT ON COLUMN individual.male IS 'True if sex of the individual is male; false if female';

COMMENT ON COLUMN individual.biotype_id IS 'Genome composition';

COMMENT ON COLUMN individual.taxonomic_confidence IS 'Confidence that the researcher places in the taxonomic designation assigned in biotype_id, ranging from 0 (no confidence) to 1 (certainty)';

COMMENT ON COLUMN individual.experimenter_id IS 'Person or institution that collected or raised the individual';

COMMENT ON COLUMN individual.notes IS 'Notes on some aspect of individual, e.g. for an insect, what host plant it was collected on, or whether or not there were beak marks on the wings';

-- table pedigree

CREATE TABLE pedigree (
	pedigree_id serial PRIMARY KEY,
	pedigree_name character varying(255) NOT NULL UNIQUE,
	female_id integer,
        FOREIGN KEY (female_id) REFERENCES individual (individual_id) 
                ON DELETE RESTRICT,
	male_id integer,
        FOREIGN KEY (male_id) REFERENCES individual (individual_id) 
                ON DELETE RESTRICT,
	experimenter_id integer,
        FOREIGN KEY (experimenter_id) REFERENCES contact (contact_id)
                ON DELETE RESTRICT,
	geolocation_id integer,
        FOREIGN KEY (geolocation_id) REFERENCES geolocation (geolocation_id) 
                ON DELETE RESTRICT,
	date_mated date,
	date_female_died date,
	cross_type_id integer,
        FOREIGN KEY (cross_type_id) REFERENCES cvterm (cvterm_id) 
                ON DELETE RESTRICT
);

COMMENT ON TABLE pedigree IS 'Pedigrees of individuals.'
COMMENT ON COLUMN pedigree.pedigree_name IS 'Reference name given the pedigree by the experimenter';
COMMENT ON COLUMN pedigree.date_mated IS 'Date the female was mated';
COMMENT ON COLUMN pedigree.date_female_died IS 'Date female harvested';

-- table pedigreeprop
--
-- examples for attribute/value pairs:
--	days_mated,
--	days_laying,
--	num_eggs_hatched,
--	num_pupa,
--	num_adults,
--	num_females

CREATE TABLE pedigreeprop (
	pedigreeprop_id serial PRIMARY KEY,
	pedigree_id integer NOT NULL,
        FOREIGN KEY (pedigree_id) REFERENCES pedigree (pedigree_id) 
                ON DELETE CASCADE,
	cvterm_id integer NOT NULL,
        FOREIGN KEY (cvterm_id) REFERENCES cvterm (cvterm_id)
                ON DELETE CASCADE,
	rank integer,
	CONSTRAINT pedigreeprop_c1 UNIQUE (pedigree_id, cvterm_id)
);


-- table primer
--
-- A short oligonucleotide

CREATE TABLE primer (
	primer_id serial PRIMARY KEY,
	primer_name character varying(255) NOT NULL UNIQUE,
	primer_sequence character varying(128),
	designer_id integer REFERENCES contact (contact_id) ON DELETE RESTRICT,
	published_id integer,
	tm real,
	strand_forward boolean,
	notes character varying(255)
);

ALTER TABLE primer
	ADD CONSTRAINT primer_published_id_fkey FOREIGN KEY (published_id)
	REFERENCES pub(pub_id) ON DELETE RESTRICT;

COMMENT ON TABLE primer IS 'A short oligonucleotide';
COMMENT ON COLUMN primer.primer_name IS 'Reference name of the primer';
COMMENT ON COLUMN primer.primer_sequence IS 'The 5-prime to 3-prime nucleotide sequence of the primer';
COMMENT ON COLUMN primer.designer_id IS 'Experimenter that designed the primer';
COMMENT ON COLUMN primer.published_id IS 'Journal where the primer sequence was published';
COMMENT ON COLUMN primer.tm IS 'Hypothesized melting temperature';
COMMENT ON COLUMN primer.strand_forward IS 'Primer anneals to forward strand if true, reverse strand if false';
COMMENT ON COLUMN primer.notes IS 'General observations about the primer';

-- table primer_designcluster
--
-- Relates primers to the clusters that they were designed from

CREATE TABLE primer_designcluster (
	primer_designcluster_id serial PRIMARY KEY,
	primer_id integer NOT NULL REFERENCES primer(primer_id) ON DELETE RESTRICT,
	designcluster_id integer NOT NULL,
	design_species_id integer REFERENCES organism(organism_id) ON DELETE RESTRICT,
	outgroup_id integer REFERENCES organism(organism_id) ON DELETE RESTRICT,
	pos smallint,
	UNIQUE (primer_id, designcluster_id)
);

ALTER TABLE primer_designcluster
	ADD CONSTRAINT primer_designcluster_designcluster_id_fkey
	FOREIGN KEY (designcluster_id)
	REFERENCES feature(feature_id) ON DELETE RESTRICT;

COMMENT ON TABLE primer_designcluster IS 'Relates primers to the clusters that they were designed from';
COMMENT ON COLUMN primer_designcluster.primer_id IS 'A primer';
COMMENT ON COLUMN primer_designcluster.designcluster_id IS 'Sequence or contig that the primer was designed from';
COMMENT ON COLUMN primer_designcluster.design_species_id IS 'Organism that the primer was designed from';
COMMENT ON COLUMN primer_designcluster.outgroup_id IS 'The outgroup used in primer design';
COMMENT ON COLUMN primer_designcluster.pos IS 'Position of the 5-prime end of the primer relative to the design cluster';

-- table gtassay
--
-- Method of polymorphism detection

CREATE TABLE gtassay (
	gtassay_id serial PRIMARY KEY,
	assay_name character varying(255) NOT NULL,
	locus_id integer,
        FOREIGN KEY (locus_id) REFERENCES feature (feature_id)
                ON DELETE RESTRICT,
	polytype_id integer,
        FOREIGN KEY (polytype_id) REFERENCES cvterm (cvterm_id)
                ON DELETE RESTRICT,
	pcrexperiment_id integer NOT NULL,
        FOREIGN KEY (pcrexperiment_id) REFERENCES pcrexperiment (pcrexperiment_id)
                ON DELETE RESTRICT,
        CONSTRAINT gtassay_c1 UNIQUE (assay_name)
);

COMMENT ON TABLE gtassay IS 'Method of polymorphism detection';
COMMENT ON COLUMN gtassay.assay_name IS 'Reference name of the assay';
COMMENT ON COLUMN gtassay.locus_id IS 'Locus that is examined for polymorphisms';
COMMENT ON COLUMN gtassay.polytype_id IS 'Method of scoring the polymorphism for this assay (e.g., AFLP, SSCP, RFLP, size polymorphism)';

-- create table gtassayprop
-- 
-- examples for attribute/value pairs:
--	specific_pcr_conditions,
--	annealing_temp,
--	rflp_enzyme,
--	microsat_repeat_type,
--	aflp_adapter_1,
--	aflp_adapter_2,
--	aflp_adapter_enzyme_1,
--	aflp_adapter_enzyme_2,
--	aflp_overhang_linker_1,
--	aflp_overhang_linker_2,
--	snp_type,
--	snp_position

CREATE TABLE gtassayprop (
	gtassayprop_id serial PRIMARY KEY,
	gtassay_id integer NOT NULL,
        FOREIGN KEY (gtassay_id) REFERENCES gtassay (gtassay_id) 
                ON DELETE CASCADE,
	cvterm_id integer NOT NULL,
        FOREIGN KEY (cvterm_id) REFERENCES cvterm (cvterm_id)
                ON DELETE CASCADE,
	rank integer,
	CONSTRAINT gtassayprop_c1 UNIQUE (gtassay_id, cvterm_id)
);

-- table specimen
--
-- Part of an individual that is used in an experiment

CREATE TABLE specimen (
	specimen_id serial PRIMARY KEY,
	specimen_name character varying(255) NOT NULL,
	description character varying(255),
	barcode character varying(255),
	extract_type_id integer NOT NULL,
        FOREIGN KEY (extract_type_id) REFERENCES cvterm (cvterm_id)
                ON DELETE RESTRICT,
	tissue_type_id integer NOT NULL,
        FOREIGN KEY (tissue_type_id) REFERENCES cvterm (cvterm_id)
                ON DELETE RESTRICT,
	individual_id integer NOT NULL,
        FOREIGN KEY (individual_id) REFERENCES individual (individual_id)
                ON DELETE CASCADE,
	storage_location character varying(255),
	experimenter_id integer,
        FOREIGN KEY (experimenter_id) REFERENCES contact (contact_id)
                ON DELETE RESTRICT,
        CONSTRAINT specimen_c1 UNIQUE (specimen_name)
);

COMMENT ON TABLE specimen IS 'Part of an individual that is used in an experiment';

COMMENT ON COLUMN specimen.specimen_name IS 'Reference name for the specimen';

COMMENT ON COLUMN specimen.description IS 'Description of the specimen including information on specimen quality, e.g. concentration, purity, etc.';

COMMENT ON COLUMN specimen.barcode IS 'Barcode identifier of the specimen';

COMMENT ON COLUMN specimen.extract_type_id IS 'How the specimen was processed to produce distinctive molecules';

COMMENT ON COLUMN specimen.tissue_type_id IS 'Tissue or morphological type that was used in the specimen extraction';

COMMENT ON COLUMN specimen.individual_id IS 'Individual used in the extraction';

COMMENT ON COLUMN specimen.storage_location IS 'Location, e.g. freezer, lab, etc., where specimen was stored';

COMMENT ON COLUMN specimen.experimenter_id IS 'Researcher that extracted the specimen';

-- table gtexperiment
--
-- Experiment to assign the genotype of an individual at a particular
-- locus

CREATE TABLE gtexperiment (
	gtexperiment_id serial PRIMARY KEY,
	specimen_id integer NOT NULL,
        FOREIGN KEY (speciment_id) REFERENCES specimen (specimen_id) 
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

COMMENT ON TABLE gtexperiment IS 'Experiment to assign the genotype of an individual at a particular locus';

COMMENT ON COLUMN gtexperiment.specimen_id IS 'Specimen used in the experiment';

COMMENT ON COLUMN gtexperiment.gtassay_id IS 'Assay conditions used to assign genotype';

COMMENT ON COLUMN gtexperiment.genotype_id IS 'Genotypic score';

COMMENT ON COLUMN gtexperiment.pub_id IS 'Publication reference where the genotype experiment was published';

COMMENT ON COLUMN gtexperiment.experimenter_id IS 'Person performing the experiment';

COMMENT ON COLUMN gtexperiment.experiment_date IS 'Date that the experiment was performed';

COMMENT ON COLUMN gtexperiment.notes IS 'Notes on score';

-- create table gtexperiment_project
--
-- Associates a genotype experiment with a project (e.g., an
-- experimental study)

CREATE TABLE gtexperiment_project (
	gtexperiment_project_id serial PRIMARY KEY,
	gtexperiment_id integer NOT NULL,
        FOREIGN KEY (gtexperiment_id) REFERENCES gtexperiment (gtexperiment_id)
		ON DELETE CASCADE,
	project_id integer NOT NULL,
        FOREIGN KEY (project_id) REFERENCES project (project_id)
		ON DELETE CASCADE,
	CONSTRAINT gtexperiment_project_c1 UNIQUE (gtexperiment_id, project_id);
);

COMMENT ON TABLE gtexperiment_project IS 'Associates a genotype experiment with a project (e.g., an experimental study)'

-- table image (link out to a file name)
--
-- Link to an external image file

CREATE TABLE image (
	image_id serial PRIMARY KEY,
	uri character varying(1024) NOT NULL UNIQUE
);

COMMENT ON TABLE image IS 'Link to an external image file';
COMMENT ON COLUMN image.uri IS 'URL or local file path to image';

-- table pcrexperiment
--
-- Initial examination of a locus

CREATE TABLE pcrexperiment (
	pcrexperiment_id serial PRIMARY KEY,
	expected_length smallint,
	intron boolean,
	test_species_id integer REFERENCES organism(organism_id) ON DELETE RESTRICT,
	outcome_success boolean,
	image_id integer REFERENCES image(image_id) ON DELETE RESTRICT,
	forward_primer_id integer REFERENCES primer(primer_id) ON DELETE RESTRICT,
	reverse_primer_id integer REFERENCES primer(primer_id) ON DELETE RESTRICT
);

COMMENT ON TABLE pcrexperiment IS 'Initial examination of a locus';
COMMENT ON COLUMN pcrexperiment.expected_length IS 'Length of the expectant product';
COMMENT ON COLUMN pcrexperiment.intron IS 'True if cDNA includes an intron, false if it does not';
COMMENT ON COLUMN pcrexperiment.test_species_id IS 'Taxa for which optimization experiments were performed';
COMMENT ON COLUMN pcrexperiment.outcome_success IS 'True if experiment generated strong product in expected size range, false if no product was generated, or NULL if a weak product or non-specific amplification was observed';
COMMENT ON COLUMN pcrexperiment.image_id IS 'Digital image of the experimental results';
COMMENT ON COLUMN pcrexperiment.forward_primer_id IS 'Primer that sits on the coding strand';
COMMENT ON COLUMN pcrexperiment.reverse_primer_id IS 'Primer that sits on the non-coding strand';

-- create table individual_image

CREATE TABLE individual_image (
	individual_image_id serial PRIMARY KEY,
	individual_id integer NOT NULL REFERENCES individual(individual_id) ON DELETE RESTRICT,
	image_id integer NOT NULL REFERENCES image(image_id) ON DELETE RESTRICT,
	UNIQUE (individual_id, image_id)
);

-- create table specimen_image

CREATE TABLE specimen_image (
	specimen_image_id serial PRIMARY KEY,
	specimen_id integer NOT NULL REFERENCES specimen(specimen_id) ON DELETE RESTRICT,
	image_id integer NOT NULL REFERENCES image(image_id) ON DELETE RESTRICT,
	UNIQUE (specimen_id, image_id)
);

-- table ptassay
--
-- Method of polymorphism detection

CREATE TABLE ptassay (
	ptassay_id serial PRIMARY KEY,
	assay_name character varying(255) NOT NULL UNIQUE
);

COMMENT ON TABLE ptassay IS 'Phenotype determination assay';
COMMENT ON COLUMN ptassay.assay_name IS 'Reference name of the assay';

-- create table ptassayprop

CREATE TABLE ptassayprop (
	ptassayprop_id serial PRIMARY KEY,
	ptassay_id integer NOT NULL,
        FOREIGN KEY (ptassay_id) REFERENCES ptassay (ptassay_id) 
                ON DELETE CASCADE,
	cvterm_id integer NOT NULL,
        FOREIGN KEY (cvterm_id) REFERENCES cvterm (cvterm_id)
                ON DELETE CASCADE,
	rank integer,
	CONSTRAINT ptassayprop_c1 UNIQUE (ptassay_id, cvterm_id)
);

-- table individual_phenotype
--
-- Experimental result or observation associating an individual with a
-- phenotype

CREATE TABLE individual_phenotype (
	individual_phenotype_id serial PRIMARY KEY,
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

COMMENT ON COLUMN individual_phenotype.ptassay_id IS 'Assay conditions used to assign phenotype';

COMMENT ON COLUMN individual_phenotype.phenotype_id IS 'Phenotypic score';

COMMENT ON COLUMN individual_phenotype.experimenter_id IS 'Person performing the experiment';

COMMENT ON COLUMN individual_phenotype.assay_date IS 'Date that the experiment was performed';

COMMENT ON COLUMN individual_phenotype.pub_id IS 'Publication reference where the phenotype experiment resulting in the phenotype assignment was published';

COMMENT ON COLUMN individual_phenotype.notes IS 'Notes on score';

-- create table individual_phenotype_project
--
-- Assigns the individual-phenotype association to a project (e.g., an
-- experimental study)

CREATE TABLE individual_phenotype_project (
	individual_phenotype_project_id serial PRIMARY KEY,
	individual_phenotype_id integer NOT NULL, 
	FOREIGN KEY (individual_phenotype_id) REFERENCES individual_phenotype (individual_phenotype_id)
		ON DELETE CASCADE,
	project_id integer NOT NULL,
        FOREIGN KEY (project_id) REFERENCES project (project_id)
		ON DELETE CASCADE,
	CONSTRAINT individual_phenotype_project_c1 UNIQUE (individual_phenotype_id, project_id)
);

COMMENT ON TABLE biotype_phenotype_project IS 'Assigns the individual-phenotype association to a project (e.g., an experimental study)';

-- table stock_phenotype
--
-- Experimental result or observation associating a stock with a
-- phenotype

CREATE TABLE stock_phenotype (
	stock_phenotype_id serial PRIMARY KEY,
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

COMMENT ON COLUMN stock_phenotype.ptassay_id IS 'Assay conditions used to assign phenotype';

COMMENT ON COLUMN stock_phenotype.phenotype_id IS 'Phenotypic score';

COMMENT ON COLUMN stock_phenotype.experimenter_id IS 'Person performing the experiment';

COMMENT ON COLUMN stock_phenotype.assay_date IS 'Date that the experiment was performed';

COMMENT ON COLUMN stock_phenotype.pub_id IS 'Publication reference where the phenotype experiment resulting in the phenotype assignment was published';

COMMENT ON COLUMN stock_phenotype.notes IS 'Notes on score';

-- create table stock_phenotype_project
--
-- Assigns the stock-phenotype association to a project (e.g., an
-- experimental study)

CREATE TABLE stock_phenotype_project (
	stock_phenotype_project_id serial PRIMARY KEY,
	stock_phenotype_id integer NOT NULL, 
	FOREIGN KEY (stock_phenotype_id) REFERENCES stock_phenotype (stock_phenotype_id)
		ON DELETE CASCADE,
	project_id integer NOT NULL,
        FOREIGN KEY (project_id) REFERENCES project (project_id)
		ON DELETE CASCADE,
	CONSTRAINT stock_phenotype_project_c1 UNIQUE (stock_phenotype_id, project_id)
);

COMMENT ON TABLE biotype_phenotype_project IS 'Assigns the stock-phenotype association to a project (e.g., an experimental study)';

-- table biotype_phenotype
--
-- Experimental result or observation associating a biotype with a
-- phenotype

CREATE TABLE biotype_phenotype (
	biotype_phenotype_id serial PRIMARY KEY,
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

COMMENT ON COLUMN biotype_phenotype.ptassay_id IS 'Assay conditions used to assign phenotype';

COMMENT ON COLUMN biotype_phenotype.phenotype_id IS 'Phenotypic score';

COMMENT ON COLUMN biotype_phenotype.experimenter_id IS 'Person performing the experiment';

COMMENT ON COLUMN biotype_phenotype.assay_date IS 'Date that the experiment was performed';

COMMENT ON COLUMN biotype_phenotype.pub_id IS 'Publication reference where the phenotype experiment resulting in the phenotype assignment was published';

COMMENT ON COLUMN biotype_phenotype.notes IS 'Notes on score';

-- create table biotype_phenotype_project
--
-- Assigns the biotype-phenotype association to a project (e.g., an
-- experimental study)

CREATE TABLE biotype_phenotype_project (
	biotype_phenotype_project_id serial PRIMARY KEY,
	biotype_phenotype_id integer NOT NULL,
        FOREIGN KEY (biotype_phenotype_id) REFERENCES biotype_phenotype (biotype_phenotype_id)
		ON DELETE CASCADE,
	project_id integer NOT NULL,
        FOREIGN KEY (project)id) REFERENCES project (project_id)
		ON DELETE CASCADE,
	CONSTRAINT biotype_phenotype_project_c1 UNIQUE (biotype_phenotype_id, project_id)
);

COMMENT ON TABLE biotype_phenotype_project IS 'Assigns the biotype-phenotype association to a project (e.g., an experimental study)';