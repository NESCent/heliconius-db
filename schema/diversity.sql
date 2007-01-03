--
-- The natural diversity extension module for Chado.
--
-- This schema is an extension (a 'module') to the Chado schema, the common
-- data model of the GMOD project. See http://www.gmod.org/chado for further
-- information. You will likely need other modules of the chado schema
-- installed first (see dependencies).
--

--
-- Copyright (c) 2006-2007, Nassib Nassar, nassar@etymon.com
-- Copyright (c) 2006-2007, Owen McMillan, womcmill@ncsu.edu
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

-- table stock
--
-- Keeps track of information related to stocks or lines kept for
-- experimental purposes

CREATE TABLE stock (
	stock_id serial PRIMARY KEY,
	stock_name character varying(255) NOT NULL UNIQUE,
	description character varying(255),
	biotype_id integer NOT NULL,
	maternal_source_id integer,
	paternal_source_id integer,
	geographic_site_id integer,
	experimenter_id integer,
	date_created date
);

COMMENT ON TABLE stock IS 'Keeps track of information related to stocks or lines kept for experimental purposes';
COMMENT ON COLUMN stock.stock_name IS 'Reference name of the stock';
COMMENT ON COLUMN stock.description IS 'Information on how the stock was created or other relevant information describing stock';
COMMENT ON COLUMN stock.biotype_id IS 'Taxonomic designation of the stock';
COMMENT ON COLUMN stock.maternal_source_id IS 'Maternal source of the stock if known';
COMMENT ON COLUMN stock.paternal_source_id IS 'Paternal source of the stock if known';
COMMENT ON COLUMN stock.geographic_site_id IS 'Location that is the source of the stock if known';
COMMENT ON COLUMN stock.experimenter_id IS 'Experimenter that started the stock';
COMMENT ON COLUMN stock.date_created IS 'When the stock was created';

-- create table biotype

CREATE TABLE biotype (
	biotype_id serial PRIMARY KEY
);

-- table biotype_organism
--
-- Allows grouping of several or more organisms to allow for the
-- presence of hybrid individuals

CREATE TABLE biotype_organism (
	biotype_organism_id serial PRIMARY KEY,
	biotype_id integer NOT NULL REFERENCES biotype(biotype_id) ON DELETE RESTRICT,
	organism_id integer NOT NULL REFERENCES organism(organism_id) ON DELETE RESTRICT,
	UNIQUE (biotype_id, organism_id)
);

COMMENT ON TABLE biotype_organism IS
	'Allows grouping of several or more organisms to allow for the presence of hybrid individuals';

-- table geographic_site:

