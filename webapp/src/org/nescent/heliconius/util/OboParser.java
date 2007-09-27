package org.nescent.heliconius.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class OboParser {
	String file;
	BufferedReader reader;
	int curPos=0;
	/**
	 * @param file
	 */
	public OboParser(String file) {
		this.file = file;
	}

	/**
	 * @return the file
	 */
	public String getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(String file) {
		this.file = file;
	}

	public String getDefaultNameSpace() throws Exception
	{
		if(reader ==null)
			reader=new BufferedReader(new FileReader(new File(file)));
		
		String str=reader.readLine();
		
		while(str!=null)
		{
			if(str.indexOf("default-namespace:")!=-1)
			{
				String ss[]=str.split(":");
				if(ss.length==2)
				{
					return ss[1].trim();
				}
			}
			str=reader.readLine();
		}
		return null;
	}
	
	public OboTerm nextTerm() throws Exception
	{
		if(reader ==null)
			reader=new BufferedReader(new FileReader(new File(file)));
		OboTerm term=new OboTerm();
		String str=reader.readLine();
		boolean started=false;
		boolean finished=false;
		while(str!=null && !finished)
		{
			if(str.indexOf("[Term]")!=-1)
			{
				if(!started)
					started=true;
				else
					finished=true;
			}
			else if(str.trim().equals(""))
			{
				if(started)
					finished=true;
			}
			else 
			{
				String ss[]=str.split(": ");
				if(ss.length==2)
				{
					if(ss[0].equals("id"))
					{
						String sss[]=ss[1].split(":");
						term.setId(sss[1].trim());
					}
					else if(ss[0].equals("name"))
					{
						term.setName(ss[1].trim());
					}
					else if(ss[0].equals("def"))
					{
						String sss[]=ss[1].split("\\[");
						String def=sss[0].replaceAll("\"","");
						term.setDef(def);
					}
					else if(ss[0].equals("is_a"))
					{
						String sss[]=ss[1].split("!");
						String ssss[]=sss[0].split(":");
						term.setIs_a(ssss[1]);
					}
					
				}
			}
			if(!finished)
				str=reader.readLine();
		}
		if(finished)
			return term;
		
		return null;
	}
}
