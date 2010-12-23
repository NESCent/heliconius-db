/**
 * Collections: class to query collections by scientific name and 
 * location and return a list of collections as XML document
 */
package org.nescent.heliconius.ws.rest;


import java.io.*;
import java.util.List;
import java.util.Set;
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

import org.hibernate.Session;
import org.nescent.heliconius.hibernate.Biotype;
import org.nescent.heliconius.hibernate.BiotypeDAO;
import org.nescent.heliconius.hibernate.Geolocation;
import org.nescent.heliconius.hibernate.HibernateSessionFactory;
import org.nescent.heliconius.hibernate.Individual;
import org.nescent.heliconius.hibernate.IndividualBiotype;
import org.nescent.heliconius.hibernate.IndividualDAO;

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
           // System.out.println(query);
            if (query != null)
            {
            	
            	StringTokenizer st = new StringTokenizer(query, "&");
            	
            	
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
            
            
            StringWriter writer = new StringWriter (  ) ; 
			PrintWriter out = new PrintWriter ( writer ) ; 
			e.printStackTrace(out);
            String str = writer.toString();
            String body ="<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" 
        		+"<CollectionsResponse>"
        		+"<Error>"+str+"</Error>"
        		+"</CollectionsResponse>";		
        		
            Source source = new StreamSource(
            	new ByteArrayInputStream(body.getBytes()));
        	return source;
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
	private Source createSource(String country,String province,String genus,String species,String subspecies) throws Exception {
                
		Session sess=HibernateSessionFactory.getSession();
		String name="";
		if(genus!=null && ! genus.trim().equals(""))
			name=genus.trim();
		if(species!=null && ! species.trim().equals(""))
			name+=" " + species.trim();
		if(subspecies!=null && ! subspecies.trim().equals(""))
			name+=" ssp. " + subspecies.trim();
		name=name.trim();
		IndividualDAO indvDao=new IndividualDAO();
		
		List indvList=null;
		
		//indvList=indvDao.findByProperty("biotype",biotype);
		String query="from Individual as indv join indv.individualBiotypes as biotypes join biotypes.biotype as biotype where indv.isCaptivityReared=FALSE AND  biotype.name like '%" + name +"%'";
		if(country!=null && ! country.trim().equals(""))
			query+=" AND indv.geolocation.country='" + country +"'";
		if(province!=null && ! province.trim().equals(""))
			query+=" AND indv.geolocation.province='" + province +"'";
		
		indvList=sess.createQuery(query).list();
			
		String body ="<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" 
        	+"<CollectionsResponse>"
        	+"<Individuals>";
		if(indvList!=null)
		{
			for(int i=0;i<indvList.size();i++)
			{
				
				try{
					Individual indv=(Individual)((Object [])indvList.get(i))[0];
					if(indv!=null)
					{
						body+="<Individual id=\"" + indv.getIndividualId()+"\">";
			            body +="<Name>" + indv.getName() +"</Name>";
			            Set biotypes=indv.getIndividualBiotypes();
			            if(biotypes!=null && biotypes.size()>0)
			            {
			            	IndividualBiotype indvBiotype=(IndividualBiotype)biotypes.toArray()[0];
							if(indvBiotype!=null)
							{
								Biotype biotype=indvBiotype.getBiotype();
								if(biotype!=null)
									body+="<ScientificName><Simple>"+biotype.getName()+"</Simple></ScientificName>"; 
								else
									body+="<ScientificName><Simple></Simple></ScientificName>"; 
							}
			            }
			            else
			            	body+="<ScientificName><Simple></Simple></ScientificName>"; 
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
			            	{
			            		try
			            		{
			            			alt=String.valueOf(geo.getAltitude());
			            		}
			            		catch(Exception e)
			            		{
			            			alt="";
			            		}
			            	}
			            }
			            body+="<GeoLocation>" 
			            	+"<Country>" + country1 +"</Country>" 
			            	+"<Province>"+ province1+"</Province>"
			            	+"<Latitude>" + lat+"</Latitude>"
			            	+"<Longitude>"+longitude+"</Longitude>"
			            	+"<Altitude>"+ alt+"</Altitude>"
			            	+"</GeoLocation>";
			            	
			            body+="<Images></Images>"
			            	+"</Individual>";
					}
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		body+="</Individuals>"
            +"</CollectionsResponse>";
        Source source = new StreamSource(
            new ByteArrayInputStream(body.getBytes()));
        //System.out.println(body);
       
        return source;
    }
	
	
}
