/**
 *  Hybrids: class to query Hybrids by scientific names of both 
 *  parents and return a list of Hybrids as XML document
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
public class Hybrids implements Provider<Source> {
	@Resource(type=Object.class)
    protected WebServiceContext wsContext;
	
	/* (non-Javadoc)
	 * @see javax.xml.ws.Provider#invoke(java.lang.Object)
	 */
	public Source invoke(Source arg0) {
		try {
            
			MessageContext mc = wsContext.getMessageContext();
            String query = (String)mc.get(MessageContext.QUERY_STRING);
            String genus1="";
            String species1="";
            String subspecies1="";
            String genus2="";
            String species2="";
            String subspecies2="";
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
            			if(ss[0].equals("genus1"))
	            		{
	            			genus1 = ss[1];
	            		}
	            		else if(ss[0].equals("species1"))
	            		{
	            			species1 = ss[1];
	            		}
	            		else if(ss[0].equals("subspecies1"))
	            		{
	            			subspecies1 =ss[1];
	            		}
	            		else if(ss[0].equals("genus2"))
	            		{
	            			genus2 = ss[1];
	            		}
	            		else if(ss[0].equals("species2"))
	            		{
	            			species2 = ss[1];
	            		}
	            		else if(ss[0].equals("subspecies2"))
	            		{
	            			subspecies2 =ss[1];
	            		}
            		}
            	}
            }
            
            return createSource(genus1,species1,subspecies1,genus2,species2,subspecies2);
            
			
        } catch(Exception e) {
            e.printStackTrace();
            throw new HTTPException(500);
        }
	
	}

	/**
	 * createSource:function to query hybrids by scientific names of both 
	 * parents, and put the result into XML document
	 * 
	 * @param genus1 : genus name of parent 1
	 * @param species1 : species name of parent 1 
	 * @param subspecies1 : subspecies name of parent 1
	 * @param genus2 : genus name of parent 2
	 * @param species2 : species name of parent 2 
	 * @param subspecies2 : subspecies name of parent 2
	 * @return Source : stream source of the XML document string
	 */
	private Source createSource(String genus1,String species1,String subspecies1,
			String genus2,String species2,String subspecies2) {
		String body ="<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" 
        	+"<HybridsResponse>"
        	+"<Individuals>"
        	+"<Individual id=\"0\">"
        	+"<ScientificName><Simple>Scientific Name of Parent 1</Simple></ScientificName>"
        	+"<ScientificName2><Simple>Scientific Name of Parent 2</Simple></ScientificName2>"
        	+"<GeoLocation><Country>USA</Country><Province>NC</Province>"
            +"<Latitude>30.5</Latitude>"
            +"<Longitude>100.3</Longitude>"
            +"<Altitude>330.8</Altitude>"
            +"</GeoLocation>"
            +"<Images><Image>image1</Image></Images>"
            +"<Specimens SpecimenNumber=\"5\"></Specimens>"
            +"</Individual>"
            +"<Individual id=\"1\">"
            +"<ScientificName><Simple>Scientific Name of Parent 1</Simple></ScientificName>"
        	+"<ScientificName2><Simple>Scientific Name of Parent 2</Simple></ScientificName2>"
            +"<GeoLocation><Country>USA</Country><Province>FL</Province>"
            +"<Latitude>23.0</Latitude>"
            +"<Longitude>90.5</Longitude>"
            +"<Altitude>230.1</Altitude>"
            +"</GeoLocation>"
            +"<Images><Image>image2</Image></Images>"
            +"<Specimens SpecimenNumber=\"2\"></Specimens>"
            +"</Individual>"
            +"</Individuals>"
            +"</HybridsResponse>";
        Source source = new StreamSource(
            new ByteArrayInputStream(body.getBytes()));
        return source;
    }
}
