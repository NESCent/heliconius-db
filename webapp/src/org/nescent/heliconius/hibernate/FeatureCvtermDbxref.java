package org.nescent.heliconius.hibernate;



/**
 * FeatureCvtermDbxref generated by MyEclipse - Hibernate Tools
 */

public class FeatureCvtermDbxref  implements java.io.Serializable {


    // Fields    

     private Integer featureCvtermDbxrefId;
     private Dbxref dbxref;
     private FeatureCvterm featureCvterm;


    // Constructors

    /** default constructor */
    public FeatureCvtermDbxref() {
    }

    
    /** full constructor */
    public FeatureCvtermDbxref(Dbxref dbxref, FeatureCvterm featureCvterm) {
        this.dbxref = dbxref;
        this.featureCvterm = featureCvterm;
    }

   
    // Property accessors

    public Integer getFeatureCvtermDbxrefId() {
        return this.featureCvtermDbxrefId;
    }
    
    public void setFeatureCvtermDbxrefId(Integer featureCvtermDbxrefId) {
        this.featureCvtermDbxrefId = featureCvtermDbxrefId;
    }

    public Dbxref getDbxref() {
        return this.dbxref;
    }
    
    public void setDbxref(Dbxref dbxref) {
        this.dbxref = dbxref;
    }

    public FeatureCvterm getFeatureCvterm() {
        return this.featureCvterm;
    }
    
    public void setFeatureCvterm(FeatureCvterm featureCvterm) {
        this.featureCvterm = featureCvterm;
    }
   








}