CREATE TABLE geographic_site (
	geographic_site_id serial PRIMARY KEY,
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

COMMENT ON COLUMN geographic_site.east IS 'East if true, west if false';
COMMENT ON COLUMN geographic_site.north IS 'North if true, south if false';
COMMENT ON COLUMN geographic_site.altitude_min IS 'Lower altitude range in meters';
COMMENT ON COLUMN geographic_site.altitude_max IS 'Upper altitude range in meters';

-- table experimenter

CREATE TABLE experimenter (
	experimenter_id serial PRIMARY KEY,
	name character varying(255) NOT NULL UNIQUE
);

COMMENT ON COLUMN experimenter.name IS
	'Name of person (last, first) or institution performing the experiment';

-- table individual

CREATE TABLE individual (
	individual_id serial PRIMARY KEY,
	individual_name character varying(255) NOT NULL UNIQUE,
	wild boolean NOT NULL,
	pedigree_id integer REFERENCES individual(individual_id) ON DELETE RESTRICT,
	stock_id integer REFERENCES stock(stock_id) ON DELETE RESTRICT,
	geographic_site_id integer REFERENCES geographic_site(geographic_site_id) ON DELETE RESTRICT,
	collection_date date,
	male boolean,
	biotype_id integer NOT NULL REFERENCES biotype(biotype_id) ON DELETE RESTRICT,
	taxonomic_confidence real,
	experimenter_id integer REFERENCES experimenter(experimenter_id) ON DELETE RESTRICT,
	notes character varying(255)
);

COMMENT ON COLUMN individual.individual_name IS 'Reference name of the individual';
COMMENT ON COLUMN individual.wild IS 
	'True if field collected specimen; false if reared in captivity';
COMMENT ON COLUMN individual.geographic_site_id IS 
	'Geographic site where individual was collected or raised';
COMMENT ON COLUMN individual.collection_date IS 'Date when the individual was collected';
COMMENT ON COLUMN individual.male IS 'True if sex of the individual is male; false if female';
COMMENT ON COLUMN individual.biotype_id IS 'Genome composition';
COMMENT ON COLUMN individual.taxonomic_confidence IS
	'Confidence that the researcher places in the taxonomic designation assigned in biotype_id, ranging from 0 (no confidence) to 1 (certainty)';
COMMENT ON COLUMN individual.experimenter_id IS
	'Person or institution that collected or raised the individual';
COMMENT ON COLUMN individual.notes IS 'Notes on some aspect of individual, e.g. for an insect, what host plant it was collected on, or whether or not there were beak marks on the wings';

-- create table cross_type

CREATE TABLE cross_type (
	cross_type_id serial PRIMARY KEY,
	type_name character varying(255) NOT NULL UNIQUE
);

-- table pedigree

CREATE TABLE pedigree (
	pedigree_id serial PRIMARY KEY,
	pedigree_name character varying(255) NOT NULL UNIQUE,
	female_id integer REFERENCES individual(individual_id) ON DELETE RESTRICT,
	male_id integer REFERENCES individual(individual_id) ON DELETE RESTRICT,
	experimenter_id integer REFERENCES experimenter(experimenter_id) ON DELETE RESTRICT,
	experiment_geographic_site_id integer REFERENCES geographic_site(geographic_site_id) ON DELETE RESTRICT,
	date_mated date,
	date_female_died date,
--	days_mated smallint,
	cross_type_id integer REFERENCES cross_type(cross_type_id) ON DELETE RESTRICT
--	days_laying smallint,
--	num_eggs_hatched smallint,
--	num_pupa smallint,
--	num_adults smallint,
--	num_females smallint
);

COMMENT ON COLUMN pedigree.pedigree_name IS 'Reference name given the pedigree by the experimenter';
COMMENT ON COLUMN pedigree.date_mated IS 'Date the female was mated';
COMMENT ON COLUMN pedigree.date_female_died IS 'Date female harvested';
--COMMENT ON COLUMN pedigree.days_mated IS 'Number of days that the female was mated';
--COMMENT ON COLUMN pedigree.days_laying IS 'Number of days that the female laid eggs';
--COMMENT ON COLUMN pedigree.num_eggs_hatched IS 'Number of eggs that hatched';
--COMMENT ON COLUMN pedigree.num_pupa IS 'Number of larvae that are pupating';
--COMMENT ON COLUMN pedigree.num_adults IS 'Number of pupae ecclosing';
--COMMENT ON COLUMN pedigree.num_females IS 'Number of female offspring';

-- table pedigree_cvterm

CREATE TABLE pedigree_cvterm (
	pedigree_cvterm_id serial PRIMARY KEY,
	pedigree_id integer NOT NULL REFERENCES pedigree(pedigree_id) ON DELETE RESTRICT,
	cvterm_id integer NOT NULL,
	rank integer,
	cvterm_type character varying(255),
	UNIQUE (pedigree_id, cvterm_id)
);

ALTER TABLE pedigree_cvterm
	ADD CONSTRAINT pedigree_cvterm_cvterm_id_fkey FOREIGN KEY (cvterm_id)
	REFERENCES cvterm(cvterm_id) ON DELETE RESTRICT;

-- table polytype
--
-- Type of marker or method of scoring

CREATE TABLE polytype (
	polytype_id serial PRIMARY KEY,
	poly_name character varying(255) NOT NULL UNIQUE
);

COMMENT ON TABLE polytype IS 'Type of marker or method of scoring';
COMMENT ON COLUMN polytype.poly_name IS 'Type of marker or method of scoring';

-- table primer
--
-- A short oligonucleotide

CREATE TABLE primer (
	primer_id serial PRIMARY KEY,
	primer_name character varying(255) NOT NULL UNIQUE,
	primer_sequence character varying(128),
	designer_id integer REFERENCES experimenter(experimenter_id) ON DELETE RESTRICT,
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
	assay_name character varying(255) NOT NULL UNIQUE,
	locus_id integer,
	polytype_id integer REFERENCES polytype(polytype_id) ON DELETE RESTRICT,
	forward_primer_id integer REFERENCES primer(primer_id) ON DELETE RESTRICT,
	reverse_primer_id integer REFERENCES primer(primer_id) ON DELETE RESTRICT
);

ALTER TABLE gtassay
	ADD CONSTRAINT gtassay_locus_id_fkey FOREIGN KEY (locus_id)
	REFERENCES feature(feature_id) ON DELETE RESTRICT;

COMMENT ON TABLE gtassay IS 'Method of polymorphism detection';
COMMENT ON COLUMN gtassay.assay_name IS 'Reference name of the assay';
COMMENT ON COLUMN gtassay.locus_id IS 'Locus that is examined for polymorphisms';
COMMENT ON COLUMN gtassay.polytype_id IS 'Method of scoring the polymorphism for this assay (e.g., AFLP, SSCP, RFLP, size polymorphism)';
COMMENT ON COLUMN gtassay.forward_primer_id IS 'Primer that sits on the coding strand';
COMMENT ON COLUMN gtassay.reverse_primer_id IS 'Primer that sits on the non-coding strand';

-- create table gtassay_cvterm

CREATE TABLE gtassay_cvterm (
	gtassay_cvterm_id serial PRIMARY KEY,
	gtassay_id integer NOT NULL REFERENCES gtassay(gtassay_id) ON DELETE RESTRICT,
	cvterm_id integer NOT NULL,
	rank integer,
	cvterm_type character varying(255),
	UNIQUE (gtassay_id, cvterm_id)
--	specific_pcr_conditions character varying(255),
--	annealing_temp real,
--	rflp_enzyme character varying(64),
--	microsat_repeat_type character varying(16),
--	aflp_adapter_1 character varying(64),
--	aflp_adapter_2 character varying(64),
--	aflp_adapter_enzyme_1 character varying(64),
--	aflp_adapter_enzyme_2 character varying(64),
--	aflp_overhang_linker_1 character varying(16),
--	aflp_overhang_linker_2 character varying(16),
--	snp_type character varying(16),
--	snp_position smallint
);

ALTER TABLE gtassay_cvterm
	ADD CONSTRAINT gtassay_cvterm_cvterm_id_fkey FOREIGN KEY (cvterm_id)
	REFERENCES cvterm(cvterm_id) ON DELETE RESTRICT;

-- table specimen
--
-- Part of an individual that is used in an experiment

CREATE TABLE specimen (
	specimen_id serial PRIMARY KEY,
	specimen_name character varying(255) NOT NULL UNIQUE,
	description character varying(255),
	barcode character varying(255),
	extract_type_id integer NOT NULL,
	tissue_type_id integer NOT NULL,
	individual_id integer NOT NULL REFERENCES individual(individual_id) ON DELETE RESTRICT,
	storage_location character varying(255),
	experimenter_id integer REFERENCES experimenter(experimenter_id) ON DELETE RESTRICT
);

ALTER TABLE specimen
	ADD CONSTRAINT specimen_extract_type_id_fkey FOREIGN KEY (extract_type_id)
	REFERENCES cvterm(cvterm_id) ON DELETE RESTRICT;

ALTER TABLE specimen
	ADD CONSTRAINT specimen_tissue_type_id_fkey FOREIGN KEY (tissue_type_id)
	REFERENCES cvterm(cvterm_id) ON DELETE RESTRICT;

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
	specimen_id integer NOT NULL REFERENCES specimen(specimen_id) ON DELETE RESTRICT,
	gtassay_id integer NOT NULL REFERENCES gtassay(gtassay_id) ON DELETE RESTRICT,
	genotype_id integer,
	experimenter_id integer REFERENCES experimenter(experimenter_id) ON DELETE RESTRICT,
	experiment_date date,
	genotype_published_id integer,
	notes character varying(255)
);

