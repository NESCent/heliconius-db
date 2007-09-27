package org.nescent.heliconius.hibernate;



/**
 * BiotypeOrganism generated by MyEclipse - Hibernate Tools
 */

public class BiotypeOrganism  implements java.io.Serializable {


    // Fields    

     private Integer biotypeOrganismId;
     private Organism organism;
     private Biotype biotype;


    // Constructors

    /** default constructor */
    public BiotypeOrganism() {
    }

    
    /** full constructor */
    public BiotypeOrganism(Organism organism, Biotype biotype) {
        this.organism = organism;
        this.biotype = biotype;
    }

   
    // Property accessors

    public Integer getBiotypeOrganismId() {
        return this.biotypeOrganismId;
    }
    
    public void setBiotypeOrganismId(Integer biotypeOrganismId) {
        this.biotypeOrganismId = biotypeOrganismId;
    }

    public Organism getOrganism() {
        return this.organism;
    }
    
    public void setOrganism(Organism organism) {
        this.organism = organism;
    }

    public Biotype getBiotype() {
        return this.biotype;
    }
    
    public void setBiotype(Biotype biotype) {
        this.biotype = biotype;
    }
   








}