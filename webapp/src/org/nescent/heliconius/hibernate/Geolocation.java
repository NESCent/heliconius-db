package org.nescent.heliconius.hibernate;

import java.util.HashSet;
import java.util.Set;


/**
 * Geolocation generated by MyEclipse - Hibernate Tools
 */

public class Geolocation  implements java.io.Serializable {


    // Fields    

     private Integer geolocationId;
     private Politlocation politlocation;
     private String description;
     private String coordinateXml;
     private Float latitude;
     private Float longitude;
     private String geodeticDatum;
     private Float altitude;
     private Float altitudeDev;
     private String country;
     private String province;
     private String county;
     private Set individuals = new HashSet(0);
     private Set crossexperiments = new HashSet(0);
     private Set stocks = new HashSet(0);


    // Constructors

    /** default constructor */
    public Geolocation() {
    }

    
    /** full constructor */
    public Geolocation(Politlocation politlocation, String description, String coordinateXml, Float latitude, Float longitude, String geodeticDatum, Float altitude, Float altitudeDev, Set individuals, Set crossexperiments, Set stocks) {
        this.politlocation = politlocation;
        this.description = description;
        this.coordinateXml = coordinateXml;
        this.latitude = latitude;
        this.longitude = longitude;
        this.geodeticDatum = geodeticDatum;
        this.altitude = altitude;
        this.altitudeDev = altitudeDev;
        this.individuals = individuals;
        this.crossexperiments = crossexperiments;
        this.stocks = stocks;
    }

   
    // Property accessors

    /**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}


	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}


	/**
	 * @return the county
	 */
	public String getCounty() {
		return county;
	}


	/**
	 * @param county the county to set
	 */
	public void setCounty(String county) {
		this.county = county;
	}


	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}


	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}


	public Integer getGeolocationId() {
        return this.geolocationId;
    }
    
    public void setGeolocationId(Integer geolocationId) {
        this.geolocationId = geolocationId;
    }

    public Politlocation getPolitlocation() {
        return this.politlocation;
    }
    
    public void setPolitlocation(Politlocation politlocation) {
        this.politlocation = politlocation;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoordinateXml() {
        return this.coordinateXml;
    }
    
    public void setCoordinateXml(String coordinateXml) {
        this.coordinateXml = coordinateXml;
    }

    public Float getLatitude() {
        return this.latitude;
    }
    
    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return this.longitude;
    }
    
    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getGeodeticDatum() {
        return this.geodeticDatum;
    }
    
    public void setGeodeticDatum(String geodeticDatum) {
        this.geodeticDatum = geodeticDatum;
    }

    public Float getAltitude() {
        return this.altitude;
    }
    
    public void setAltitude(Float altitude) {
        this.altitude = altitude;
    }

    public Float getAltitudeDev() {
        return this.altitudeDev;
    }
    
    public void setAltitudeDev(Float altitudeDev) {
        this.altitudeDev = altitudeDev;
    }

    public Set getIndividuals() {
        return this.individuals;
    }
    
    public void setIndividuals(Set individuals) {
        this.individuals = individuals;
    }

    public Set getCrossexperiments() {
        return this.crossexperiments;
    }
    
    public void setCrossexperiments(Set crossexperiments) {
        this.crossexperiments = crossexperiments;
    }

    public Set getStocks() {
        return this.stocks;
    }
    
    public void setStocks(Set stocks) {
        this.stocks = stocks;
    }
   








}