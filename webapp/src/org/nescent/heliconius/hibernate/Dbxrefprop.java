package org.nescent.heliconius.hibernate;



/**
 * Dbxrefprop generated by MyEclipse - Hibernate Tools
 */

public class Dbxrefprop  implements java.io.Serializable {


    // Fields    

     private Integer dbxrefpropId;
     private Cvterm cvterm;
     private Dbxref dbxref;
     private String value;
     private Integer rank;


    // Constructors

    /** default constructor */
    public Dbxrefprop() {
    }

    
    /** full constructor */
    public Dbxrefprop(Cvterm cvterm, Dbxref dbxref, String value, Integer rank) {
        this.cvterm = cvterm;
        this.dbxref = dbxref;
        this.value = value;
        this.rank = rank;
    }

   
    // Property accessors

    public Integer getDbxrefpropId() {
        return this.dbxrefpropId;
    }
    
    public void setDbxrefpropId(Integer dbxrefpropId) {
        this.dbxrefpropId = dbxrefpropId;
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
   








}