package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class FeaturepropPub.
 * @see org.nescent.heliconius.hibernate.FeaturepropPub
 * @author MyEclipse - Hibernate Tools
 */
public class FeaturepropPubDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(FeaturepropPubDAO.class);

	//property constants

    
    public void save(FeaturepropPub transientInstance) {
        log.debug("saving FeaturepropPub instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(FeaturepropPub persistentInstance) {
        log.debug("deleting FeaturepropPub instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public FeaturepropPub findById( java.lang.Integer id) {
        log.debug("getting FeaturepropPub instance with id: " + id);
        try {
            FeaturepropPub instance = (FeaturepropPub) getSession()
                    .get("org.nescent.heliconius.hibernate.FeaturepropPub", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(FeaturepropPub instance) {
        log.debug("finding FeaturepropPub instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.FeaturepropPub")
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
      log.debug("finding FeaturepropPub instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from FeaturepropPub as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public FeaturepropPub merge(FeaturepropPub detachedInstance) {
        log.debug("merging FeaturepropPub instance");
        try {
            FeaturepropPub result = (FeaturepropPub) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(FeaturepropPub instance) {
        log.debug("attaching dirty FeaturepropPub instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(FeaturepropPub instance) {
        log.debug("attaching clean FeaturepropPub instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}