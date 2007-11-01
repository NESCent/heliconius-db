package org.nescent.heliconius.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.nescent.heliconius.hibernate.Biotype;
import org.nescent.heliconius.hibernate.BiotypeDAO;
import org.nescent.heliconius.hibernate.BiotypeOrganism;
import org.nescent.heliconius.hibernate.Crossexperiment;
import org.nescent.heliconius.hibernate.CrossexperimentDAO;
import org.nescent.heliconius.hibernate.Crossexperimentprop;
import org.nescent.heliconius.hibernate.CvDAO;
import org.nescent.heliconius.hibernate.Cvterm;
import org.nescent.heliconius.hibernate.CvtermDAO;
import org.nescent.heliconius.hibernate.Geolocation;
import org.nescent.heliconius.hibernate.HibernateSessionFactory;
import org.nescent.heliconius.hibernate.Individual;
import org.nescent.heliconius.hibernate.IndividualBiotype;
import org.nescent.heliconius.hibernate.IndividualDAO;
import org.nescent.heliconius.hibernate.Organism;
import org.nescent.heliconius.hibernate.OrganismDAO;

public class ImportFromMakefileOutput {
	
	Hashtable individuals;
	Hashtable crosses;
	BufferedWriter writer;
	String pathBase="C:\\heliconiusdb\\trunk\\data\\import\\";
	
	public ImportFromMakefileOutput() throws Exception
	{
		individuals=new Hashtable();
		crosses=new Hashtable();
		writer =new BufferedWriter(new FileWriter(new File(pathBase+"errors.txt")));
		writer.write("where\treason\taction\tid");
		writer.newLine();
		
	}
	
	public void writeError(String message) throws Exception 
	{
		writer.write(message);
		writer.newLine();
		writer.flush();
		
	}
	public void readWilds() throws Exception
	{
		
		//read wild
		String strFile=pathBase+"wild_with_fields.txt";
		BufferedReader reader=new BufferedReader(new FileReader(new File(strFile)));
		String line=reader.readLine();
		line=reader.readLine();
		while(line!=null)
		{
			String values[]=line.split("\t");
			if(values.length!=18)
				writeError("Error while reading wild.\tFiled number not euqal to 18("+values.length+")\tignored\t"+values[0]);
			else
			{
					
				boolean emptyNo=false;
				Wild wild=new Wild();
				
				if(! values[0].trim().equals(""))
					wild.setCollection_No(values[0]);
				else
				{
					if(! values[8].trim().equals(""))
						wild.setCollection_No(values[8]);
					else
						emptyNo=true;
						
				}
				
				if(emptyNo)
					writeError("Error while reading wild.\tEmpty Collection No\tignored\t"+values[0]);
				else
				{
				
					wild.setCollection_Date(values[1]);
					wild.setGenus(values[2]);
					wild.setSpecies(values[3]);
					wild.setRace(values[4]);
					wild.setSex(values[5]);
					wild.setGeneral_Notes(values[6]);
					wild.setWildOrReared(values[7]);
					wild.setCollection_Site(values[9]);
					wild.setProvince(values[10]);
					wild.setCountry(values[11]);
					wild.setLatitude(values[12]);
					wild.setLongitude(values[13]);
					wild.setAltitude(values[14]);
					wild.setCollector(values[15]);
					wild.setCollection_Notes(values[16]);
					wild.setIsWild(values[17]);
					
					individuals.put(values[0], wild);
				}
			}
			line=reader.readLine();
			
		}
		reader.close();
		
	}
	
