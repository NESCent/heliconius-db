package org.nescent.heliconius.hibernate;

import java.util.HashSet;
import java.util.Set;


/**
 * Project generated by MyEclipse - Hibernate Tools
 */

public class Project  implements java.io.Serializable {


    // Fields    

     private Integer projectId;
     private String name;
     private String description;
     private Set individualPhenotypeProjects = new HashSet(0);
     private Set stockPhenotypeProjects = new HashSet(0);
     private Set biotypePhenotypeProjects = new HashSet(0);
     private Set gtexperimentProjects = new HashSet(0);


    // Constructors

    /** default constructor */
    public Project() {
    }

	/** minimal constructor */
    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    /** full constructor */
    public Project(String name, String description, Set individualPhenotypeProjects, Set stockPhenotypeProjects, Set biotypePhenotypeProjects, Set gtexperimentProjects) {
        this.name = name;
        this.description = description;
        this.individualPhenotypeProjects = individualPhenotypeProjects;
        this.stockPhenotypeProjects = stockPhenotypeProjects;
        this.biotypePhenotypeProjects = biotypePhenotypeProjects;
        this.gtexperimentProjects = gtexperimentProjects;
    }

   
    // Property accessors

    public Integer getProjectId() {
        return this.projectId;
    }
    
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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

    public Set getIndividualPhenotypeProjects() {
        return this.individualPhenotypeProjects;
    }
    
    public void setIndividualPhenotypeProjects(Set individualPhenotypeProjects) {
        this.individualPhenotypeProjects = individualPhenotypeProjects;
    }

    public Set getStockPhenotypeProjects() {
        return this.stockPhenotypeProjects;
    }
    
    public void setStockPhenotypeProjects(Set stockPhenotypeProjects) {
        this.stockPhenotypeProjects = stockPhenotypeProjects;
    }

    public Set getBiotypePhenotypeProjects() {
        return this.biotypePhenotypeProjects;
    }
    
    public void setBiotypePhenotypeProjects(Set biotypePhenotypeProjects) {
        this.biotypePhenotypeProjects = biotypePhenotypeProjects;
    }

    public Set getGtexperimentProjects() {
        return this.gtexperimentProjects;
    }
    
    public void setGtexperimentProjects(Set gtexperimentProjects) {
        this.gtexperimentProjects = gtexperimentProjects;
    }
   








}