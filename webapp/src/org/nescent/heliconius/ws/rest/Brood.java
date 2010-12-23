/**
 * Brood: class to query brood by id and return brood information as XML 
 * document
 */

package org.nescent.heliconius.ws.rest;
import java.io.ByteArrayInputStream;
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

import org.nescent.heliconius.hibernate.Biotype;
import org.nescent.heliconius.hibernate.Crossexperiment;
import org.nescent.heliconius.hibernate.CrossexperimentDAO;
import org.nescent.heliconius.hibernate.Crossexperimentprop;
import org.nescent.heliconius.hibernate.Cvterm;
import org.nescent.heliconius.hibernate.Individual;
import org.nescent.heliconius.hibernate.IndividualBiotype;
import org.nescent.heliconius.hibernate.IndividualDAO;


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
        
		CrossexperimentDAO crossDao=new CrossexperimentDAO();
		Crossexperiment cross=crossDao.findById(Integer.valueOf(id));
		
		String body ="<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" 
        	+"<BroodResponse>";
        
        	
		if(cross==null)
		{
			body +="<Error>No brood found</Error>";
	    }
		else
		{
			body+="<Brood id=\"" + id +"\">";
        	
			Individual father=cross.getIndividualByMaleId();
        	if(father!=null)
        	{
        		body+="<Father id=\"" + father.getIndividualId()+"\">";
        		IndividualBiotype indvBiotype=(IndividualBiotype)father.getIndividualBiotypes().toArray()[0];
    			Biotype biotype=indvBiotype.getBiotype();
    			body+="<ScientificName><Simple>"+biotype.getName()+"</Simple></ScientificName></Father>";
        	}
        	
        	Individual mother=cross.getIndividualByFemaleId();
        	if(mother!=null)
        	{
        		body+="<Mother id=\"" + mother.getIndividualId()+"\">";
        		IndividualBiotype indvBiotype=(IndividualBiotype)mother.getIndividualBiotypes().toArray()[0];
    			Biotype biotype=indvBiotype.getBiotype();
    			body+="<ScientificName><Simple>"+biotype.getName()+"</Simple></ScientificName></Mother>";
        	}
        	Cvterm cv=cross.getCvterm();
        	
        	if(cv!=null)
    			body+="<CrossType>" + cv.getName()+ "</CrossType>";
        	else
        		body+="<CrossType></CrossType>";
        	
        	Set set=cross.getCrossexperimentprops();
        	for(int j=0;j<set.size();j++)
        	{
        		Crossexperimentprop prop=(Crossexperimentprop)set.toArray()[j];
        		if(prop.getCvterm().getName().equals("Number_Of_Eggs"))
        			body+="<NumberOfEggs>" + prop.getValue() + "</NumberOfEggs>";
        		if(prop.getCvterm().getName().equals("Number_Of_Eclosed"))
        			body+="<NumberOfAdults>" + prop.getValue() + "</NumberOfAdults>";
        	}
        	
        	Set children=cross.getIndividuals();
        	
        	body+="<Offsprings>";
        	for(int i=0; i<children.size();i++)
        	{
        		Individual indv=(Individual)children.toArray()[i];
        		body+="<Individual id=\"" + indv.getIndividualId() + "\">";
        		IndividualBiotype indvBiotype=(IndividualBiotype)indv.getIndividualBiotypes().toArray()[0];
    			Biotype biotype=indvBiotype.getBiotype();
    			
    			body+="<ScientificName><Simple>"+biotype.getName()+"</Simple></ScientificName>"
        			+"</Individual>";
        	}
            body+="</Offsprings>";
            
            if(father!=null)
            {
            	Crossexperiment fcross=father.getCrossexperiment();
            	if(fcross!=null)
            	{
            		body+="<FatherBrood id=\"" + fcross.getName() + "\">";
            		Individual ffather=fcross.getIndividualByMaleId();
                	if(ffather!=null)
                	{
                		body+="<Father id=\"" + ffather.getIndividualId()+"\">";
                		IndividualBiotype findvBiotype=(IndividualBiotype)ffather.getIndividualBiotypes().toArray()[0];
            			Biotype fbiotype=findvBiotype.getBiotype();
            			body+="<ScientificName><Simple>"+fbiotype.getName()+"</Simple></ScientificName></Father>";
                	}
                	
                	Individual fmother=fcross.getIndividualByFemaleId();
                	if(fmother!=null)
                	{
                		body+="<Mother id=\"" + fmother.getIndividualId()+"\">";
                		IndividualBiotype mindvBiotype=(IndividualBiotype)fmother.getIndividualBiotypes().toArray()[0];
            			Biotype mbiotype=mindvBiotype.getBiotype();
            			body+="<ScientificName><Simple>"+mbiotype.getName()+"</Simple></ScientificName></Mother>";
                	}
                	
                	body+="</FatherBrood>";
            	}
            }
        
            if(mother!=null)
            {
            	Crossexperiment mcross=mother.getCrossexperiment();
            	if(mcross!=null)
            	{
            		body+="<MotherBrood id=\"" + mcross.getName() + "\">";
            		Individual mfather=mcross.getIndividualByMaleId();
                	if(mfather!=null)
                	{
                		body+="<Father id=\"" + mfather.getIndividualId()+"\">";
                		IndividualBiotype mindvBiotype=(IndividualBiotype)mfather.getIndividualBiotypes().toArray()[0];
            			Biotype mbiotype=mindvBiotype.getBiotype();
            			body+="<ScientificName><Simple>"+mbiotype.getName()+"</Simple></ScientificName></Father>";
                	}
                	
                	Individual mmother=mcross.getIndividualByFemaleId();
                	if(mmother!=null)
                	{
                		body+="<Mother id=\"" + mmother.getIndividualId()+"\">";
                		IndividualBiotype mindvBiotype=(IndividualBiotype)mmother.getIndividualBiotypes().toArray()[0];
            			Biotype mbiotype=mindvBiotype.getBiotype();
            			body+="<ScientificName><Simple>"+mbiotype.getName()+"</Simple></ScientificName></Mother>";
                	}
                	
                	body+="</MotherBrood>";
            	}
            }
            body+="</Brood>";
		} 
        body+="</BroodResponse>";
        Source source = new StreamSource(
            new ByteArrayInputStream(body.getBytes()));
        return source;
    }
	
	public static void main(String [] agrs)
	{
		Brood test=new Brood();
		Source sc=test.createSource("47");
		System.out.println(sc.toString());
		
	}
}