	public void readReared() throws Exception
	{
		
		//read wild
		String strFile=pathBase+"reared_with_fields.txt";
		BufferedReader reader=new BufferedReader(new FileReader(new File(strFile)));
		String line=reader.readLine();
		line=reader.readLine();
		while(line!=null)
		{
			String values[]=line.split("\t");
			if(values.length!=24 && values.length!=23)
				writeError("Error while reading reared.\tFiled number not euqal to 23 or 24("+values.length+")\tignored\t"+values[0]);
			else
			{
				boolean emptyNo=false;
				Reared reared=new Reared();
				
				if(! values[0].trim().equals(""))
					reared.setCollection_No(values[0]);
				else
				{
					if(! values[8].trim().equals(""))
						reared.setCollection_No(values[8]);
					else
						emptyNo=true;
						
				}
				
				if(emptyNo)
					writeError("Error while reading wild.\tEmpty Collection No\tignored\t"+values[0]);
				else
				{
								
					reared.setCollection_No(values[0]);
					reared.setCollection_Date(values[1]);
					reared.setGenus(values[2]);
					reared.setSpecies(values[3]);
					reared.setRace(values[4]);
					reared.setSex(values[5]);
					reared.setGeneral_Notes(values[6]);
					reared.setWildOrReared(values[7]);
					reared.setOld_Mother(values[9]);
					reared.setOld_Father(values[10]);
					reared.setMember_Brood_No(values[11]);
					reared.setOffspring_No(values[12]);
					reared.setOld_Cross_Type(values[13]);
					reared.setOld_Parent(values[14]);
					reared.setOld_Brood_No(values[15]);
					reared.setRearing_Notes(values[16]);
					reared.setCalculated_Collection_Number(values[17]);
					reared.setCalc_Cross_Type(values[18]);
					reared.setCalc_Mother(values[19]);
					reared.setCalc_Father(values[20]);
					reared.setCalc_Filial_Status(values[21]);
					reared.setIsReared(values[22]);
					if(values.length==24)
						reared.setCreator(values[23]);
	
					
					individuals.put(values[0], reared);
				}
			}
			line=reader.readLine();
			
		}
		reader.close();
		
	}
	
	public void readCrosses() throws Exception
	{
		
		//read wild
		String strFile=pathBase+"cross_with_fields.txt";
		BufferedReader reader=new BufferedReader(new FileReader(new File(strFile)));
		String line=reader.readLine();
		line=reader.readLine();
		while(line!=null)
		{
			String values[]=line.split("\t");
			if(values.length!=15)
				writeError("Error while reading cross.\tFiled number not euqal to 15("+values.length+")\tignored\t"+values[2]);
			else
			{
				Cross cross=new Cross();
				
				cross.setCross_is(values[0]);
				cross.setCross_Type(values[1]);
				cross.setCross_Name(values[2].trim());
				cross.setFemale_Source (values[3]);
				cross.setFemale_Mark(values[4]);
				cross.setFemale_Collection_No(values[5]);
				cross.setMale_Source(values[6]);
				cross.setMale_Mark(values[7]);
				String males=values[8].trim();
				if(males.indexOf(",")>0)
				{
					String ss[]=males.split(",");
					cross.setMale_Collection_No(ss[0].toUpperCase().trim());
				}
				else if(males.indexOf("-")>0)
				{
					String ss[]=males.split("-");
					cross.setMale_Collection_No(ss[0].toUpperCase().trim());
				}
				else if(males.indexOf("\\+")>0)
				{
					String ss[]=males.split("\\+");
					cross.setMale_Collection_No(ss[0].toUpperCase().trim());
				}
				else
					cross.setMale_Collection_No(males.toUpperCase().trim());
				
				cross.setDate_Mated(values[9]);
				cross.setDate_F_Died(values[10]);
				cross.setNo_Eggs(values[11]);
				cross.setNo_Eclosed(values[12]);
				cross.setCross_Notes(values[13]);
				cross.setPercent_Survival(values[14]);

				crosses.put(values[2].trim(), cross);
			}
			line=reader.readLine();
			
		}
		reader.close();
		
	}
	
	public void linkCrossToIndividual() throws Exception
	{
		for(Enumeration e=individuals.keys();e.hasMoreElements();)
		{
			String key=(String)e.nextElement();
			McIndividual indv=(McIndividual)individuals.get(key);
			if(indv instanceof Reared)
			{
				Reared reared=(Reared)indv;
				String cross_id=reared.getMember_Brood_No();
				if(cross_id!=null)
				{
					Cross cross=(Cross)crosses.get(cross_id);
					if(cross!=null)
						reared.setCross(cross);
					else
						writeError("Error while assigning cross to individual\tNo cross found: "+cross_id+"\tignored\t"+key );
				}
				else
					writeError("Error while assigning cross to individual\tCross_id is null\tignored\t"+key );
			}
		}
	}
	
