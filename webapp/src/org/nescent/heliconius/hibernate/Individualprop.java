package org.nescent.heliconius.hibernate;



/**
 * Individualprop generated by MyEclipse - Hibernate Tools
 */

public class Individualprop  implements java.io.Serializable {


    // Fields    

     private Integer individualpropId;
     private Cvterm cvterm;
     private Individual individual;
     private String value;
     private Integer rank;


    // Constructors

    /** default constructor */
    public Individualprop() {
    }

	/** minimal constructor */
    public Individualprop(Cvterm cvterm, Individual individual, Integer rank) {
        this.cvterm = cvterm;
        this.individual = individual;
        this.rank = rank;
    }
    
    /** full constructor */
    public Individualprop(Cvterm cvterm, Individual individual, String value, Integer rank) {
        this.cvterm = cvterm;
        this.individual = individual;
        this.value = value;
        this.rank = rank;
    }

   
    // Property accessors

    public Integer getIndividualpropId() {
        return this.individualpropId;
    }
    
    public void setIndividualpropId(Integer individualpropId) {
        this.individualpropId = individualpropId;
    }

    public Cvterm getCvterm() {
        return this.cvterm;
    }
    
    public void setCvterm(Cvterm cvterm) {
        this.cvterm = cvterm;
    }

    public Individual getIndividual() {
        return this.individual;
    }
    
    public void setIndividual(Individual individual) {
        this.individual = individual;
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