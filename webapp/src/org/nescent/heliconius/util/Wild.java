package org.nescent.heliconius.util;

public class Wild extends McIndividual{
	String Collection_Site;
	String Province;
	String Country;
	String Latitude;
	String Longitude;
	String Altitude;
	String Collector;
	String Collection_Notes;
	String creator;
	String IsWild;
	/**
	 * @return the altitude
	 */
	public String getAltitude() {
		return Altitude;
	}
	/**
	 * @param altitude the altitude to set
	 */
	public void setAltitude(String altitude) {
		Altitude = altitude;
	}
	/**
	 * @return the collection_Notes
	 */
	public String getCollection_Notes() {
		return Collection_Notes;
	}
	/**
	 * @param collection_Notes the collection_Notes to set
	 */
	public void setCollection_Notes(String collection_Notes) {
		Collection_Notes = collection_Notes;
	}
	/**
	 * @return the collection_Site
	 */
	public String getCollection_Site() {
		return Collection_Site;
	}
	/**
	 * @param collection_Site the collection_Site to set
	 */
	public void setCollection_Site(String collection_Site) {
		Collection_Site = collection_Site;
	}
	/**
	 * @return the collector
	 */
	public String getCollector() {
		return Collector;
	}
	/**
	 * @param collector the collector to set
	 */
	public void setCollector(String collector) {
		Collector = collector;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return Country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		Country = country;
	}
	/**
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}
	/**
	 * @param creator the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	/**
	 * @return the isWild
	 */
	public String getIsWild() {
		return IsWild;
	}
	/**
	 * @param isWild the isWild to set
	 */
	public void setIsWild(String isWild) {
		IsWild = isWild;
	}
	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return Latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return Longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	/**
	 * @return the province
	 */
	public String getProvince() {
		return Province;
	}
	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		Province = province;
	}
	
}
