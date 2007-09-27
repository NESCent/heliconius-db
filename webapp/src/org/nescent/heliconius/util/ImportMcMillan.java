package org.nescent.heliconius.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.nescent.heliconius.hibernate.Biotype;
import org.nescent.heliconius.hibernate.BiotypeDAO;
import org.nescent.heliconius.hibernate.BiotypeOrganism;
import org.nescent.heliconius.hibernate.Cv;
import org.nescent.heliconius.hibernate.CvDAO;
import org.nescent.heliconius.hibernate.Cvterm;
import org.nescent.heliconius.hibernate.CvtermDAO;
import org.nescent.heliconius.hibernate.Db;
import org.nescent.heliconius.hibernate.DbDAO;
import org.nescent.heliconius.hibernate.Dbxref;
import org.nescent.heliconius.hibernate.Geolocation;
import org.nescent.heliconius.hibernate.HibernateSessionFactory;
import org.nescent.heliconius.hibernate.Individual;
import org.nescent.heliconius.hibernate.IndividualBiotype;
import org.nescent.heliconius.hibernate.Organism;
import org.nescent.heliconius.hibernate.OrganismDAO;
import org.nescent.heliconius.hibernate.Politlocation;
import org.nescent.heliconius.hibernate.PolitlocationDAO;

public class ImportMcMillan {

	String dbURL="jdbc:postgresql://152.3.58.175:5432/mcmillan";
	String user="xl24";
	String password="lxh72325";
	
	public Connection getConnection() throws Exception
	{
		Class.forName("org.postgresql.Driver").newInstance();
		
		String connString=dbURL+"?user="+user+"&password="+password;
		java.sql.Connection conn;
		
		conn = DriverManager.getConnection(connString);
		return conn;
	}
	
	public void importOrganism() throws Exception
	{
		
		Session sess=HibernateSessionFactory.getSession();
		Transaction tr=sess.beginTransaction();
		
		String sql="SELECT * from organism";
		Connection conn=getConnection();
		
		Statement stm=conn.createStatement();
		ResultSet rs=stm.executeQuery(sql);
		while(rs.next())
		{
			Organism org=new Organism();
			Biotype biotype= new Biotype();
			BiotypeOrganism bioorg=new BiotypeOrganism();
						
			String organism_id=rs.getString("organism_id");
			String genus=rs.getString("genus");
			if(genus.equals("N/A")) continue;
			String species=rs.getString("species");
			String subspecies=rs.getString("subspecies");
			String abbreviation=rs.getString("abbreviation");
			String common_name=rs.getString("common_name");
			String notes=rs.getString("notes");
			String classname=rs.getString("class");
			String order=rs.getString("order");
			String family=rs.getString("family");
			String subfamily=rs.getString("subfamily");
			
			org.setComment(notes);
			org.setCommonName(common_name);
			org.setGenus(genus);
			org.setOrganismId(Integer.valueOf(organism_id));
			if(subspecies != null && ! subspecies.trim().equals(""))
				species=species+" ssp. " + subspecies;
			org.setSpecies(species);
			
			if(abbreviation!=null && ! abbreviation.trim().equals(""))
				org.setAbbreviation(abbreviation);
			else
				org.setAbbreviation(genus+" "+species);
			
			bioorg.setBiotype(biotype);
			bioorg.setOrganism(org);
			
			biotype.setName(genus+" "+species);
			biotype.getBiotypeOrganisms().add(bioorg);
			org.getBiotypeOrganisms().add(bioorg);
			
			
			try
			{
				sess.save(org);
			}
			catch(Exception e)
			{
				System.out.println(org.getGenus()+":"+org.getSpecies()+":"+e);
			}
		}
		
		stm.close();
		rs.close();
		conn.close();
		
		
		sess.flush();
		tr.commit();
		sess.close();
		
	}
	
