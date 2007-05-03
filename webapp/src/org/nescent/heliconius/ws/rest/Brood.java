/**
 * Brood: class to query brood by id and return brood information as XML 
 * document
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
public class Brood implements Provider<Source> {

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
		
		String body ="<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" 
        	+"<BroodResponse>"
        	+"<Brood id=\"" + id +"\">"
        	+"<Father id=\"1000\">"
        	+"<ScientificName><Simple>Scientific Name of Father</Simple></ScientificName>"
        	+"</Father>"
            +"<Mother id=\"1001\">"
            +"<ScientificName><Simple>Scientific Name of Mother</Simple></ScientificName>"
            +"</Mother>"
            +"<CrossType>Cross Type 1</CrossType>"
            +"<NumberOfEggs>100</NumberOfEggs>"
            +"<NumberOfAdults>25</NumberOfAdults>"
            +"<Offsprings>";
        
        for(int i=10000; i<10025;i++)
        {
        	body+="<Individual id=\"" + i + "\">"
        		+"<ScientificName><Simple>Offspring " + i + "</Simple></ScientificName>"
        		+"</Individual>";
        }
            
        body+="</Offsprings>";
        
        body+="<FatherBrood id=\"2000\">"
        	+"<Father id=\"20001\">"
        	+"<ScientificName><Simple>Scientific Name of Father's Father</Simple></ScientificName>"
        	+"</Father>"
        	+"<Mother id=\"20002\">"
        	+"<ScientificName><Simple>Scientific Name of Father's Mother</Simple></ScientificName>"
        	+"</Mother>"
        	+"</FatherBrood>";
        body+="<MotherBrood id=\"3000\">"
        	+"<Father id=\"30001\">"
        	+"<ScientificName><Simple>Scientific Name of Mother's Father</Simple></ScientificName>"
        	+"</Father>"
        	+"<Mother id=\"30002\">"
        	+"<ScientificName><Simple>Scientific Name of Mother's Mother</Simple></ScientificName>"
        	+"</Mother>"
        	+"</MotherBrood>";
        body+="</Brood>";
        body+="</BroodResponse>";
        
        Source source = new StreamSource(
            new ByteArrayInputStream(body.getBytes()));
        return source;
    }
}
