package org.nescent.heliconius.hibernate;



/**
 * IndividualImage generated by MyEclipse - Hibernate Tools
 */

public class IndividualImage  implements java.io.Serializable {


    // Fields    

     private Integer individualImageId;
     private Individual individual;
     private Image image;


    // Constructors

    /** default constructor */
    public IndividualImage() {
    }

    
    /** full constructor */
    public IndividualImage(Individual individual, Image image) {
        this.individual = individual;
        this.image = image;
    }

   
    // Property accessors

    public Integer getIndividualImageId() {
        return this.individualImageId;
    }
    
    public void setIndividualImageId(Integer individualImageId) {
        this.individualImageId = individualImageId;
    }

    public Individual getIndividual() {
        return this.individual;
    }
    
    public void setIndividual(Individual individual) {
        this.individual = individual;
    }

    public Image getImage() {
        return this.image;
    }
    
    public void setImage(Image image) {
        this.image = image;
    }
   








}