ALTER TABLE gtexperiment
	ADD CONSTRAINT gtexperiment_genotype_id_fkey FOREIGN KEY (genotype_id)
	REFERENCES genotype(genotype_id) ON DELETE RESTRICT;

ALTER TABLE gtexperiment
	ADD CONSTRAINT gtexperiment_genotype_published_fkey FOREIGN KEY (genotype_published_id)
	REFERENCES pub(pub_id) ON DELETE RESTRICT;

COMMENT ON TABLE gtexperiment IS 'Experiment to assign the genotype of an individual at a particular locus';
COMMENT ON COLUMN gtexperiment.specimen_id IS 'Specimen used in the experiment';
COMMENT ON COLUMN gtexperiment.gtassay_id IS 'Assay conditions used to assign genotype';
COMMENT ON COLUMN gtexperiment.genotype_id IS 'Genotypic score';
COMMENT ON COLUMN gtexperiment.genotype_published_id IS 'Journal where result was published';
COMMENT ON COLUMN gtexperiment.experimenter_id IS 'Person performing the experiment';
COMMENT ON COLUMN gtexperiment.experiment_date IS 'Date that the experiment was performed';
COMMENT ON COLUMN gtexperiment.notes IS 'Notes on score';

-- create table studyx

CREATE TABLE studyx (
	studyx_id serial PRIMARY KEY,
	studyx_name character varying(255) NOT NULL UNIQUE
);

