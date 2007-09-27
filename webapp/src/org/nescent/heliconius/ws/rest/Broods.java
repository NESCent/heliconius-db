/**
 * Broods: class to query broods by scientific names of both parents and 
 * return a list of broods as XML document
 */

package org.nescent.heliconius.ws.rest;

import java.io.ByteArrayInputStream;
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
import org.nescent.heliconius.hibernate.Crossexperiment;
import org.nescent.heliconius.hibernate.Crossexperimentprop;
import org.nescent.heliconius.hibernate.Cvterm;
import org.nescent.heliconius.hibernate.HibernateSessionFactory;
import org.nescent.heliconius.hibernate.Individual;
import org.nescent.heliconius.hibernate.IndividualBiotype;

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
		
		Session sess=HibernateSessionFactory.getSession();
		String name="";
		if(genus1!=null && ! genus1.trim().equals(""))
			name=genus1.trim();
		if(species1!=null && ! species1.trim().equals(""))
			name+=" " + species1.trim();
		if(subspecies1!=null && ! subspecies1.trim().equals(""))
			name+=" ssp. " + subspecies1.trim();
		name=name.trim();
		String fstr="select individual from Individual individual join individual.individualBiotypes biotypes join biotypes.biotype biotype where biotype.name like '%"+name+"%'";
		String name1="";
		if(genus2!=null && ! genus2.trim().equals(""))
			name1=genus2.trim();
		if(species2!=null && ! species2.trim().equals(""))
			name1+=" " + species2.trim();
		if(subspecies2!=null && ! subspecies2.trim().equals(""))
			name1+=" ssp. " + subspecies2.trim();
		name1=name1.trim();
		
		String mstr="select individual from Individual individual join individual.individualBiotypes biotypes join biotypes.biotype biotype where biotype.name like '%"+name1+"%'";
		
		String query="select cross from Crossexperiment cross where "
			+"cross.individualByFemaleId in (" + fstr +")"
			+"AND cross.individualByMaleId in (" + mstr +")";
		
		
		String body ="<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" 
        	+"<BroodsResponse>"
        	+"<Broods>";
		
		
		List indvCross=sess.createQuery(query).list();
        for(int i=0;i<indvCross.size();i++)
        {
        	Crossexperiment cross=(Crossexperiment)indvCross.get(i);
        	body+="<Brood id=\"" + cross.getCrossexperimentId()+"\">";
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
        	
            body+="</Brood>";
        } 
            
        body+="</Broods>";
        body+="</BroodsResponse>";
        
        
        Source source = new StreamSource(
            new ByteArrayInputStream(body.getBytes()));
        return source;
    }
	
	public static void main(String [] agrs)
	{
		Broods test=new Broods();
		Source sc=test.createSource("","erato","","","erato","");
		System.out.println(sc.toString());
		
	}
}
