package org.nescent.heliconius.hibernate;



/**
 * FeatureCvtermPub generated by MyEclipse - Hibernate Tools
 */

public class FeatureCvtermPub  implements java.io.Serializable {


    // Fields    

     private Integer featureCvtermPubId;
     private Pub pub;
     private FeatureCvterm featureCvterm;


    // Constructors

    /** default constructor */
    public FeatureCvtermPub() {
    }

    
    /** full constructor */
    public FeatureCvtermPub(Pub pub, FeatureCvterm featureCvterm) {
        this.pub = pub;
        this.featureCvterm = featureCvterm;
    }

   
    // Property accessors

    public Integer getFeatureCvtermPubId() {
        return this.featureCvtermPubId;
    }
    
    public void setFeatureCvtermPubId(Integer featureCvtermPubId) {
        this.featureCvtermPubId = featureCvtermPubId;
    }

    public Pub getPub() {
        return this.pub;
    }
    
    public void setPub(Pub pub) {
        this.pub = pub;
    }

    public FeatureCvterm getFeatureCvterm() {
        return this.featureCvterm;
    }
    
    public void setFeatureCvterm(FeatureCvterm featureCvterm) {
        this.featureCvterm = featureCvterm;
    }
   








}