-- create table gtexperiment_study

CREATE TABLE gtexperiment_study (
	gtexperiment_study_id serial PRIMARY KEY,
	gtexperiment_id integer NOT NULL 
		REFERENCES gtexperiment(gtexperiment_id)
		ON DELETE RESTRICT,
	studyx_id integer NOT NULL REFERENCES studyx(studyx_id)
		ON DELETE RESTRICT,
	UNIQUE (gtexperiment_id, studyx_id)
);

-- table image (link out to a file name)
--
-- Link to an external image file

CREATE TABLE image (
	image_id serial PRIMARY KEY,
	uri character varying(1024) NOT NULL UNIQUE
);

COMMENT ON TABLE image IS 'Link to an external image file';
COMMENT ON COLUMN image.uri IS 'URL or local file path to image';

-- table gtassay_test
--
-- Initial examination of a locus

CREATE TABLE gtassay_test (
	gtassay_test_id serial PRIMARY KEY,
	gtassay_id integer NOT NULL REFERENCES gtassay(gtassay_id) ON DELETE RESTRICT,
	expected_length smallint,
	intron boolean,
	test_species_id integer REFERENCES organism(organism_id) ON DELETE RESTRICT,
	outcome_success boolean,
	image_id integer REFERENCES image(image_id) ON DELETE RESTRICT
);

COMMENT ON TABLE gtassay_test IS 'Initial examination of a locus';
COMMENT ON COLUMN gtassay_test.gtassay_id IS 'Assay conditions used in test';
COMMENT ON COLUMN gtassay_test.expected_length IS 'Length of the expectant product';
COMMENT ON COLUMN gtassay_test.intron IS 'True if cDNA includes an intron, false if it does not';
COMMENT ON COLUMN gtassay_test.test_species_id IS 'Taxa for which optimization experiments were performed';
COMMENT ON COLUMN gtassay_test.outcome_success IS 'True if experiment generated strong product in expected size range, false if no product was generated, or NULL if a weak product or non-specific amplification was observed';
COMMENT ON COLUMN gtassay_test.image_id IS 'Digital image of the experimental results';

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

COMMENT ON TABLE ptassay IS 'Method of polymorphism detection';
COMMENT ON COLUMN ptassay.assay_name IS 'Reference name of the assay';

-- create table ptassay_cvterm

CREATE TABLE ptassay_cvterm (
	ptassay_cvterm_id serial PRIMARY KEY,
	ptassay_id integer NOT NULL REFERENCES ptassay(ptassay_id) ON DELETE RESTRICT,
	cvterm_id integer NOT NULL,
	rank integer,
	cvterm_type character varying(255),
	UNIQUE (ptassay_id, cvterm_id)
);

ALTER TABLE ptassay_cvterm
	ADD CONSTRAINT ptassay_cvterm_cvterm_id_fkey FOREIGN KEY (cvterm_id)
	REFERENCES cvterm(cvterm_id) ON DELETE RESTRICT;

