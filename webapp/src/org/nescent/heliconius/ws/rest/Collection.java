/**
 * Collection: class to query collection by id and return collection information 
 * as XML document
 */

package org.nescent.heliconius.ws.rest;
import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.BindingType;
import javax.xml.ws.Provider;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.WebServiceProvider;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.http.HTTPBinding;
import javax.xml.ws.http.HTTPException;

import org.nescent.heliconius.hibernate.Biotype;
import org.nescent.heliconius.hibernate.Geolocation;
import org.nescent.heliconius.hibernate.Individual;
import org.nescent.heliconius.hibernate.IndividualBiotype;
import org.nescent.heliconius.hibernate.IndividualDAO;


@WebServiceProvider
@BindingType(value=HTTPBinding.HTTP_BINDING)

/**
 * @author xianhua
 *
 */
public class Collection implements Provider<Source> {

	@Resource(type=Object.class)
    protected WebServiceContext wsContext;
	/* (non-Javadoc)
	 * @see javax.xml.ws.Provider#invoke(java.lang.Object)
	 */
	
	public Source invoke(Source arg0) {
		try {
			MessageContext mc = wsContext.getMessageContext();
            String query = (String)mc.get(MessageContext.QUERY_STRING);
                        
            if (query != null && query.contains("id="))
            {
            	StringTokenizer st = new StringTokenizer(query, "=&/");
            	
            	
            	String token=st.nextToken();
            	
            	if(token.equals("id"))
            	{
            		String id = st.nextToken();
            		return createSource(id);
            	}
            	else
            		throw new HTTPException(404);
            }
            else {
                throw new HTTPException(404);
            }
			
        } catch(Exception e) {
            e.printStackTrace();
            throw new HTTPException(500);
        }
	
	}
	
	
	/**
	 * @param str
	 * @return Source
	 */
	private Source createSource(String id) {
        
		IndividualDAO indvDao=new IndividualDAO();
		Individual indv=indvDao.findById(Integer.valueOf(id));
		String body ="<CollectionResponse>";
		if(indv==null)
		{
			body +="<Error>No individual found</Error>";
	            
		}
		else
		{
			body +="<Individual id=\"" + id +"\">";
			
			IndividualBiotype indvBiotype=(IndividualBiotype)indv.getIndividualBiotypes().toArray()[0];
			Biotype biotype=indvBiotype.getBiotype();
			
			body+="<ScientificName><Simple>"+biotype.getName()+"</Simple>";
			String genus="";
			String species="";
			String subspecies="";
			
			String ss[]=biotype.getName().split(" ssp. ");
			if(ss.length==2)
			{
				subspecies=ss[1];
			}
			String sss[]=ss[0].split(" ");
			if(sss.length==2)
				species=sss[1];
			genus=sss[0];
			
			body+="<Genus>" + genus + "</Genus>"
			+"<Species>" + species + "</Species>"
    		+"<Subspecies>" + subspecies + "</Subspecies>"
    		+"</ScientificName>";
    		
			String country1="";
            String province1="";
            String lat="";
            String longitude="";
            String alt="";
            Geolocation geo=indv.getGeolocation();
            
            if(geo!=null)
            {
            	if(geo.getCountry()!=null)
            		country1=geo.getCountry();
            	if(geo.getProvince()!=null)
            		province1=geo.getProvince();
            	if(geo.getLatitude()!=null)
            		lat=String.valueOf(geo.getLatitude());
            	if(geo.getLongitude()!=null)
            		longitude=String.valueOf(geo.getLongitude());
            	if(geo.getAltitude()!=null)
            		alt=String.valueOf(geo.getAltitude());
            }
            body+="<GeoLocation>" 
            	+"<Country>" + country1 +"</Country>" 
            	+"<Province>"+ province1+"</Province>"
            	+"<Latitude>" + lat+"</Latitude>"
            	+"<Longitude>"+longitude+"</Longitude>"
            	+"<Altitude>"+ alt+"</Altitude>"
            	+"</GeoLocation>";
            	
            body+="<Images></Images>";
            	
            
            Date date=indv.getCollectionDate();
            if(date!=null)
            	body+="<CollectionDate>" + date + "</CollectionDate>";
            else
            	body+="<CollectionDate></CollectionDate>";
            String notes=indv.getDescription();
            if(notes!=null)
            	body+="<Notes>"+notes+"</Notes>";
            else
            	body+="<Notes></Notes>";
            
            body+="</Individual>";
		}
		body+="</CollectionResponse>";
        System.out.println(body);
		Source source = new StreamSource(
            new ByteArrayInputStream(body.getBytes()));
        return source;
    }
	public static void main(String [] agrs)
	{
		Collection test=new Collection();
		Source sc=test.createSource("2537");
		System.out.println(sc.toString());
		
	}
}
