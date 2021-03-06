package org.nescent.heliconius.hibernate;



/**
 * CvtermDbxref generated by MyEclipse - Hibernate Tools
 */

public class CvtermDbxref  implements java.io.Serializable {


    // Fields    

     private Integer cvtermDbxrefId;
     private Cvterm cvterm;
     private Dbxref dbxref;
     private Integer isForDefinition;


    // Constructors

    /** default constructor */
    public CvtermDbxref() {
    }

    
    /** full constructor */
    public CvtermDbxref(Cvterm cvterm, Dbxref dbxref, Integer isForDefinition) {
        this.cvterm = cvterm;
        this.dbxref = dbxref;
        this.isForDefinition = isForDefinition;
    }

   
    // Property accessors

    public Integer getCvtermDbxrefId() {
        return this.cvtermDbxrefId;
    }
    
    public void setCvtermDbxrefId(Integer cvtermDbxrefId) {
        this.cvtermDbxrefId = cvtermDbxrefId;
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

    public Integer getIsForDefinition() {
        return this.isForDefinition;
    }
    
    public void setIsForDefinition(Integer isForDefinition) {
        this.isForDefinition = isForDefinition;
    }
   








}