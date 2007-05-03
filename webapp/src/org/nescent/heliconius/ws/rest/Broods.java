/**
 * Broods: class to query broods by scientific names of both parents and 
 * return a list of broods as XML document
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
public class Broods implements Provider<Source> {
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
	 * createSource:function to query broods by scientific names of both parents, and put the result into XML document
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
		
		/**hold 2 seconds to let the animation of the flying butterfly show up.
		 * This is only for demo purpose and should be removed later
		 */ 
		try{
			Thread.sleep(2000);
		}catch(Exception e){}
		
        String body ="<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" 
        	+"<BroodsResponse>"
        	+"<Broods>"
        	+"<Brood id=\"0\">"
        	+"<Father id=\"0\">"
        	+"<ScientificName><Simple>Scientific Name of Father</Simple></ScientificName>"
        	+"</Father>"
            +"<Mother id=\"1\">"
            +"<ScientificName><Simple>Scientific Name of Mother</Simple></ScientificName>"
            +"</Mother>"
            +"<CrossType>Cross Type 1</CrossType>"
            +"<NumberOfEggs>100</NumberOfEggs>"
            +"<NumberOfAdults>25</NumberOfAdults>"
            +"</Brood>"
            
            +"<Brood id=\"1\">"
        	+"<Father id=\"10\">"
        	+"<ScientificName><Simple>Scientific Name of Father</Simple></ScientificName>"
        	+"</Father>"
            +"<Mother id=\"11\">"
            +"<ScientificName><Simple>Scientific Name of Mother</Simple></ScientificName>"
            +"</Mother>"
            +"<CrossType>Cross Type 1</CrossType>"
            +"<NumberOfEggs>30</NumberOfEggs>"
            +"<NumberOfAdults>5</NumberOfAdults>"
            +"</Brood>"
            
            +"<Brood id=\"3\">"
        	+"<Father id=\"20\">"
        	+"<ScientificName><Simple>Scientific Name of Father</Simple></ScientificName>"
        	+"</Father>"
            +"<Mother id=\"21\">"
            +"<ScientificName><Simple>Scientific Name of Mother</Simple></ScientificName>"
            +"</Mother>"
            +"<CrossType>Cross Type 3</CrossType>"
            +"<NumberOfEggs>80</NumberOfEggs>"
            +"<NumberOfAdults>52</NumberOfAdults>"
            +"</Brood>"
            
            +"</Broods>"
            +"</BroodsResponse>";
        Source source = new StreamSource(
            new ByteArrayInputStream(body.getBytes()));
        return source;
    }
}