	public void importIndividual() throws Exception 
	{
		Session sess=HibernateSessionFactory.getSession();
		Transaction tr=sess.beginTransaction();
		
		String sql="SELECT individual.*, organism.species,organism.genus,organism.subspecies from individual, biotype_organism, organism ";
		sql+=" WHERE individual.biotype_id=biotype_organism.biotype_id AND biotype_organism.organism_id=organism.organism_id order by individual_id";
		
		Connection conn=getConnection();
		
		Statement stm=conn.createStatement();
		ResultSet rs=stm.executeQuery(sql);
		Biotype biotype=null;
		BiotypeDAO btDao=new BiotypeDAO();
		CvtermDAO cvtDao=new CvtermDAO();
		Cvterm verysure=null;
		Cvterm halfsure=null;
		Cvterm unknown=null;
		
		List cvtList = cvtDao.findByName("very sure");
		if(cvtList.size()>0)
			verysure=(Cvterm)cvtList.get(0);
		else
		{
			System.out.println("failed to find cvterm: verysure");
			return;
		}
		
		cvtList = cvtDao.findByName("half sure");
		if(cvtList.size()>0)
			halfsure=(Cvterm)cvtList.get(0);
		else
		{
			System.out.println("failed to find cvterm: halfsure");
			return;
		}
		cvtList = cvtDao.findByName("unknown");
		if(cvtList.size()>0)
			unknown=(Cvterm)cvtList.get(0);
		else
		{
			System.out.println("failed to find cvterm: unknown");
			return;
		}
		
		int count=0;
			
		while(rs.next())
		{
			if(count==242)
				System.out.println("hi");
			
			String genus=rs.getString("genus");
			if(genus.equals("N/A")) continue;
			String species=rs.getString("species");
			String subspecies=rs.getString("subspecies");
			if(subspecies != null && ! subspecies.trim().equals(""))
				species=species+" ssp. " + subspecies;
			String abbreviation=genus+" "+species;
			
			List btList=btDao.findByName(abbreviation);
			if(btList.size()<1)continue;
					
			biotype=(Biotype)btList.get(0);
			
						
			
			String individual_id=rs.getString("individual_id");
			
			String individual_name=rs.getString("individual_name");
			if(individual_name==null || individual_name.trim().equals("")) continue;
			Individual indv=new Individual();
			indv.setName(individual_name);
			
			String wild=rs.getString("wild");
			String pedigree_id=rs.getString("pedigree_id");
			String stock_id=rs.getString("stock_id");
			String geographic_site_id=rs.getString("geographic_site_id");
			String collection_date=rs.getString("collection_date");
			String male=rs.getString("male");
			String biotype_id=rs.getString("biotype_id");
			String taxonomic_confidence=rs.getString("taxonomic_confidence");
			String experimenter_id=rs.getString("experimenter_id");
			String notes=rs.getString("notes");
			String o_genus=rs.getString("o_genus");
			String o_species=rs.getString("o_species");
			String o_race=rs.getString("o_race");
			String r_old_mother=rs.getString("r_old_mother");
			String r_old_father=rs.getString("r_old_father");
			String r_member_brood_no=rs.getString("r_member_brood_no");
			String r_offspring_no=rs.getString("r_offspring_no");
			String r_old_cross_type=rs.getString("r_old_cross_type");
			String r_old_parent=rs.getString("r_old_parent");
			String r_old_brood_no=rs.getString("r_old_brood_no");
			String r_calculated_collection_number=rs.getString("r_calculated_collection_number");
			String r_calc_cross_type=rs.getString("r_calc_cross_type");
			String r_calc_mother=rs.getString("r_calc_mother");
			String r_calc_father=rs.getString("r_calc_father");
			String r_calc_filial_status=rs.getString("r_calc_filial_status");
			String r_creator=rs.getString("r_creator");
			String w_collection_site=rs.getString("w_collection_site");
			String w_dept_province=rs.getString("w_dept_province");
			String w_country=rs.getString("w_country");
			String w_latitude=rs.getString("w_latitude");
			String w_longitude=rs.getString("w_longitude");
			String w_altitude=rs.getString("w_altitude");
			String w_collector=rs.getString("w_collector");
			String w_creator=rs.getString("w_creator");

			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			if(collection_date!=null && !collection_date.trim().equals(""))
			{
				try
				{
					indv.setCollectionDate(df.parse(collection_date));
				}
				catch(Exception e)
				{
					System.out.println("Failed to convert collection date: "+collection_date);
				}
			}
			
			
			indv.setDescription(notes);
			
			if(wild.equals("t"))
				indv.setIsCaptivityReared(Boolean.TRUE);
			else
				indv.setIsCaptivityReared(Boolean.FALSE);
			
			//set biotype
			IndividualBiotype indvBiotype=new IndividualBiotype();
			
			
			indvBiotype.setBiotype(biotype);
			indvBiotype.setIndividual(indv);
			
			
			
			if(taxonomic_confidence!=null)
			{
				if(taxonomic_confidence.trim().equals("1"))
				{
					indvBiotype.setCvterm(verysure);
					verysure.getIndividualBiotypes().add(indvBiotype);
				}
				else if(taxonomic_confidence.trim().equals("0.5"))
				{
					indvBiotype.setCvterm(halfsure);
					halfsure.getIndividualBiotypes().add(indvBiotype);
				}
				else
				{
					indvBiotype.setCvterm(unknown);
					unknown.getIndividualBiotypes().add(indvBiotype);
				}
			}
			else
			{
				indvBiotype.setCvterm(unknown);
				unknown.getIndividualBiotypes().add(indvBiotype);
			}	
			
			indv.getIndividualBiotypes().add(indvBiotype);
			biotype.getIndividualBiotypes().add(indvBiotype);
			
			//set geo location
			Geolocation location = new Geolocation();
			if(w_altitude!=null && !w_altitude.trim().equals(""))
			{
				w_altitude=w_altitude.replaceAll("m", "");
				try
				{
					location.setAltitude(Float.valueOf(w_altitude));	
				}
				catch(Exception e)
				{
					System.out.println("Failed to convert altitude:"+w_altitude);
				}
			}
			
			try
			{
				if(w_latitude!=null && !w_latitude.trim().equals(""))
				{
					if(w_latitude.indexOf('-')>0)
					{
						String semi="N";
						if(w_latitude.indexOf('S')>0)
							semi="S";
						String tmp=w_latitude.replaceAll("S","");
						tmp=tmp.replaceAll("N","");
						String ss[]=tmp.split("-");
						if(ss.length==2)
						{
							float lat=Float.valueOf(ss[0]).floatValue()+Float.valueOf(ss[1]).floatValue()/60.0f;
							if(semi.equals("S"))lat=-lat;
							location.setLatitude(Float.valueOf(lat));
						}
					}
					else
						location.setLatitude(Float.valueOf(w_latitude));
				}
			}
			catch(Exception e)
			{
				System.out.println("Failed to convert latitude:"+w_latitude);
			}
			
			
			try
			{
				if(w_longitude!=null && !w_longitude.trim().equals(""))
				{
					if(w_longitude.indexOf('-')>0)
					{
						String semi="E";
						if(w_longitude.indexOf('W')>0)
							semi="W";
						String tmp=w_longitude.replaceAll("E","");
						tmp=tmp.replaceAll("W","");
						String ss[]=tmp.split("-");
						if(ss.length==2)
						{
							float longigude=Float.valueOf(ss[0]).floatValue()+Float.valueOf(ss[1]).floatValue()/60.0f;
							if(semi.equals("W"))longigude=-longigude;
							location.setLongitude(Float.valueOf(longigude));
						}
					}
					else
						location.setLongitude(Float.valueOf(w_longitude));
				}
			}
			catch(Exception e)
			{
				System.out.println("Failed to convert latitude:"+w_latitude);
			}
			
			
			Politlocation ploc=null;
			PolitlocationDAO plocDao=new PolitlocationDAO();
			Cvterm countryCvterm=null;
			Cvterm provinceCvterm=null;
			
			boolean hasPloc=false;
			if(w_country!=null && ! w_country.trim().equals(""))
			{
				String country=w_country.trim();
				List list=cvtDao.findByName(country);
				if(list.size()>0)
				{
					countryCvterm=(Cvterm)list.get(0);
					hasPloc=true;
				}
			}
			if(w_dept_province!=null && ! w_dept_province.trim().equals(""))
			{
				String province=w_dept_province.trim();
				List list=cvtDao.findByName(province);
				if(list.size()>0)
				{
					provinceCvterm=(Cvterm)list.get(0);
					hasPloc=true;
				}
			}
			
			if(countryCvterm!=null)
			{
				List list=plocDao.findByProperty("cvtermByCountryId",countryCvterm);
				if(list.size()>0)
				{
					if(provinceCvterm!=null)
					{
						for(int i=0;i<list.size();i++)
						{
							Politlocation temploc=(Politlocation)list.get(i);
							if(temploc.getCvtermByProvinceId()!=null && temploc.getCvtermByProvinceId().getName().equals(provinceCvterm.getName()))
							{
								ploc=temploc;
								i=list.size();
							}
						}
					}
					else
					{
						for(int i=0;i<list.size();i++)
						{
							Politlocation temploc=(Politlocation)list.get(i);
							if(temploc.getCvtermByProvinceId()==null)
							{
								ploc=temploc;
								i=list.size();
							}
						}
					}
				}
			}		
			else //countryCvterm==null
			{
				if(provinceCvterm!=null)
				{
					List list=plocDao.findByProperty("cvtermByProvinceId",provinceCvterm);
					for(int i=0;i<list.size();i++)
					{
						Politlocation temploc=(Politlocation)list.get(i);
						if(temploc.getCvtermByCountryId()==null)
						{
							ploc=temploc;
							i=list.size();
						}
					}
				}
			}
			if(hasPloc && ploc==null)	
			{
				ploc=new Politlocation();
				if(countryCvterm!=null)
				{
					ploc.setCvtermByCountryId(countryCvterm);
					countryCvterm.getPolitlocationsForCountryId().add(ploc);
				}
				if(provinceCvterm!=null)
				{
					ploc.setCvtermByProvinceId(provinceCvterm);
					provinceCvterm.getPolitlocationsForProvinceId().add(ploc);
				}
			}
			
			if(ploc!=null)
			{
				location.setPolitlocation(ploc);
				ploc.getGeolocations().add(location);
			}
			
			indv.setGeolocation(location);
			location.getIndividuals().add(indv);
			
			//set collection date
			
			
			System.out.println(count+": importing individual: "+individual_name);
			count++;
			sess.save(indv);
			//sess.flush();
			//tr.commit();
			
		}
		
		stm.close();
		rs.close();
		conn.close();
		
		
		sess.flush();
		tr.commit();
		sess.close();
	}
	
