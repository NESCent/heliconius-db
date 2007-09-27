package org.nescent.heliconius.hibernate;

import java.util.HashSet;
import java.util.Set;


/**
 * Contact generated by MyEclipse - Hibernate Tools
 */

public class Contact  implements java.io.Serializable {


    // Fields    

     private Integer contactId;
     private Cvterm cvterm;
     private String name;
     private String description;
     private Set stockPhenotypes = new HashSet(0);
     private Set specimens = new HashSet(0);
     private Set biotypePhenotypes = new HashSet(0);
     private Set crossexperiments = new HashSet(0);
     private Set gtexperiments = new HashSet(0);
     private Set contactRelationshipsForSubjectId = new HashSet(0);
     private Set stocks = new HashSet(0);
     private Set individualPhenotypes = new HashSet(0);
     private Set individuals = new HashSet(0);
     private Set contactRelationshipsForObjectId = new HashSet(0);


    // Constructors

    /** default constructor */
    public Contact() {
    }

	/** minimal constructor */
    public Contact(String name) {
        this.name = name;
    }
    
    /** full constructor */
    public Contact(Cvterm cvterm, String name, String description, Set stockPhenotypes, Set specimens, Set biotypePhenotypes, Set crossexperiments, Set gtexperiments, Set contactRelationshipsForSubjectId, Set stocks, Set individualPhenotypes, Set individuals, Set contactRelationshipsForObjectId) {
        this.cvterm = cvterm;
        this.name = name;
        this.description = description;
        this.stockPhenotypes = stockPhenotypes;
        this.specimens = specimens;
        this.biotypePhenotypes = biotypePhenotypes;
        this.crossexperiments = crossexperiments;
        this.gtexperiments = gtexperiments;
        this.contactRelationshipsForSubjectId = contactRelationshipsForSubjectId;
        this.stocks = stocks;
        this.individualPhenotypes = individualPhenotypes;
        this.individuals = individuals;
        this.contactRelationshipsForObjectId = contactRelationshipsForObjectId;
    }

   
    // Property accessors

    public Integer getContactId() {
        return this.contactId;
    }
    
    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public Cvterm getCvterm() {
        return this.cvterm;
    }
    
    public void setCvterm(Cvterm cvterm) {
        this.cvterm = cvterm;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public Set getStockPhenotypes() {
        return this.stockPhenotypes;
    }
    
    public void setStockPhenotypes(Set stockPhenotypes) {
        this.stockPhenotypes = stockPhenotypes;
    }

    public Set getSpecimens() {
        return this.specimens;
    }
    
    public void setSpecimens(Set specimens) {
        this.specimens = specimens;
    }

    public Set getBiotypePhenotypes() {
        return this.biotypePhenotypes;
    }
    
    public void setBiotypePhenotypes(Set biotypePhenotypes) {
        this.biotypePhenotypes = biotypePhenotypes;
    }

    public Set getCrossexperiments() {
        return this.crossexperiments;
    }
    
    public void setCrossexperiments(Set crossexperiments) {
        this.crossexperiments = crossexperiments;
    }

    public Set getGtexperiments() {
        return this.gtexperiments;
    }
    
    public void setGtexperiments(Set gtexperiments) {
        this.gtexperiments = gtexperiments;
    }

    public Set getContactRelationshipsForSubjectId() {
        return this.contactRelationshipsForSubjectId;
    }
    
    public void setContactRelationshipsForSubjectId(Set contactRelationshipsForSubjectId) {
        this.contactRelationshipsForSubjectId = contactRelationshipsForSubjectId;
    }

    public Set getStocks() {
        return this.stocks;
    }
    
    public void setStocks(Set stocks) {
        this.stocks = stocks;
    }

    public Set getIndividualPhenotypes() {
        return this.individualPhenotypes;
    }
    
    public void setIndividualPhenotypes(Set individualPhenotypes) {
        this.individualPhenotypes = individualPhenotypes;
    }

    public Set getIndividuals() {
        return this.individuals;
    }
    
    public void setIndividuals(Set individuals) {
        this.individuals = individuals;
    }

    public Set getContactRelationshipsForObjectId() {
        return this.contactRelationshipsForObjectId;
    }
    
    public void setContactRelationshipsForObjectId(Set contactRelationshipsForObjectId) {
        this.contactRelationshipsForObjectId = contactRelationshipsForObjectId;
    }
   








}