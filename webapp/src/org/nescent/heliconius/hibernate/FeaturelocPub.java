package org.nescent.heliconius.hibernate;



/**
 * FeaturelocPub generated by MyEclipse - Hibernate Tools
 */

public class FeaturelocPub  implements java.io.Serializable {


    // Fields    

     private Integer featurelocPubId;
     private Featureloc featureloc;
     private Pub pub;


    // Constructors

    /** default constructor */
    public FeaturelocPub() {
    }

    
    /** full constructor */
    public FeaturelocPub(Featureloc featureloc, Pub pub) {
        this.featureloc = featureloc;
        this.pub = pub;
    }

   
    // Property accessors

    public Integer getFeaturelocPubId() {
        return this.featurelocPubId;
    }
    
    public void setFeaturelocPubId(Integer featurelocPubId) {
        this.featurelocPubId = featurelocPubId;
    }

    public Featureloc getFeatureloc() {
        return this.featureloc;
    }
    
    public void setFeatureloc(Featureloc featureloc) {
        this.featureloc = featureloc;
    }

    public Pub getPub() {
        return this.pub;
    }
    
    public void setPub(Pub pub) {
        this.pub = pub;
    }
   








}