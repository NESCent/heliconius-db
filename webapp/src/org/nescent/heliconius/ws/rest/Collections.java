/**
 * Collections: class to query collections by scientific name and 
 * location and return a list of collections as XML document
 */
package org.nescent.heliconius.ws.rest;

import java.io.ByteArrayInputStream;
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

@WebServiceProvider
@BindingType(value=HTTPBinding.HTTP_BINDING)
public class Collections implements Provider<Source> {
	@Resource(type=Object.class)
    protected WebServiceContext wsContext;
	
	/* (non-Javadoc)
	 * @see javax.xml.ws.Provider#invoke(java.lang.Object)
	 */
	public Source invoke(Source arg0) {
		try {
            
			MessageContext mc = wsContext.getMessageContext();
            String query = (String)mc.get(MessageContext.QUERY_STRING);
            String country="";
            String province="";
            String genus="";
            String species="";
            String subspecies="";
            System.out.println(query);
            if (query != null)
            {
            	
            	StringTokenizer st = new StringTokenizer(query, "&");
            	
            	if(st.hasMoreTokens())
            	
            	
            	while(st.hasMoreTokens())
            	{
            		String token=st.nextToken();
            		String ss[]=token.split("=");
            		if(ss.length==2)
            		{
            			if(ss[0].equals("country"))
            			{
            				country = ss[1];
            			}
	            		else if(ss[0].equals("province"))
	            		{
	            			province = ss[1];
	            		} 
	            		else if(ss[0].equals("genus"))
	            		{
	            			genus = ss[1];
	            		}
	            		else if(ss[0].equals("species"))
	            		{
	            			species = ss[1];
	            		}
	            		else if(ss[0].equals("subspecies"))
	            		{
	            			subspecies =ss[1];
	            		}
            		}
            	}
            }
            
            return createSource(country,province,genus,species,subspecies);
            
			
        } catch(Exception e) {
            e.printStackTrace();
            throw new HTTPException(500);
        }
	
	}
	
	/**
	 * @param country
	 * @param province
	 * @param genus
	 * @param species
	 * @param subspecies
	 * @return
	 */
	private Source createSource(String country,String province,String genus,String species,String subspecies) {
                
		/**hold 2 seconds to let the animation of the flying butterfly show up.
		 * This is only for demo purpose and should be removed later
		 */ 
		try{
			Thread.sleep(2000);
		}catch(Exception e){}
		
		String body ="<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" 
        	+"<CollectionsResponse>"
        	+"<Individuals>"
        	+"<Individual id=\"0\">"
            +"<ScientificName><Simple>"+genus+" "+ species+" "+subspecies + "</Simple></ScientificName>"
            +"<GeoLocation><Country>" + country +"</Country><Province>"+ province+"</Province>"
            +"<Latitude>30.5</Latitude>"
            +"<Longitude>100.3</Longitude>"
            +"<Altitude>330.8</Altitude>"
            +"</GeoLocation>"
            +"<Images><Image>image1</Image></Images>"
            +"<Specimens SpecimenNumber=\"5\"></Specimens>"
            +"</Individual>"
            +"<Individual id=\"1\">"
            +"<ScientificName><Simple>Scientific Name 1</Simple></ScientificName>"
            +"<GeoLocation><Country>USA</Country><Province>FL</Province>"
            +"<Latitude>23.0</Latitude>"
            +"<Longitude>90.5</Longitude>"
            +"<Altitude>230.1</Altitude>"
            +"</GeoLocation>"
            +"<Images><Image>image2</Image></Images>"
            +"<Specimens SpecimenNumber=\"2\"></Specimens>"
            +"</Individual>"
            +"</Individuals>"
            +"</CollectionsResponse>";
        Source source = new StreamSource(
            new ByteArrayInputStream(body.getBytes()));
        return source;
    }
}
