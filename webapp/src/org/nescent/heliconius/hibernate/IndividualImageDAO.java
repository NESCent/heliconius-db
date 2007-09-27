package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class IndividualImage.
 * @see org.nescent.heliconius.hibernate.IndividualImage
 * @author MyEclipse - Hibernate Tools
 */
public class IndividualImageDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(IndividualImageDAO.class);

	//property constants

    
    public void save(IndividualImage transientInstance) {
        log.debug("saving IndividualImage instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(IndividualImage persistentInstance) {
        log.debug("deleting IndividualImage instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public IndividualImage findById( java.lang.Integer id) {
        log.debug("getting IndividualImage instance with id: " + id);
        try {
            IndividualImage instance = (IndividualImage) getSession()
                    .get("org.nescent.heliconius.hibernate.IndividualImage", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(IndividualImage instance) {
        log.debug("finding IndividualImage instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.IndividualImage")
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
      log.debug("finding IndividualImage instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from IndividualImage as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public IndividualImage merge(IndividualImage detachedInstance) {
        log.debug("merging IndividualImage instance");
        try {
            IndividualImage result = (IndividualImage) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(IndividualImage instance) {
        log.debug("attaching dirty IndividualImage instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(IndividualImage instance) {
        log.debug("attaching clean IndividualImage instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}