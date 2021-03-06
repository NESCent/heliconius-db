package org.nescent.heliconius.hibernate;

import java.util.HashSet;
import java.util.Set;


/**
 * Specimen generated by MyEclipse - Hibernate Tools
 */

public class Specimen  implements java.io.Serializable {


    // Fields    

     private Integer specimenId;
     private Individual individual;
     private Cvterm cvtermByTissueTypeId;
     private Contact contact;
     private Cvterm cvtermByExtractTypeId;
     private String name;
     private String description;
     private String identifier;
     private String storageLocation;
     private Set specimenImages = new HashSet(0);
     private Set gtexperiments = new HashSet(0);


    // Constructors

    /** default constructor */
    public Specimen() {
    }

	/** minimal constructor */
    public Specimen(Individual individual, Cvterm cvtermByTissueTypeId, Cvterm cvtermByExtractTypeId, String name) {
        this.individual = individual;
        this.cvtermByTissueTypeId = cvtermByTissueTypeId;
        this.cvtermByExtractTypeId = cvtermByExtractTypeId;
        this.name = name;
    }
    
    /** full constructor */
    public Specimen(Individual individual, Cvterm cvtermByTissueTypeId, Contact contact, Cvterm cvtermByExtractTypeId, String name, String description, String identifier, String storageLocation, Set specimenImages, Set gtexperiments) {
        this.individual = individual;
        this.cvtermByTissueTypeId = cvtermByTissueTypeId;
        this.contact = contact;
        this.cvtermByExtractTypeId = cvtermByExtractTypeId;
        this.name = name;
        this.description = description;
        this.identifier = identifier;
        this.storageLocation = storageLocation;
        this.specimenImages = specimenImages;
        this.gtexperiments = gtexperiments;
    }

   
    // Property accessors

    public Integer getSpecimenId() {
        return this.specimenId;
    }
    
    public void setSpecimenId(Integer specimenId) {
        this.specimenId = specimenId;
    }

    public Individual getIndividual() {
        return this.individual;
    }
    
    public void setIndividual(Individual individual) {
        this.individual = individual;
    }

    public Cvterm getCvtermByTissueTypeId() {
        return this.cvtermByTissueTypeId;
    }
    
    public void setCvtermByTissueTypeId(Cvterm cvtermByTissueTypeId) {
        this.cvtermByTissueTypeId = cvtermByTissueTypeId;
    }

    public Contact getContact() {
        return this.contact;
    }
    
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Cvterm getCvtermByExtractTypeId() {
        return this.cvtermByExtractTypeId;
    }
    
    public void setCvtermByExtractTypeId(Cvterm cvtermByExtractTypeId) {
        this.cvtermByExtractTypeId = cvtermByExtractTypeId;
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

    public String getIdentifier() {
        return this.identifier;
    }
    
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getStorageLocation() {
        return this.storageLocation;
    }
    
    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    public Set getSpecimenImages() {
        return this.specimenImages;
    }
    
    public void setSpecimenImages(Set specimenImages) {
        this.specimenImages = specimenImages;
    }

    public Set getGtexperiments() {
        return this.gtexperiments;
    }
    
    public void setGtexperiments(Set gtexperiments) {
        this.gtexperiments = gtexperiments;
    }
   








}