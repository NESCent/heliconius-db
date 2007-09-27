package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class FeatureCvtermPub.
 * @see org.nescent.heliconius.hibernate.FeatureCvtermPub
 * @author MyEclipse - Hibernate Tools
 */
public class FeatureCvtermPubDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(FeatureCvtermPubDAO.class);

	//property constants

    
    public void save(FeatureCvtermPub transientInstance) {
        log.debug("saving FeatureCvtermPub instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(FeatureCvtermPub persistentInstance) {
        log.debug("deleting FeatureCvtermPub instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public FeatureCvtermPub findById( java.lang.Integer id) {
        log.debug("getting FeatureCvtermPub instance with id: " + id);
        try {
            FeatureCvtermPub instance = (FeatureCvtermPub) getSession()
                    .get("org.nescent.heliconius.hibernate.FeatureCvtermPub", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(FeatureCvtermPub instance) {
        log.debug("finding FeatureCvtermPub instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.FeatureCvtermPub")
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
      log.debug("finding FeatureCvtermPub instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from FeatureCvtermPub as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public FeatureCvtermPub merge(FeatureCvtermPub detachedInstance) {
        log.debug("merging FeatureCvtermPub instance");
        try {
            FeatureCvtermPub result = (FeatureCvtermPub) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(FeatureCvtermPub instance) {
        log.debug("attaching dirty FeatureCvtermPub instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(FeatureCvtermPub instance) {
        log.debug("attaching clean FeatureCvtermPub instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}