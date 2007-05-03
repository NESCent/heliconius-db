/**
 * Collection: class to query collection by id and return collection information 
 * as XML document
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
        
		/**hold 2 seconds to let the animation of the flying butterfly show up.
		 * This is only for demo purpose and should be removed later
		 */ 
		try{
			Thread.sleep(2000);
		}catch(Exception e){}
		
		String notes="Basking in their first moments of nuptial bliss, brides and grooms around the country are exiting churches, showered not with rice or confetti, but surrounded instead by the fluttering gossamer wings of hundreds of butterflies. All but unheard of just a few years ago, butterfly releases are the latest fashion at weddings, not to mention at memorial services, grand openings, divorces and prison releases.\n"; 
        notes+="\"The beautiful flight of the butterflies as they ascended -- it captured the beauty and the spirit of the day,\" said Dr. Patricia Heaman of White Haven, Pa., who ordered a \"mixed bouquet\" of several species of butterflies for the garden wedding last month of her nature-loving daughter. \"It just seemed like the natural culmination to a natural event.\""; 

		String body =
            "<CollectionResponse>"
        	+"<Individual id=\"" + id +"\">"
            +"<ScientificName>"
            +"<Simple>Scientific Name 1</Simple>"
            +"<Family>family name</Family>"
    		+"<Subfamily>Subfamily name</Subfamily>"
    		+"<Genus>Genus name</Genus>"
    		+"<Species>Species name </Species>"
    		+"<Subspecies>Subspecies name</Subspecies>"
    		+"</ScientificName>"
            +"<GeoLocation><Country>USA</Country><Province>NC</Province>"
            +"<Latitude>30.5</Latitude>"
            +"<Longitude>100.3</Longitude>"
            +"<Altitude>330.8</Altitude>"
            +"</GeoLocation>"
            +"<Images>"
            +"<Image>eratocyrbia.jpg</Image>"
            +"</Images>"
            +"<Specimens SpecimenNumber=\"5\">"
        	+"<Specimen id=\"1\">"
        	+"<Tissue>tissue 1</Tissue>"
        	+"<Location>location 1</Location>"
        	+"</Specimen>"
        	+"<Specimen id=\"2\">"
        	+"<Tissue>tissue 2</Tissue>"
        	+"<Location>location 2</Location>"
        	+"</Specimen>"
        	+"</Specimens>"
        	+"<Sex>Male</Sex>"
        	+"<CollectionDate>01/20/2001</CollectionDate>"
        	+"<Notes>"+notes+"</Notes>"
            +"</Individual>"
            +"</CollectionResponse>";
        Source source = new StreamSource(
            new ByteArrayInputStream(body.getBytes()));
        return source;
    }
	
}
