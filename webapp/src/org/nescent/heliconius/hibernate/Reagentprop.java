package org.nescent.heliconius.hibernate;



/**
 * Reagentprop generated by MyEclipse - Hibernate Tools
 */

public class Reagentprop  implements java.io.Serializable {


    // Fields    

     private Integer reagentpropId;
     private Cvterm cvterm;
     private Reagent reagent;
     private String value;
     private Integer rank;


    // Constructors

    /** default constructor */
    public Reagentprop() {
    }

	/** minimal constructor */
    public Reagentprop(Cvterm cvterm, Reagent reagent, Integer rank) {
        this.cvterm = cvterm;
        this.reagent = reagent;
        this.rank = rank;
    }
    
    /** full constructor */
    public Reagentprop(Cvterm cvterm, Reagent reagent, String value, Integer rank) {
        this.cvterm = cvterm;
        this.reagent = reagent;
        this.value = value;
        this.rank = rank;
    }

   
    // Property accessors

    public Integer getReagentpropId() {
        return this.reagentpropId;
    }
    
    public void setReagentpropId(Integer reagentpropId) {
        this.reagentpropId = reagentpropId;
    }

    public Cvterm getCvterm() {
        return this.cvterm;
    }
    
    public void setCvterm(Cvterm cvterm) {
        this.cvterm = cvterm;
    }

    public Reagent getReagent() {
        return this.reagent;
    }
    
    public void setReagent(Reagent reagent) {
        this.reagent = reagent;
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