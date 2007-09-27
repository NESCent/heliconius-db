package org.nescent.heliconius.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * Politlocation generated by MyEclipse - Hibernate Tools
 */

public class Politlocation  implements java.io.Serializable {


    // Fields    

     private Integer politlocationId;
     private Cvterm cvtermByCountyId;
     private Cvterm cvtermByCountryId;
     private Cvterm cvtermByProvinceId;
     private Date refdate;
     private String postalcode;
     private Set geolocations = new HashSet(0);


    // Constructors

    /** default constructor */
    public Politlocation() {
    }

    
    /** full constructor */
    public Politlocation(Cvterm cvtermByCountyId, Cvterm cvtermByCountryId, Cvterm cvtermByProvinceId, Date refdate, String postalcode, Set geolocations) {
        this.cvtermByCountyId = cvtermByCountyId;
        this.cvtermByCountryId = cvtermByCountryId;
        this.cvtermByProvinceId = cvtermByProvinceId;
        this.refdate = refdate;
        this.postalcode = postalcode;
        this.geolocations = geolocations;
    }

   
    // Property accessors

    public Integer getPolitlocationId() {
        return this.politlocationId;
    }
    
    public void setPolitlocationId(Integer politlocationId) {
        this.politlocationId = politlocationId;
    }

    public Cvterm getCvtermByCountyId() {
        return this.cvtermByCountyId;
    }
    
    public void setCvtermByCountyId(Cvterm cvtermByCountyId) {
        this.cvtermByCountyId = cvtermByCountyId;
    }

    public Cvterm getCvtermByCountryId() {
        return this.cvtermByCountryId;
    }
    
    public void setCvtermByCountryId(Cvterm cvtermByCountryId) {
        this.cvtermByCountryId = cvtermByCountryId;
    }

    public Cvterm getCvtermByProvinceId() {
        return this.cvtermByProvinceId;
    }
    
    public void setCvtermByProvinceId(Cvterm cvtermByProvinceId) {
        this.cvtermByProvinceId = cvtermByProvinceId;
    }

    public Date getRefdate() {
        return this.refdate;
    }
    
    public void setRefdate(Date refdate) {
        this.refdate = refdate;
    }

    public String getPostalcode() {
        return this.postalcode;
    }
    
    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public Set getGeolocations() {
        return this.geolocations;
    }
    
    public void setGeolocations(Set geolocations) {
        this.geolocations = geolocations;
    }
   








}