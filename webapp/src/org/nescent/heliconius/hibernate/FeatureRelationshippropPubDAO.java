package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class FeatureRelationshippropPub.
 * @see org.nescent.heliconius.hibernate.FeatureRelationshippropPub
 * @author MyEclipse - Hibernate Tools
 */
public class FeatureRelationshippropPubDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(FeatureRelationshippropPubDAO.class);

	//property constants

    
    public void save(FeatureRelationshippropPub transientInstance) {
        log.debug("saving FeatureRelationshippropPub instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(FeatureRelationshippropPub persistentInstance) {
        log.debug("deleting FeatureRelationshippropPub instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public FeatureRelationshippropPub findById( java.lang.Integer id) {
        log.debug("getting FeatureRelationshippropPub instance with id: " + id);
        try {
            FeatureRelationshippropPub instance = (FeatureRelationshippropPub) getSession()
                    .get("org.nescent.heliconius.hibernate.FeatureRelationshippropPub", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(FeatureRelationshippropPub instance) {
        log.debug("finding FeatureRelationshippropPub instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.FeatureRelationshippropPub")
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
      log.debug("finding FeatureRelationshippropPub instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from FeatureRelationshippropPub as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public FeatureRelationshippropPub merge(FeatureRelationshippropPub detachedInstance) {
        log.debug("merging FeatureRelationshippropPub instance");
        try {
            FeatureRelationshippropPub result = (FeatureRelationshippropPub) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(FeatureRelationshippropPub instance) {
        log.debug("attaching dirty FeatureRelationshippropPub instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(FeatureRelationshippropPub instance) {
        log.debug("attaching clean FeatureRelationshippropPub instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}