package org.nescent.heliconius.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.nescent.heliconius.hibernate.Cv;
import org.nescent.heliconius.hibernate.CvDAO;
import org.nescent.heliconius.hibernate.Cvterm;
import org.nescent.heliconius.hibernate.Db;
import org.nescent.heliconius.hibernate.DbDAO;
import org.nescent.heliconius.hibernate.Dbxref;
import org.nescent.heliconius.hibernate.HibernateSessionFactory;

public class ImportObo {

	
	public void importObo(String oboFile) throws Exception
	{
		Session sess=HibernateSessionFactory.getSession();
		Transaction tr= sess.beginTransaction();
		
		OboParser parser=new OboParser(oboFile);
		String namespace=parser.getDefaultNameSpace();
		
		DbDAO dbDao = new DbDAO();
		CvDAO cvDao=new CvDAO();
		
		if(namespace!=null)
		{
			System.out.println("Namespace: " + namespace);
			List dbList=dbDao.findByName(namespace);
			Db db=null;
			if(dbList.size()>0)
			{
				db=(Db)dbList.get(0);
			}
			else
			{
				db=new Db();
				db.setName(namespace);
				db.setDescription(namespace);
			}
			
			Cv cv=null;
			List cvList = cvDao.findByName(namespace);
			if(cvList.size()>0)
			{
				cv=(Cv)cvList.get(0);
			}
			else
			{
				cv=new Cv();
				cv.setName(namespace);
				cv.setDefinition(namespace);
			}			
			
			OboTerm term=parser.nextTerm();
			while(term!=null)
			{
				System.out.println(term.toString());
				
				Dbxref dbxref=new Dbxref();
				dbxref.setDb(db);
				dbxref.setAccession(namespace+":"+term.getId());
				dbxref.setDescription(term.getDef());
				dbxref.setVersion("0");
				
				db.getDbxrefs().add(dbxref);
				
				Cvterm cvterm=new Cvterm();
				cvterm.setCv(cv);
				cvterm.setDbxref(dbxref);
				cvterm.setDefinition(term.getDef());
				cvterm.setName(term.getName());
				cvterm.setIsObsolete(new Integer(0));
				cvterm.setIsRelationshiptype(new Integer(0));
				cv.getCvterms().add(cvterm);
				
				sess.save(cv);
				term=parser.nextTerm();
			}
			
			
		}
		sess.flush();
		tr.commit();
		sess.close();
	}
	
	public static void main(String [] agrs)
	{
		try
		{
			String file="C:\\Documents and Settings\\xianhua\\My Documents\\heliconius.obo";
			ImportObo importObo=new ImportObo();
			importObo.importObo(file);
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
}
