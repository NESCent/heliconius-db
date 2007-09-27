package org.nescent.heliconius.hibernate;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class FeaturePub.
 * @see org.nescent.heliconius.hibernate.FeaturePub
 * @author MyEclipse - Hibernate Tools
 */
public class FeaturePubDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(FeaturePubDAO.class);

	//property constants

    
    public void save(FeaturePub transientInstance) {
        log.debug("saving FeaturePub instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(FeaturePub persistentInstance) {
        log.debug("deleting FeaturePub instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public FeaturePub findById( java.lang.Integer id) {
        log.debug("getting FeaturePub instance with id: " + id);
        try {
            FeaturePub instance = (FeaturePub) getSession()
                    .get("org.nescent.heliconius.hibernate.FeaturePub", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(FeaturePub instance) {
        log.debug("finding FeaturePub instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.FeaturePub")
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
      log.debug("finding FeaturePub instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from FeaturePub as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public FeaturePub merge(FeaturePub detachedInstance) {
        log.debug("merging FeaturePub instance");
        try {
            FeaturePub result = (FeaturePub) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(FeaturePub instance) {
        log.debug("attaching dirty FeaturePub instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(FeaturePub instance) {
        log.debug("attaching clean FeaturePub instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}