-- table individual_phenotype
--
-- Experimental result or observation associating an individual with a
-- phenotype

CREATE TABLE individual_phenotype (
	individual_phenotype_id serial PRIMARY KEY,
	individual_id integer NOT NULL REFERENCES individual(individual_id)
		ON DELETE RESTRICT,
	ptassay_id integer REFERENCES ptassay(ptassay_id)
		ON DELETE RESTRICT,
	phenotype_id integer,
	experimenter_id integer REFERENCES experimenter(experimenter_id) ON DELETE RESTRICT,
	assay_date date,
	phenotype_published_id integer,
	notes character varying(255)
);

ALTER TABLE individual_phenotype
	ADD CONSTRAINT individual_phenotype_phenotype_id_fkey FOREIGN KEY (phenotype_id)
	REFERENCES phenotype(phenotype_id) ON DELETE RESTRICT;

ALTER TABLE individual_phenotype
	ADD CONSTRAINT individual_phenotype_phenotype_published_fkey
	FOREIGN KEY (phenotype_published_id)
	REFERENCES pub(pub_id) ON DELETE RESTRICT;

COMMENT ON TABLE individual_phenotype IS 'Experimental result or observation associating an individual with a phenotype';
COMMENT ON COLUMN individual_phenotype.individual_id IS 'Individual used in the experiment';
COMMENT ON COLUMN individual_phenotype.ptassay_id IS 'Assay conditions used to assign phenotype';
COMMENT ON COLUMN individual_phenotype.phenotype_id IS 'Phenotypic score';
COMMENT ON COLUMN individual_phenotype.experimenter_id IS 'Person performing the experiment';
COMMENT ON COLUMN individual_phenotype.assay_date IS 'Date that the experiment was performed';
COMMENT ON COLUMN individual_phenotype.phenotype_published_id IS 'Journal where result was published';
COMMENT ON COLUMN individual_phenotype.notes IS 'Notes on score';

-- create table individual_phenotype_study

CREATE TABLE individual_phenotype_study (
	individual_phenotype_study_id serial PRIMARY KEY,
	individual_phenotype_id integer NOT NULL 
		REFERENCES individual_phenotype(individual_phenotype_id)
		ON DELETE RESTRICT,
	studyx_id integer NOT NULL REFERENCES studyx(studyx_id)
		ON DELETE RESTRICT,
	UNIQUE (individual_phenotype_id, studyx_id)
);

-- table stock_phenotype
--
-- Experimental result or observation associating a stock with a
-- phenotype

CREATE TABLE stock_phenotype (
	stock_phenotype_id serial PRIMARY KEY,
	stock_id integer NOT NULL REFERENCES stock(stock_id)
		ON DELETE RESTRICT,
	ptassay_id integer REFERENCES ptassay(ptassay_id)
		ON DELETE RESTRICT,
	phenotype_id integer,
	experimenter_id integer REFERENCES experimenter(experimenter_id) ON DELETE RESTRICT,
	assay_date date,
	phenotype_published_id integer,
	notes character varying(255)
);

ALTER TABLE stock_phenotype
	ADD CONSTRAINT stock_phenotype_phenotype_id_fkey FOREIGN KEY (phenotype_id)
	REFERENCES phenotype(phenotype_id) ON DELETE RESTRICT;

ALTER TABLE stock_phenotype
	ADD CONSTRAINT stock_phenotype_phenotype_published_fkey
	FOREIGN KEY (phenotype_published_id)
	REFERENCES pub(pub_id) ON DELETE RESTRICT;

COMMENT ON TABLE stock_phenotype IS 'Experimental result or observation associating a stock with a phenotype';
COMMENT ON COLUMN stock_phenotype.stock_id IS 'Stock used in the experiment';
COMMENT ON COLUMN stock_phenotype.ptassay_id IS 'Assay conditions used to assign phenotype';
COMMENT ON COLUMN stock_phenotype.phenotype_id IS 'Phenotypic score';
COMMENT ON COLUMN stock_phenotype.experimenter_id IS 'Person performing the experiment';
COMMENT ON COLUMN stock_phenotype.assay_date IS 'Date that the experiment was performed';
COMMENT ON COLUMN stock_phenotype.phenotype_published_id IS 'Journal where result was published';
COMMENT ON COLUMN stock_phenotype.notes IS 'Notes on score';

