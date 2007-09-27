package org.nescent.heliconius.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * Feature generated by MyEclipse - Hibernate Tools
 */

public class Feature  implements java.io.Serializable {


    // Fields    

     private Integer featureId;
     private Organism organism;
     private Cvterm cvterm;
     private Dbxref dbxref;
     private String name;
     private String uniquename;
     private String residues;
     private Integer seqlen;
     private String md5checksum;
     private Byte isAnalysis;
     private Byte isObsolete;
     private Date timeaccessioned;
     private Date timelastmodified;
     private Set featureDbxrefs = new HashSet(0);
     private Set featurelocsForFeatureId = new HashSet(0);
     private Set featureGenotypesForChromosomeId = new HashSet(0);
     private Set featureGenotypesForFeatureId = new HashSet(0);
     private Set featurePubs = new HashSet(0);
     private Set gtassaies = new HashSet(0);
     private Set reagents = new HashSet(0);
     private Set featurePhenotypes = new HashSet(0);
     private Set featurelocsForSrcfeatureId = new HashSet(0);
     private Set featureRelationshipsForSubjectId = new HashSet(0);
     private Set featureRelationshipsForObjectId = new HashSet(0);
     private Set featureCvterms = new HashSet(0);
     private Set featureprops = new HashSet(0);
     private Set featureSynonyms = new HashSet(0);


    // Constructors

    /** default constructor */
    public Feature() {
    }

	/** minimal constructor */
    public Feature(Organism organism, Cvterm cvterm, String uniquename, Byte isAnalysis, Byte isObsolete, Date timeaccessioned, Date timelastmodified) {
        this.organism = organism;
        this.cvterm = cvterm;
        this.uniquename = uniquename;
        this.isAnalysis = isAnalysis;
        this.isObsolete = isObsolete;
        this.timeaccessioned = timeaccessioned;
        this.timelastmodified = timelastmodified;
    }
    
    /** full constructor */
    public Feature(Organism organism, Cvterm cvterm, Dbxref dbxref, String name, String uniquename, String residues, Integer seqlen, String md5checksum, Byte isAnalysis, Byte isObsolete, Date timeaccessioned, Date timelastmodified, Set featureDbxrefs, Set featurelocsForFeatureId, Set featureGenotypesForChromosomeId, Set featureGenotypesForFeatureId, Set featurePubs, Set gtassaies, Set reagents, Set featurePhenotypes, Set featurelocsForSrcfeatureId, Set featureRelationshipsForSubjectId, Set featureRelationshipsForObjectId, Set featureCvterms, Set featureprops, Set featureSynonyms) {
        this.organism = organism;
        this.cvterm = cvterm;
        this.dbxref = dbxref;
        this.name = name;
        this.uniquename = uniquename;
        this.residues = residues;
        this.seqlen = seqlen;
        this.md5checksum = md5checksum;
        this.isAnalysis = isAnalysis;
        this.isObsolete = isObsolete;
        this.timeaccessioned = timeaccessioned;
        this.timelastmodified = timelastmodified;
        this.featureDbxrefs = featureDbxrefs;
        this.featurelocsForFeatureId = featurelocsForFeatureId;
        this.featureGenotypesForChromosomeId = featureGenotypesForChromosomeId;
        this.featureGenotypesForFeatureId = featureGenotypesForFeatureId;
        this.featurePubs = featurePubs;
        this.gtassaies = gtassaies;
        this.reagents = reagents;
        this.featurePhenotypes = featurePhenotypes;
        this.featurelocsForSrcfeatureId = featurelocsForSrcfeatureId;
        this.featureRelationshipsForSubjectId = featureRelationshipsForSubjectId;
        this.featureRelationshipsForObjectId = featureRelationshipsForObjectId;
        this.featureCvterms = featureCvterms;
        this.featureprops = featureprops;
        this.featureSynonyms = featureSynonyms;
    }

   
    // Property accessors

    public Integer getFeatureId() {
        return this.featureId;
    }
    
    public void setFeatureId(Integer featureId) {
        this.featureId = featureId;
    }

    public Organism getOrganism() {
        return this.organism;
    }
    
    public void setOrganism(Organism organism) {
        this.organism = organism;
    }

    public Cvterm getCvterm() {
        return this.cvterm;
    }
    
    public void setCvterm(Cvterm cvterm) {
        this.cvterm = cvterm;
    }

    public Dbxref getDbxref() {
        return this.dbxref;
    }
    
