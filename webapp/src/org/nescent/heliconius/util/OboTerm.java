package org.nescent.heliconius.util;

public class OboTerm {
	String id; //: ID:0000000
	String name; //: Certainty
	String def; //: "The certainty of assign a biotype to an individual." [Ceitainty:100000]
	String xref_analog; //: Certainty:100000 "Certainty database."
	String is_a; //: ID:0000000 ! Certainty
	OboTerm parent;
	
	public String toString()
	{
		return id+":"+name+":"+def+":"+is_a;
	}
	/**
	 * @return the parent
	 */
	public OboTerm getParent() {
		return parent;
	}
	/**
	 * @param parent the parent to set
	 */
	public void setParent(OboTerm parent) {
		this.parent = parent;
	}
	/**
	 * @return the def
	 */
	public String getDef() {
		return def;
	}
	/**
	 * @param def the def to set
	 */
	public void setDef(String def) {
		this.def = def;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the is_a
	 */
	public String getIs_a() {
		return is_a;
	}
	/**
	 * @param is_a the is_a to set
	 */
	public void setIs_a(String is_a) {
		this.is_a = is_a;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the xref_analog
	 */
	public String getXref_analog() {
		return xref_analog;
	}
	/**
	 * @param xref_analog the xref_analog to set
	 */
	public void setXref_analog(String xref_analog) {
		this.xref_analog = xref_analog;
	}

		
}
