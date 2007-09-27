package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class FeaturePubprop.
 * @see org.nescent.heliconius.hibernate.FeaturePubprop
 * @author MyEclipse - Hibernate Tools
 */
public class FeaturePubpropDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(FeaturePubpropDAO.class);

	//property constants
	public static final String VALUE = "value";
	public static final String RANK = "rank";

    
    public void save(FeaturePubprop transientInstance) {
        log.debug("saving FeaturePubprop instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(FeaturePubprop persistentInstance) {
        log.debug("deleting FeaturePubprop instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public FeaturePubprop findById( java.lang.Integer id) {
        log.debug("getting FeaturePubprop instance with id: " + id);
        try {
            FeaturePubprop instance = (FeaturePubprop) getSession()
                    .get("org.nescent.heliconius.hibernate.FeaturePubprop", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(FeaturePubprop instance) {
        log.debug("finding FeaturePubprop instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.FeaturePubprop")
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
      log.debug("finding FeaturePubprop instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from FeaturePubprop as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByValue(Object value) {
		return findByProperty(VALUE, value);
	}
	
	public List findByRank(Object rank) {
		return findByProperty(RANK, rank);
	}
	
    public FeaturePubprop merge(FeaturePubprop detachedInstance) {
        log.debug("merging FeaturePubprop instance");
        try {
            FeaturePubprop result = (FeaturePubprop) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(FeaturePubprop instance) {
        log.debug("attaching dirty FeaturePubprop instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(FeaturePubprop instance) {
        log.debug("attaching clean FeaturePubprop instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}