-- create table stock_phenotype_study

CREATE TABLE stock_phenotype_study (
	stock_phenotype_study_id serial PRIMARY KEY,
	stock_phenotype_id integer NOT NULL 
		REFERENCES stock_phenotype(stock_phenotype_id)
		ON DELETE RESTRICT,
	studyx_id integer NOT NULL REFERENCES studyx(studyx_id)
		ON DELETE RESTRICT,
	UNIQUE (stock_phenotype_id, studyx_id)
);

-- table biotype_phenotype
--
-- Experimental result or observation associating a biotype with a
-- phenotype

CREATE TABLE biotype_phenotype (
	biotype_phenotype_id serial PRIMARY KEY,
	biotype_id integer NOT NULL REFERENCES biotype(biotype_id)
		ON DELETE RESTRICT,
	ptassay_id integer REFERENCES ptassay(ptassay_id)
		ON DELETE RESTRICT,
	phenotype_id integer,
	experimenter_id integer REFERENCES experimenter(experimenter_id) ON DELETE RESTRICT,
	assay_date date,
	phenotype_published_id integer,
	notes character varying(255)
);

ALTER TABLE biotype_phenotype
	ADD CONSTRAINT biotype_phenotype_phenotype_id_fkey FOREIGN KEY (phenotype_id)
	REFERENCES phenotype(phenotype_id) ON DELETE RESTRICT;

ALTER TABLE biotype_phenotype
	ADD CONSTRAINT biotype_phenotype_phenotype_published_fkey
	FOREIGN KEY (phenotype_published_id)
	REFERENCES pub(pub_id) ON DELETE RESTRICT;

COMMENT ON TABLE biotype_phenotype IS 'Experimental result or observation associating a biotype with a phenotype';
COMMENT ON COLUMN biotype_phenotype.biotype_id IS 'Biotype used in the experiment';
COMMENT ON COLUMN biotype_phenotype.ptassay_id IS 'Assay conditions used to assign phenotype';
COMMENT ON COLUMN biotype_phenotype.phenotype_id IS 'Phenotypic score';
COMMENT ON COLUMN biotype_phenotype.experimenter_id IS 'Person performing the experiment';
COMMENT ON COLUMN biotype_phenotype.assay_date IS 'Date that the experiment was performed';
COMMENT ON COLUMN biotype_phenotype.phenotype_published_id IS 'Journal where result was published';
COMMENT ON COLUMN biotype_phenotype.notes IS 'Notes on score';

-- create table biotype_phenotype_study

CREATE TABLE biotype_phenotype_study (
	biotype_phenotype_study_id serial PRIMARY KEY,
	biotype_phenotype_id integer NOT NULL 
		REFERENCES biotype_phenotype(biotype_phenotype_id)
		ON DELETE RESTRICT,
	studyx_id integer NOT NULL REFERENCES studyx(studyx_id)
		ON DELETE RESTRICT,
	UNIQUE (biotype_phenotype_id, studyx_id)
);

-- add foreign key constraints to table stock

ALTER TABLE stock
	ADD CONSTRAINT stock_biotype_fkey FOREIGN KEY (biotype_id)
	REFERENCES biotype(biotype_id) ON DELETE RESTRICT;

ALTER TABLE stock
	ADD CONSTRAINT stock_maternal_source_fkey FOREIGN KEY (maternal_source_id)
	REFERENCES biotype(biotype_id) ON DELETE RESTRICT;

ALTER TABLE stock
	ADD CONSTRAINT stock_paternal_source_fkey FOREIGN KEY (paternal_source_id)
	REFERENCES biotype(biotype_id) ON DELETE RESTRICT;

ALTER TABLE stock
	ADD CONSTRAINT stock_geographic_site_fkey FOREIGN KEY (geographic_site_id)
	REFERENCES geographic_site(geographic_site_id) ON DELETE RESTRICT;

ALTER TABLE stock
	ADD CONSTRAINT stock_experimenter_fkey FOREIGN KEY (experimenter_id)
	REFERENCES experimenter(experimenter_id) ON DELETE RESTRICT;
