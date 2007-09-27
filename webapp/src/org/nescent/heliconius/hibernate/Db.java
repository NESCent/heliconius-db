package org.nescent.heliconius.hibernate;

import java.util.HashSet;
import java.util.Set;


/**
 * Db generated by MyEclipse - Hibernate Tools
 */

public class Db  implements java.io.Serializable {


    // Fields    

     private Integer dbId;
     private String name;
     private String description;
     private String urlprefix;
     private String url;
     private Set dbxrefs = new HashSet(0);


    // Constructors

    /** default constructor */
    public Db() {
    }

	/** minimal constructor */
    public Db(String name) {
        this.name = name;
    }
    
    /** full constructor */
    public Db(String name, String description, String urlprefix, String url, Set dbxrefs) {
        this.name = name;
        this.description = description;
        this.urlprefix = urlprefix;
        this.url = url;
        this.dbxrefs = dbxrefs;
    }

   
    // Property accessors

    public Integer getDbId() {
        return this.dbId;
    }
    
    public void setDbId(Integer dbId) {
        this.dbId = dbId;
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

    public String getUrlprefix() {
        return this.urlprefix;
    }
    
    public void setUrlprefix(String urlprefix) {
        this.urlprefix = urlprefix;
    }

    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public Set getDbxrefs() {
        return this.dbxrefs;
    }
    
    public void setDbxrefs(Set dbxrefs) {
        this.dbxrefs = dbxrefs;
    }
   








}