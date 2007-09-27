package org.nescent.heliconius.hibernate;



/**
 * Cvtermpath generated by MyEclipse - Hibernate Tools
 */

public class Cvtermpath  implements java.io.Serializable {


    // Fields    

     private Integer cvtermpathId;
     private Cvterm cvtermBySubjectId;
     private Cvterm cvtermByObjectId;
     private Cvterm cvtermByTypeId;
     private Cv cv;
     private Integer pathdistance;


    // Constructors

    /** default constructor */
    public Cvtermpath() {
    }

	/** minimal constructor */
    public Cvtermpath(Cvterm cvtermBySubjectId, Cvterm cvtermByObjectId, Cv cv) {
        this.cvtermBySubjectId = cvtermBySubjectId;
        this.cvtermByObjectId = cvtermByObjectId;
        this.cv = cv;
    }
    
    /** full constructor */
    public Cvtermpath(Cvterm cvtermBySubjectId, Cvterm cvtermByObjectId, Cvterm cvtermByTypeId, Cv cv, Integer pathdistance) {
        this.cvtermBySubjectId = cvtermBySubjectId;
        this.cvtermByObjectId = cvtermByObjectId;
        this.cvtermByTypeId = cvtermByTypeId;
        this.cv = cv;
        this.pathdistance = pathdistance;
    }

   
    // Property accessors

    public Integer getCvtermpathId() {
        return this.cvtermpathId;
    }
    
    public void setCvtermpathId(Integer cvtermpathId) {
        this.cvtermpathId = cvtermpathId;
    }

    public Cvterm getCvtermBySubjectId() {
        return this.cvtermBySubjectId;
    }
    
    public void setCvtermBySubjectId(Cvterm cvtermBySubjectId) {
        this.cvtermBySubjectId = cvtermBySubjectId;
    }

    public Cvterm getCvtermByObjectId() {
        return this.cvtermByObjectId;
    }
    
    public void setCvtermByObjectId(Cvterm cvtermByObjectId) {
        this.cvtermByObjectId = cvtermByObjectId;
    }

    public Cvterm getCvtermByTypeId() {
        return this.cvtermByTypeId;
    }
    
    public void setCvtermByTypeId(Cvterm cvtermByTypeId) {
        this.cvtermByTypeId = cvtermByTypeId;
    }

    public Cv getCv() {
        return this.cv;
    }
    
    public void setCv(Cv cv) {
        this.cv = cv;
    }

    public Integer getPathdistance() {
        return this.pathdistance;
    }
    
    public void setPathdistance(Integer pathdistance) {
        this.pathdistance = pathdistance;
    }
   








}