	public void importCountryProvince() throws Exception 
	{
		String w_dept_province="";
		String w_country="";
		try
		{
		Session sess=HibernateSessionFactory.getSession();
		Transaction tr=sess.beginTransaction();
		
		String sql="SELECT * from individual";
		
		Connection conn=getConnection();
		
		Statement stm=conn.createStatement();
		ResultSet rs=stm.executeQuery(sql);
		
		CvtermDAO cvtermDAO=new CvtermDAO();
		while(rs.next())
		{
			w_dept_province=rs.getString("w_dept_province");
			w_country=rs.getString("w_country");
			if(w_country!=null && ! w_country.trim().equals(""))
			{
				w_country=w_country.trim();
				List contryList=cvtermDAO.findByName(w_country);
				if(contryList.size()==0)
				{
					Cvterm term=addCvTerm(w_country, "country");
					sess.save(term);
					System.out.println("add county: "+w_country);
				}
			}
			if(w_dept_province!=null && !w_dept_province.trim().equals(""))
			{
				w_dept_province=w_dept_province.trim();
				List contryList=cvtermDAO.findByName(w_dept_province);
				if(contryList.size()==0)
				{
					Cvterm term=addCvTerm(w_dept_province, "province");
					sess.save(term);
					System.out.println("add province: "+w_dept_province);
				}
			}
			
			
		}
		stm.close();
		rs.close();
		conn.close();
		
		
		sess.flush();
		tr.commit();
		sess.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	
	public Cvterm addCvTerm(String term, String namespace)
	{
		
		Cv cv=null;
		CvDAO cvdao=new CvDAO();
		DbDAO dbdao=new DbDAO();
		
		List cvlist=cvdao.findByName(namespace);
		if(cvlist.size()>0)
		{
			cv=(Cv)cvlist.get(0);
			
		}
		else
		{
			cv=new Cv();
			cv.setDefinition(namespace);
			cv.setName(namespace);
		}
		
		Db db=null;
		List dblist=dbdao.findByName(namespace +" db");
		if(dblist.size()>0)
		{
			db=(Db)dblist.get(0);
		}
		else
		{
			db=new Db();
			db.setName(namespace +" db");
		}
		
		Dbxref dbxref=new Dbxref();
		dbxref.setDb(db);
		dbxref.setDescription("Description for " + term +" dbxref.");
		dbxref.setAccession(term);
		dbxref.setVersion("");
		db.getDbxrefs().add(dbxref);
		
		Cvterm cvterm=new Cvterm();
		cvterm.setCv(cv);
		cvterm.setDbxref(dbxref);
		dbxref.getCvterms().add(cvterm);
		cv.getCvterms().add(cvterm);
		cvterm.setDefinition("Definition for " + term);
		cvterm.setName(term);
		cvterm.setIsObsolete(new Integer(0));
		cvterm.setIsRelationshiptype(new Integer(0));
		
		return cvterm;
	}
	
	
	public static void main(String [] agrs)
	{
		try
		{
			ImportMcMillan importmc=new ImportMcMillan();
			//importmc.importOrganism();
			//importmc.importCountryProvince();
			
			importmc.importIndividual();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
