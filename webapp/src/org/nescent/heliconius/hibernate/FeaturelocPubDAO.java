package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class FeaturelocPub.
 * @see org.nescent.heliconius.hibernate.FeaturelocPub
 * @author MyEclipse - Hibernate Tools
 */
public class FeaturelocPubDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(FeaturelocPubDAO.class);

	//property constants

    
    public void save(FeaturelocPub transientInstance) {
        log.debug("saving FeaturelocPub instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(FeaturelocPub persistentInstance) {
        log.debug("deleting FeaturelocPub instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public FeaturelocPub findById( java.lang.Integer id) {
        log.debug("getting FeaturelocPub instance with id: " + id);
        try {
            FeaturelocPub instance = (FeaturelocPub) getSession()
                    .get("org.nescent.heliconius.hibernate.FeaturelocPub", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(FeaturelocPub instance) {
        log.debug("finding FeaturelocPub instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.FeaturelocPub")
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
      log.debug("finding FeaturelocPub instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from FeaturelocPub as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public FeaturelocPub merge(FeaturelocPub detachedInstance) {
        log.debug("merging FeaturelocPub instance");
        try {
            FeaturelocPub result = (FeaturelocPub) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(FeaturelocPub instance) {
        log.debug("attaching dirty FeaturelocPub instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(FeaturelocPub instance) {
        log.debug("attaching clean FeaturelocPub instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}