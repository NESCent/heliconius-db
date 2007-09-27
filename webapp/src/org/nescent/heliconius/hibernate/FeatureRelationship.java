package org.nescent.heliconius.hibernate;

import java.util.HashSet;
import java.util.Set;


/**
 * FeatureRelationship generated by MyEclipse - Hibernate Tools
 */

public class FeatureRelationship  implements java.io.Serializable {


    // Fields    

     private Integer featureRelationshipId;
     private Feature featureBySubjectId;
     private Feature featureByObjectId;
     private Cvterm cvterm;
     private String value;
     private Integer rank;
     private Set featureRelationshipprops = new HashSet(0);
     private Set featureRelationshipPubs = new HashSet(0);


    // Constructors

    /** default constructor */
    public FeatureRelationship() {
    }

	/** minimal constructor */
    public FeatureRelationship(Feature featureBySubjectId, Feature featureByObjectId, Cvterm cvterm, Integer rank) {
        this.featureBySubjectId = featureBySubjectId;
        this.featureByObjectId = featureByObjectId;
        this.cvterm = cvterm;
        this.rank = rank;
    }
    
    /** full constructor */
    public FeatureRelationship(Feature featureBySubjectId, Feature featureByObjectId, Cvterm cvterm, String value, Integer rank, Set featureRelationshipprops, Set featureRelationshipPubs) {
        this.featureBySubjectId = featureBySubjectId;
        this.featureByObjectId = featureByObjectId;
        this.cvterm = cvterm;
        this.value = value;
        this.rank = rank;
        this.featureRelationshipprops = featureRelationshipprops;
        this.featureRelationshipPubs = featureRelationshipPubs;
    }

   
    // Property accessors

    public Integer getFeatureRelationshipId() {
        return this.featureRelationshipId;
    }
    
    public void setFeatureRelationshipId(Integer featureRelationshipId) {
        this.featureRelationshipId = featureRelationshipId;
    }

    public Feature getFeatureBySubjectId() {
        return this.featureBySubjectId;
    }
    
    public void setFeatureBySubjectId(Feature featureBySubjectId) {
        this.featureBySubjectId = featureBySubjectId;
    }

    public Feature getFeatureByObjectId() {
        return this.featureByObjectId;
    }
    
    public void setFeatureByObjectId(Feature featureByObjectId) {
        this.featureByObjectId = featureByObjectId;
    }

    public Cvterm getCvterm() {
        return this.cvterm;
    }
    
    public void setCvterm(Cvterm cvterm) {
        this.cvterm = cvterm;
    }

    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }

    public Integer getRank() {
        return this.rank;
    }
    
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Set getFeatureRelationshipprops() {
        return this.featureRelationshipprops;
    }
    
    public void setFeatureRelationshipprops(Set featureRelationshipprops) {
        this.featureRelationshipprops = featureRelationshipprops;
    }

    public Set getFeatureRelationshipPubs() {
        return this.featureRelationshipPubs;
    }
    
    public void setFeatureRelationshipPubs(Set featureRelationshipPubs) {
        this.featureRelationshipPubs = featureRelationshipPubs;
    }
   








}