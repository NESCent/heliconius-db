package org.nescent.heliconius.hibernate;



/**
 * Pubauthor generated by MyEclipse - Hibernate Tools
 */

public class Pubauthor  implements java.io.Serializable {


    // Fields    

     private Integer pubauthorId;
     private Pub pub;
     private Integer rank;
     private Byte editor;
     private String surname;
     private String givennames;
     private String suffix;


    // Constructors

    /** default constructor */
    public Pubauthor() {
    }

	/** minimal constructor */
    public Pubauthor(Pub pub, Integer rank, String surname) {
        this.pub = pub;
        this.rank = rank;
        this.surname = surname;
    }
    
    /** full constructor */
    public Pubauthor(Pub pub, Integer rank, Byte editor, String surname, String givennames, String suffix) {
        this.pub = pub;
        this.rank = rank;
        this.editor = editor;
        this.surname = surname;
        this.givennames = givennames;
        this.suffix = suffix;
    }

   
    // Property accessors

    public Integer getPubauthorId() {
        return this.pubauthorId;
    }
    
    public void setPubauthorId(Integer pubauthorId) {
        this.pubauthorId = pubauthorId;
    }

    public Pub getPub() {
        return this.pub;
    }
    
    public void setPub(Pub pub) {
        this.pub = pub;
    }

    public Integer getRank() {
        return this.rank;
    }
    
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Byte getEditor() {
        return this.editor;
    }
    
    public void setEditor(Byte editor) {
        this.editor = editor;
    }

    public String getSurname() {
        return this.surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGivennames() {
        return this.givennames;
    }
    
    public void setGivennames(String givennames) {
        this.givennames = givennames;
    }

    public String getSuffix() {
        return this.suffix;
    }
    
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
   








}