    public void setDbxref(Dbxref dbxref) {
        this.dbxref = dbxref;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getUniquename() {
        return this.uniquename;
    }
    
    public void setUniquename(String uniquename) {
        this.uniquename = uniquename;
    }

    public String getResidues() {
        return this.residues;
    }
    
    public void setResidues(String residues) {
        this.residues = residues;
    }

    public Integer getSeqlen() {
        return this.seqlen;
    }
    
    public void setSeqlen(Integer seqlen) {
        this.seqlen = seqlen;
    }

    public String getMd5checksum() {
        return this.md5checksum;
    }
    
    public void setMd5checksum(String md5checksum) {
        this.md5checksum = md5checksum;
    }

    public Byte getIsAnalysis() {
        return this.isAnalysis;
    }
    
    public void setIsAnalysis(Byte isAnalysis) {
        this.isAnalysis = isAnalysis;
    }

    public Byte getIsObsolete() {
        return this.isObsolete;
    }
    
    public void setIsObsolete(Byte isObsolete) {
        this.isObsolete = isObsolete;
    }

    public Date getTimeaccessioned() {
        return this.timeaccessioned;
    }
    
    public void setTimeaccessioned(Date timeaccessioned) {
        this.timeaccessioned = timeaccessioned;
    }

    public Date getTimelastmodified() {
        return this.timelastmodified;
    }
    
    public void setTimelastmodified(Date timelastmodified) {
        this.timelastmodified = timelastmodified;
    }

    public Set getFeatureDbxrefs() {
        return this.featureDbxrefs;
    }
    
    public void setFeatureDbxrefs(Set featureDbxrefs) {
        this.featureDbxrefs = featureDbxrefs;
    }

    public Set getFeaturelocsForFeatureId() {
        return this.featurelocsForFeatureId;
    }
    
    public void setFeaturelocsForFeatureId(Set featurelocsForFeatureId) {
        this.featurelocsForFeatureId = featurelocsForFeatureId;
    }

    public Set getFeatureGenotypesForChromosomeId() {
        return this.featureGenotypesForChromosomeId;
    }
    
    public void setFeatureGenotypesForChromosomeId(Set featureGenotypesForChromosomeId) {
        this.featureGenotypesForChromosomeId = featureGenotypesForChromosomeId;
    }

    public Set getFeatureGenotypesForFeatureId() {
        return this.featureGenotypesForFeatureId;
    }
    
    public void setFeatureGenotypesForFeatureId(Set featureGenotypesForFeatureId) {
        this.featureGenotypesForFeatureId = featureGenotypesForFeatureId;
    }

    public Set getFeaturePubs() {
        return this.featurePubs;
    }
    
    public void setFeaturePubs(Set featurePubs) {
        this.featurePubs = featurePubs;
    }

    public Set getGtassaies() {
        return this.gtassaies;
    }
    
    public void setGtassaies(Set gtassaies) {
        this.gtassaies = gtassaies;
    }

    public Set getReagents() {
        return this.reagents;
    }
    
    public void setReagents(Set reagents) {
        this.reagents = reagents;
    }

    public Set getFeaturePhenotypes() {
        return this.featurePhenotypes;
    }
    
    public void setFeaturePhenotypes(Set featurePhenotypes) {
        this.featurePhenotypes = featurePhenotypes;
    }

    public Set getFeaturelocsForSrcfeatureId() {
        return this.featurelocsForSrcfeatureId;
    }
    
    public void setFeaturelocsForSrcfeatureId(Set featurelocsForSrcfeatureId) {
        this.featurelocsForSrcfeatureId = featurelocsForSrcfeatureId;
    }

    public Set getFeatureRelationshipsForSubjectId() {
        return this.featureRelationshipsForSubjectId;
    }
    
    public void setFeatureRelationshipsForSubjectId(Set featureRelationshipsForSubjectId) {
        this.featureRelationshipsForSubjectId = featureRelationshipsForSubjectId;
    }

    public Set getFeatureRelationshipsForObjectId() {
        return this.featureRelationshipsForObjectId;
    }
    
    public void setFeatureRelationshipsForObjectId(Set featureRelationshipsForObjectId) {
        this.featureRelationshipsForObjectId = featureRelationshipsForObjectId;
    }

    public Set getFeatureCvterms() {
        return this.featureCvterms;
    }
    
    public void setFeatureCvterms(Set featureCvterms) {
        this.featureCvterms = featureCvterms;
    }

    public Set getFeatureprops() {
        return this.featureprops;
    }
    
    public void setFeatureprops(Set featureprops) {
        this.featureprops = featureprops;
    }

    public Set getFeatureSynonyms() {
        return this.featureSynonyms;
    }
    
    public void setFeatureSynonyms(Set featureSynonyms) {
        this.featureSynonyms = featureSynonyms;
    }
   








}