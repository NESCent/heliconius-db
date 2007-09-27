package org.nescent.heliconius.hibernate;



/**
 * Ptassayprop generated by MyEclipse - Hibernate Tools
 */

public class Ptassayprop  implements java.io.Serializable {


    // Fields    

     private Integer ptassaypropId;
     private Cvterm cvterm;
     private Ptassay ptassay;
     private String value;
     private Integer rank;


    // Constructors

    /** default constructor */
    public Ptassayprop() {
    }

	/** minimal constructor */
    public Ptassayprop(Cvterm cvterm, Ptassay ptassay, Integer rank) {
        this.cvterm = cvterm;
        this.ptassay = ptassay;
        this.rank = rank;
    }
    
    /** full constructor */
    public Ptassayprop(Cvterm cvterm, Ptassay ptassay, String value, Integer rank) {
        this.cvterm = cvterm;
        this.ptassay = ptassay;
        this.value = value;
        this.rank = rank;
    }

   
    // Property accessors

    public Integer getPtassaypropId() {
        return this.ptassaypropId;
    }
    
    public void setPtassaypropId(Integer ptassaypropId) {
        this.ptassaypropId = ptassaypropId;
    }

    public Cvterm getCvterm() {
        return this.cvterm;
    }
    
    public void setCvterm(Cvterm cvterm) {
        this.cvterm = cvterm;
    }

    public Ptassay getPtassay() {
        return this.ptassay;
    }
    
    public void setPtassay(Ptassay ptassay) {
        this.ptassay = ptassay;
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