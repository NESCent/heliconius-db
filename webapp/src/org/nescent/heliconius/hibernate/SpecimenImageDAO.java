package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class SpecimenImage.
 * @see org.nescent.heliconius.hibernate.SpecimenImage
 * @author MyEclipse - Hibernate Tools
 */
public class SpecimenImageDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(SpecimenImageDAO.class);

	//property constants

    
    public void save(SpecimenImage transientInstance) {
        log.debug("saving SpecimenImage instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(SpecimenImage persistentInstance) {
        log.debug("deleting SpecimenImage instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public SpecimenImage findById( java.lang.Integer id) {
        log.debug("getting SpecimenImage instance with id: " + id);
        try {
            SpecimenImage instance = (SpecimenImage) getSession()
                    .get("org.nescent.heliconius.hibernate.SpecimenImage", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(SpecimenImage instance) {
        log.debug("finding SpecimenImage instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.SpecimenImage")
                    .add(Example.create(instance))
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding SpecimenImage instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from SpecimenImage as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public SpecimenImage merge(SpecimenImage detachedInstance) {
        log.debug("merging SpecimenImage instance");
        try {
            SpecimenImage result = (SpecimenImage) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(SpecimenImage instance) {
        log.debug("attaching dirty SpecimenImage instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(SpecimenImage instance) {
        log.debug("attaching clean SpecimenImage instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}