	public void linkParentsToCross() throws Exception
	{
		for(Enumeration e=crosses.keys();e.hasMoreElements();)
		{
			String key=(String)e.nextElement();
			Cross cross=(Cross)crosses.get(key);
			
			String mother_id=cross.getFemale_Collection_No();
					
			if(mother_id!=null)
			{
				McIndividual mother=(McIndividual)individuals.get(mother_id);
				if(mother!=null)
					cross.setMother(mother);
				else
					writeError("Error while assigning mother to cross\tNo individual found: "+mother_id+"\tignored\t"+key );
			}
			else
				writeError("Error while assigning mother to cross\tMother collection no is null\tignored\t"+key );
			
			String father_id=cross.getMale_Collection_No();
			if(father_id!=null)
			{
				McIndividual father=(McIndividual)individuals.get(father_id);
				if(father!=null)
					cross.setFather(father);
				else
					writeError("Error while assigning father to cross\tNo individual found: "+father_id+"\tignored\t"+key );
			}
			else
				writeError("Error while assigning father to cross\tFather collection no is null\tignored\t"+key );
		}
	}
	public void closeFile() throws Exception
	{
		if(writer!=null)
			writer.close();
	}
	
	public void importWildIndividuals() throws Exception
	{
		System.out.println("Importing wild  individual ... ");
		Session sess=HibernateSessionFactory.getSession();
		Transaction tr=sess.beginTransaction();
		
		BiotypeDAO bioDao=new BiotypeDAO();
		OrganismDAO orgDao=new OrganismDAO();
		CvtermDAO cvtDao=new CvtermDAO();
		
		Cvterm verysure=null;
		Cvterm halfsure=null;
		Cvterm unknown=null;
		
		List cvtList = cvtDao.findByName("Certain");
		if(cvtList.size()>0)
			verysure=(Cvterm)cvtList.get(0);
		else
		{
			System.out.println("failed to find cvterm: Certain");
			return;
		}
		
		cvtList = cvtDao.findByName("Confident");
		if(cvtList.size()>0)
			halfsure=(Cvterm)cvtList.get(0);
		else
		{
			System.out.println("failed to find cvterm: Confident");
			return;
		}
		cvtList = cvtDao.findByName("Uncertain");
		if(cvtList.size()>0)
			unknown=(Cvterm)cvtList.get(0);
		else
		{
			System.out.println("failed to find cvterm: Uncertain");
			return;
		}
		
		for(Enumeration e=individuals.keys();e.hasMoreElements();)
		{
			String collection_no=(String)e.nextElement();
			McIndividual indv=(McIndividual)individuals.get(collection_no);
			if(indv instanceof Wild)
			{
				Wild wild=(Wild)indv;
				String genus=wild.getGenus();
				String species=wild.getSpecies();
				String race=wild.getRace();
				String name="";
				if(genus!=null && ! genus.trim().equals(""))
				{
					name=genus.trim();
					
					if(species!=null && ! species.trim().equals(""))
					{
						name+=" " + species.trim();
						if(race!=null && ! race.trim().equals(""))
						{
							name+=" ssp. " + race.trim();
						}	
						
						List bioList=bioDao.findByName(name);
						if(bioList.size()==0)
						{
							Organism org=new Organism();
							org.setAbbreviation(name);
							org.setGenus(genus);
							if(race!=null && ! race.trim().equals(""))
								org.setSpecies(species.trim()+" ssp. "+race.trim());
							else
								org.setSpecies(species.trim());
							
							Biotype biotype=new Biotype();
							biotype.setName(name);
														
							BiotypeOrganism bioOrg=new BiotypeOrganism();
							bioOrg.setBiotype(biotype);
							bioOrg.setOrganism(org);
							org.getBiotypeOrganisms().add(bioOrg);
							biotype.getBiotypeOrganisms().add(bioOrg);
							
							Individual individual =new Individual();
							SimpleDateFormat format=new SimpleDateFormat("MM/dd/yyyy");
							
							try
							{
								individual.setCollectionDate(format.parse(indv.getCollection_Date()));
							}
							catch(Exception ex)
							{
								writeError("Importing wild individual\tError parsing date: "+indv.getCollection_Date()+ "\tignored\t"+collection_no);
							}
							
							individual.setDescription(indv.getGeneral_Notes());
							Geolocation geo=new Geolocation();
							geo.setCountry(wild.getCountry());
							geo.setProvince(wild.getProvince());
							String w_altitude=wild.getAltitude();
							String w_latitude=wild.getLatitude();
							String w_longitude=wild.getLongitude();
							
							if(w_altitude!=null && !w_altitude.trim().equals(""))
							{
								w_altitude=w_altitude.replaceAll("m", "");
								try
								{
									geo.setAltitude(Float.valueOf(w_altitude));	
								}
								catch(Exception ex)
								{
									writeError("Importing wild individual\tError converting altitude: "+wild.getAltitude()+ "\tignored\t"+collection_no);
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
											geo.setLatitude(Float.valueOf(lat));
										}
									}
									else
										geo.setLatitude(Float.valueOf(w_latitude));
								}
							}
							catch(Exception ex)
							{
								writeError("Importing wild individual\tError converting latitude: "+wild.getLatitude()+ "\tignored\t"+collection_no);
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
											geo.setLongitude(Float.valueOf(longigude));
										}
									}
									else
										geo.setLongitude(Float.valueOf(w_longitude));
								}
							}
							catch(Exception ex)
							{
								writeError("Importing wild individual\tError converting longitude: "+wild.getLongitude()+ "\tignored\t"+collection_no);
							}
							individual.setGeolocation(geo);
							
							IndividualBiotype indBio=new IndividualBiotype();
							indBio.setBiotype(biotype);
							indBio.setIndividual(individual);
							indBio.setCvterm(verysure);
							biotype.getIndividualBiotypes().add(indBio);
							individual.getIndividualBiotypes().add(indBio);
							
							individual.setDescription(indv.getGeneral_Notes());
							
							String iswild=indv.getWildOrReared();
							
							if(iswild.equals("t"))
								individual.setIsCaptivityReared(Boolean.FALSE);
							else
								individual.setIsCaptivityReared(Boolean.TRUE);
							
							individual.setName(collection_no);
														
							sess.save(individual);
						}
					}
					else
					{
						writeError("Importing wild individual\tNo species name found for wild collection:"+collection_no+"\tignored\t"+collection_no);
					}
				}
				else
				{
					writeError("Importing wild individual\tNo genus name found for wild collection:"+collection_no+"\tignored\t"+collection_no);
				}
				
			}
		}
		
		sess.flush();
		tr.commit();
		sess.close();
		
		System.out.println("Finished importing wild individual");
	}
	public void importParentsForCross() throws Exception
	{
		System.out.println("Importing parents ... ");
		Session sess=HibernateSessionFactory.getSession();
		Transaction tr=sess.beginTransaction();
		
		IndividualDAO indvDao=new IndividualDAO();
		CrossexperimentDAO expDao=new  CrossexperimentDAO();
		
		for(Enumeration en=crosses.keys();en.hasMoreElements();)
		{
			String cross_id=(String)en.nextElement();
			Cross cross=(Cross)crosses.get(cross_id);
			List crossList=expDao.findByName(cross_id);
			if(crossList.size()>0)
			{
				Crossexperiment exp=(Crossexperiment)crossList.get(0);
				String fid=cross.getMale_Collection_No();
				List indvList=indvDao.findByName(fid);
				if(indvList.size()>0)
				{
					Individual indv=(Individual)indvList.get(0);
					exp.setIndividualByMaleId(indv);
					indv.getCrossexperimentsForMaleId().add(exp);
				}
				
				fid=cross.getFemale_Collection_No();
				indvList=indvDao.findByName(fid);
				if(indvList.size()>0)
				{
					Individual indv=(Individual)indvList.get(0);
					exp.setIndividualByFemaleId(indv);
					indv.getCrossexperimentsForFemaleId().add(exp);
				}
				System.out.println("Updating cross:"+ exp.getName());
				sess.update(exp);
			}
		}
		sess.flush();
		tr.commit();
		sess.close();
		System.out.println("Finished importing parents");
	}
	public void importRearedIndividuals() throws Exception
	{
		System.out.println("Importing reared individual ... ");
		Session sess=HibernateSessionFactory.getSession();
		Transaction tr=sess.beginTransaction();
		
		BiotypeDAO bioDao=new BiotypeDAO();
		OrganismDAO orgDao=new OrganismDAO();
		CvtermDAO cvtDao=new CvtermDAO();
		CrossexperimentDAO crossDao=new CrossexperimentDAO();
		
		Cvterm cross_notes=null;
		Cvterm cross_num_of_eggs=null;
		Cvterm cross_num_of_eclose=null;
		Cvterm cross_date_female_died=null;
		Cvterm verysure=null;
		Cvterm halfsure=null;
		Cvterm unknown=null;
		
		List cvtList = cvtDao.findByName("Certain");
		if(cvtList.size()>0)
			verysure=(Cvterm)cvtList.get(0);
		else
		{
			System.out.println("failed to find cvterm: Certain");
			return;
		}
		
		cvtList = cvtDao.findByName("Confident");
		if(cvtList.size()>0)
			halfsure=(Cvterm)cvtList.get(0);
		else
		{
			System.out.println("failed to find cvterm: Confident");
			return;
		}
		cvtList = cvtDao.findByName("Uncertain");
		if(cvtList.size()>0)
			unknown=(Cvterm)cvtList.get(0);
		else
		{
			System.out.println("failed to find cvterm: Uncertain");
			return;
		}
		
		cvtList = cvtDao.findByName("Date_Female_Died");
		if(cvtList.size()>0)
			cross_date_female_died=(Cvterm)cvtList.get(0);
		else
		{
			System.out.println("failed to find cvterm: Date_Female_Died");
			return;
		}
		
		cvtList = cvtDao.findByName("Cross_Notes");
		if(cvtList.size()>0)
			cross_notes=(Cvterm)cvtList.get(0);
		else
		{
			System.out.println("failed to find cvterm: 	Cross_Notes");
			return;
		}
		cvtList = cvtDao.findByName("Number_Of_Eggs");
		if(cvtList.size()>0)
			cross_num_of_eggs=(Cvterm)cvtList.get(0);
		else
		{
			System.out.println("failed to find cvterm: 	Number_Of_Eggs");
			return;
		}
		
		cvtList = cvtDao.findByName("Number_Of_Eclosed");
		if(cvtList.size()>0)
			cross_num_of_eclose=(Cvterm)cvtList.get(0);
		else
		{
			System.out.println("failed to find cvterm: 	Number_Of_Eclosed");
			return;
		}
		int count=1;
		for(Enumeration e=individuals.keys();e.hasMoreElements();)
		{
			try
			{
			String collection_no=(String)e.nextElement();
			McIndividual indv=(McIndividual)individuals.get(collection_no);
			if(indv instanceof Reared)
			{
				Reared reared=(Reared)indv;
				Individual individual = new Individual();
				SimpleDateFormat format=new SimpleDateFormat("MM/dd/yyyy");
				
				try
				{
					individual.setCollectionDate(format.parse(indv.getCollection_Date()));
				}
				catch(Exception ex)
				{
					writeError("Importing reared individual\tError parsing date: "+indv.getCollection_Date()+ "\tignored\t"+collection_no);
				}
				individual.setDescription(indv.getGeneral_Notes());
				individual.setIsCaptivityReared(Boolean.TRUE);
				individual.setName(collection_no);
				
				//get biotype
				Hashtable names=getNames(indv);
				Biotype biotype=null;
				ArrayList orgs=new ArrayList();
				boolean newOrg=false;
				String bioName="";
				for(Enumeration en=names.keys();en.hasMoreElements();)
				{
					String name=(String)en.nextElement();
					String type=(String)names.get(name);
					
					String ss[]=name.split("@");
					String genus=ss[0];
					String species=ss[1];
					
					name=genus+" "+species;
					if(type.equals("male"))
					{
						if(bioName.equals(""))
							bioName=name;
						else
							bioName+=" x "+name;
					}
					else
					{
						if(bioName.equals(""))
							bioName=name;
						else
							bioName=name+" x "+bioName;
					}
					
					List orgList=orgDao.findByAbbreviation(name);
					if(orgList.size()>0)
					{
						Organism org=(Organism)orgList.get(0);
						orgs.add(org);
					}
					else
					{
						Organism org=new Organism();
						org.setAbbreviation(name);
						org.setGenus(genus);
						org.setSpecies(species);
						newOrg=true;
						orgs.add(org);
					}
				}
				if(bioName.length()>64)
					bioName=bioName.substring(0,63);
				
				List bioList=bioDao.findByName(bioName);
				if(bioList.size()>0)
				{
					biotype=(Biotype)bioList.get(0);
				}
				else
				{
					
					biotype=new Biotype();
					
					biotype.setName(bioName);
					
					for(int i=0;i<orgs.size();i++)
					{
						Organism org=(Organism)orgs.get(i);
						BiotypeOrganism bioOrg=new BiotypeOrganism();
						bioOrg.setBiotype(biotype);
						bioOrg.setOrganism(org);
						org.getBiotypeOrganisms().add(bioOrg);
						biotype.getBiotypeOrganisms().add(bioOrg);
					}
				}
				
				IndividualBiotype indBio=new IndividualBiotype();
				indBio.setIndividual(individual);
				indBio.setBiotype(biotype);
				indBio.setCvterm(verysure);
				individual.getIndividualBiotypes().add(indBio);
				biotype.getIndividualBiotypes().add(indBio);
				
					
				//get cross
				if(reared.getCross()!=null)
				{
					Cross cross=reared.getCross();
					String cross_name=cross.getCross_Name();
					List crossList=crossDao.findByName(cross_name);
					if(crossList.size()>0)
					{
						Crossexperiment crossexp=(Crossexperiment)crossList.get(0);
						individual.setCrossexperiment(crossexp);
						crossexp.getIndividuals().add(individual);
					}
				}
				
				sess.save(individual);
				System.out.println(count+ ": Saving reared individual: "+bioName +"("+collection_no+")");
				count++;
			}
			}
			catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
		}
		sess.flush();
		tr.commit();
		sess.close();
		
		System.out.println("Finished importing reared individual");
	}
	public void importCrosses()
	{
		System.out.println("Importing crosses ... ");
		Session sess=HibernateSessionFactory.getSession();
		Transaction tr=sess.beginTransaction();
		
		IndividualDAO indvDao=new IndividualDAO(); 
		BiotypeDAO bioDao=new BiotypeDAO();
		OrganismDAO orgDao=new OrganismDAO();
		CvtermDAO cvtDao=new CvtermDAO();
		CrossexperimentDAO crossDao=new CrossexperimentDAO();
		
		Cvterm cross_notes=null;
		Cvterm cross_num_of_eggs=null;
		Cvterm cross_num_of_eclose=null;
		Cvterm cross_date_female_died=null;
		Cvterm f1=null;
		Cvterm f2=null;
		Cvterm bx=null;
		Cvterm stock=null;
		
		
		List cvtList = cvtDao.findByName("F1");
		if(cvtList.size()>0)
			f1=(Cvterm)cvtList.get(0);
		else
		{
			System.out.println("failed to find cvterm: F1");
			return;
		}
		
		cvtList = cvtDao.findByName("F2");
		if(cvtList.size()>0)
			f2=(Cvterm)cvtList.get(0);
		else
		{
			System.out.println("failed to find cvterm: 	F2");
			return;
		}
		cvtList = cvtDao.findByName("BX");
		if(cvtList.size()>0)
			bx=(Cvterm)cvtList.get(0);
		else
		{
			System.out.println("failed to find cvterm: 	BX");
			return;
		}
		
		cvtList = cvtDao.findByName("Stock");
		if(cvtList.size()>0)
			stock=(Cvterm)cvtList.get(0);
		else
		{
			System.out.println("failed to find cvterm: 	Stock");
			return;
		}
		cvtList = cvtDao.findByName("Date_Female_Died");
		if(cvtList.size()>0)
			cross_date_female_died=(Cvterm)cvtList.get(0);
		else
		{
			System.out.println("failed to find cvterm: Date_Female_Died");
			return;
		}
		
		cvtList = cvtDao.findByName("Cross_Notes");
		if(cvtList.size()>0)
			cross_notes=(Cvterm)cvtList.get(0);
		else
		{
			System.out.println("failed to find cvterm: 	Cross_Notes");
			return;
		}
		cvtList = cvtDao.findByName("Number_Of_Eggs");
		if(cvtList.size()>0)
			cross_num_of_eggs=(Cvterm)cvtList.get(0);
		else
		{
			System.out.println("failed to find cvterm: 	Number_Of_Eggs");
			return;
		}
		
		cvtList = cvtDao.findByName("Number_Of_Eclosed");
		if(cvtList.size()>0)
			cross_num_of_eclose=(Cvterm)cvtList.get(0);
		else
		{
			System.out.println("failed to find cvterm: 	Number_Of_Eclosed");
			return;
		}
		
		
		for(Enumeration e=crosses.keys();e.hasMoreElements();)
		{
			String name=(String)e.nextElement();
			Cross cross=(Cross)crosses.get(name);
			Crossexperiment crossexp=new Crossexperiment();
			crossexp.setName(name);
			
			String type=cross.getCross_is();
			if(type.equals("F1"))
				crossexp.setCvterm(f1);
			else if(type.equals("F2"))
				crossexp.setCvterm(f2);
			else if(type.equals("BX"))
				crossexp.setCvterm(bx);
			else if(type.equals("Stock"))
				crossexp.setCvterm(stock);
			
			McIndividual indv=cross.getMother();
			if(indv!=null)
			{
				String mother_id=indv.getCollection_No();
				List indvList=indvDao.findByName(mother_id);
				if(indvList.size()>0)
				{
					crossexp.setIndividualByFemaleId((Individual)indvList.get(0));
				}
			}
			indv=cross.getFather();
			if(indv!=null)
			{
				String father_id=indv.getCollection_No();
				List indvList=indvDao.findByName(father_id);
				if(indvList.size()>0)
				{
					crossexp.setIndividualByMaleId((Individual)indvList.get(0));
				}
			}
			SimpleDateFormat sfm=new SimpleDateFormat("MM/dd/yyyy");
			
			String date_mated=cross.getDate_Mated();
			try{
				crossexp.setExpdate(sfm.parse(date_mated));
			}catch(Exception ex)
			{
				System.out.println("Error parsing date: "+ date_mated);
			}
			
			Integer zero=new Integer(0);
			
			//get prop num of eggs
			String eggs=cross.getNo_Eggs();
			try
			{
				Integer num_eggs=Integer.valueOf(eggs);
				Crossexperimentprop prop=new Crossexperimentprop();
				prop.setCvterm(cross_num_of_eggs);
				prop.setValue(num_eggs.toString());
				prop.setRank(zero);
				prop.setCrossexperiment(crossexp);
				
				
				crossexp.getCrossexperimentprops().add(prop);
			}
			catch(Exception ex)
			{
				System.out.println("Error getting number of eggs: "+eggs + " for cross: "+ name);
			}
			
			//get prop num of eclose
			String eclosed=cross.getNo_Eclosed();
			try
			{
				Integer num_eclosed=Integer.valueOf(eclosed);
				Crossexperimentprop prop=new Crossexperimentprop();
				prop.setCvterm(cross_num_of_eclose);
				prop.setValue(num_eclosed.toString());
				prop.setRank(zero);
				prop.setCrossexperiment(crossexp);
				crossexp.getCrossexperimentprops().add(prop);
			}
			catch(Exception ex)
			{
				System.out.println("Error getting number of eclosed: "+eclosed + " for cross: "+ name);
			}
			//get prop notes
			String notes=cross.getCross_Notes();
			try
			{
				Crossexperimentprop prop=new Crossexperimentprop();
				prop.setCvterm(cross_notes);
				prop.setValue(notes);
				prop.setRank(zero);
				prop.setCrossexperiment(crossexp);
				
				crossexp.getCrossexperimentprops().add(prop);
			}
			catch(Exception ex)
			{
				System.out.println("Error getting notes: "+notes + " for cross: "+ name);
			}
			//get prop cross_date_female_died
			String date_female_died=cross.getDate_F_Died();
			try
			{
				Date d=sfm.parse(date_female_died);
				Crossexperimentprop prop=new Crossexperimentprop();
				prop.setCvterm(cross_date_female_died);
				prop.setValue(date_female_died);
				prop.setRank(zero);
				prop.setCrossexperiment(crossexp);
				crossexp.getCrossexperimentprops().add(prop);
			}
			catch(Exception ex)
			{
				System.out.println("Error getting date_female_died): "+date_female_died + " for cross: "+ name);
			}
			
			sess.save(crossexp);
		}
		
		sess.flush();
		tr.commit();
		sess.close();
		
		System.out.println("Finished importing crosses ... ");
	}
	public String getName(McIndividual indv)
	{
		if(indv==null)return null;
		String genus=indv.getGenus();
		String species=indv.getSpecies();
		String race=indv.getRace();
		
		if(species.indexOf(" x ")==-1)
		{
			String name=genus.trim();
			String species_race=species.trim();
			if(race!=null && ! race.trim().equals(""))
				species_race+=" ssp. "+race.trim();
			
			name+="@" + species_race;
			return name;
			
		}
		
		return null;
	}
	public Hashtable getNames(McIndividual indv)
	{
		Hashtable names=new Hashtable();
		String name=getName(indv);
		if(name!=null)
		{
			names.put(name,"self");
		}
		if(indv instanceof Reared)
		{
			Reared reared=(Reared)indv;
			
			if(reared.getCross()!=null)
			{
				Cross cross=reared.getCross();
				Cross cross1=cross;
				String f_source=cross1.getFemale_Source().trim();
				while(crosses.containsKey(f_source))
				{
					cross1=(Cross)crosses.get(f_source);
					f_source=cross1.getFemale_Source().trim();
				}
				
				McIndividual mother=cross1.getMother();
				
				String fname=getName(mother);
				if(fname!=null && ! names.containsKey(fname))
					names.put(fname,"female");
					
				McIndividual father=cross1.getFather();
				fname=getName(father);
				if(fname!=null && ! names.containsKey(fname))
					names.put(fname,"female");
					
				Cross cross2=cross;
				String m_source=cross2.getMale_Source().trim();
				while(crosses.containsKey(m_source))
				{
					cross2=(Cross)crosses.get(m_source);
					m_source=cross2.getMale_Source().trim();
				}
				mother=cross2.getMother();
				fname=getName(mother);
				if(fname!=null && ! names.containsKey(fname))
					names.put(fname,"male");
					
				father=cross2.getFather();
				fname=getName(father);
				if(fname!=null && ! names.containsKey(fname))
					names.put(fname,"male");
			}
		}
		return names;
	}
	public void importRearedBiotypes() throws Exception
	{
		System.out.println("Importing reared biotypes ... ");
		Session sess=HibernateSessionFactory.getSession();
		Transaction tr=sess.beginTransaction();
		
		BiotypeDAO bioDao=new BiotypeDAO();
		OrganismDAO orgDao=new OrganismDAO();
		
		for(Enumeration e=individuals.keys();e.hasMoreElements();)
		{
			String collection_no=(String)e.nextElement();
			McIndividual indv=(McIndividual)individuals.get(collection_no);
			if(indv instanceof Reared)
			{
				Reared reared=(Reared)indv;
				if(reared.getSpecies().indexOf(" x ")==-1)
				{
					String genus=reared.getGenus();
					String species=reared.getSpecies();
					String race=reared.getRace();
					String name="";
					if(genus!=null && ! genus.trim().equals(""))
					{
						name=genus.trim();
						
						if(species!=null && ! species.trim().equals(""))
						{
							name+=" " + species.trim();
							if(race!=null && ! race.trim().equals(""))
							{
								name+=" ssp. " + race.trim();
							}	
							
							List bioList=bioDao.findByName(name);
							if(bioList.size()==0)
							{
								Organism org=null;
								List orgList=orgDao.findByAbbreviation(name);
								if(orgList.size()>0)
									org=(Organism)orgList.get(0);
								else
								{
									org=new Organism();
									org.setAbbreviation(name);
									org.setGenus(genus);
									if(race!=null && ! race.trim().equals(""))
										org.setSpecies(species.trim()+" ssp. "+race.trim());
									else
										org.setSpecies(species.trim());
								}
								Biotype biotype=new Biotype();
								biotype.setName(name);
															
								BiotypeOrganism bioOrg=new BiotypeOrganism();
								bioOrg.setBiotype(biotype);
								bioOrg.setOrganism(org);
								org.getBiotypeOrganisms().add(bioOrg);
								biotype.getBiotypeOrganisms().add(bioOrg);
								
								sess.save(biotype);
							}
						}
						else
						{
							writeError("Importing reared biotype\tNo species name found for reared collection:"+collection_no+"\tignored\t"+collection_no);
						}
					}
					else
					{
						writeError("Importing reared biotype\tNo genus name found for reared collection:"+collection_no+"\tignored\t"+collection_no);
					}
				}
			}
		}
		sess.flush();
		tr.commit();
		sess.close();
		
		System.out.println("Finished importing reared biotypes");
	}
	public boolean containsWild(String genus, String species, String race)
	{
		for(Enumeration e=individuals.keys();e.hasMoreElements();)
		{
			String collection_no=(String)e.nextElement();
			McIndividual indv=(McIndividual)individuals.get(collection_no);
			if(indv instanceof Wild)
			{
				if(indv.getGenus().equals(genus) && 
					indv.getSpecies().equals(species) &&
					indv.getRace().equals(race))
					
					return true;
					
			}
		}	
		
		return false;
	}
	
	
	public static void main(String [] agrs)
	{
		ImportFromMakefileOutput importData = null;
		try
		{
			importData = new ImportFromMakefileOutput();
			importData.readWilds();
			importData.readReared();
			importData.readCrosses();
			importData.linkParentsToCross();
			importData.linkCrossToIndividual();
			importData.importWildIndividuals();
			importData.importRearedBiotypes();
			importData.importCrosses();
			importData.importRearedIndividuals();
			importData.importParentsForCross();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally{
			if(importData!=null)
			{
				try
				{
					importData.closeFile();
				}
				catch(Exception ex)
				{
					System.out.println(ex);
				}
			}
		}
	}
}
