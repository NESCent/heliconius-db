package org.nescent.heliconius.hibernate;

import java.util.HashSet;
import java.util.Set;


/**
 * FeaturePub generated by MyEclipse - Hibernate Tools
 */

public class FeaturePub  implements java.io.Serializable {


    // Fields    

     private Integer featurePubId;
     private Feature feature;
     private Pub pub;
     private Set featurePubprops = new HashSet(0);


    // Constructors

    /** default constructor */
    public FeaturePub() {
    }

	/** minimal constructor */
    public FeaturePub(Feature feature, Pub pub) {
        this.feature = feature;
        this.pub = pub;
    }
    
    /** full constructor */
    public FeaturePub(Feature feature, Pub pub, Set featurePubprops) {
        this.feature = feature;
        this.pub = pub;
        this.featurePubprops = featurePubprops;
    }

   
    // Property accessors

    public Integer getFeaturePubId() {
        return this.featurePubId;
    }
    
    public void setFeaturePubId(Integer featurePubId) {
        this.featurePubId = featurePubId;
    }

    public Feature getFeature() {
        return this.feature;
    }
    
    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public Pub getPub() {
        return this.pub;
    }
    
    public void setPub(Pub pub) {
        this.pub = pub;
    }

    public Set getFeaturePubprops() {
        return this.featurePubprops;
    }
    
    public void setFeaturePubprops(Set featurePubprops) {
        this.featurePubprops = featurePubprops;
    }
   








}