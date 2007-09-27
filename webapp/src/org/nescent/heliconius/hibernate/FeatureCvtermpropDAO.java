package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class FeatureCvtermprop.
 * @see org.nescent.heliconius.hibernate.FeatureCvtermprop
 * @author MyEclipse - Hibernate Tools
 */
public class FeatureCvtermpropDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(FeatureCvtermpropDAO.class);

	//property constants
	public static final String VALUE = "value";
	public static final String RANK = "rank";

    
    public void save(FeatureCvtermprop transientInstance) {
        log.debug("saving FeatureCvtermprop instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(FeatureCvtermprop persistentInstance) {
        log.debug("deleting FeatureCvtermprop instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public FeatureCvtermprop findById( java.lang.Integer id) {
        log.debug("getting FeatureCvtermprop instance with id: " + id);
        try {
            FeatureCvtermprop instance = (FeatureCvtermprop) getSession()
                    .get("org.nescent.heliconius.hibernate.FeatureCvtermprop", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(FeatureCvtermprop instance) {
        log.debug("finding FeatureCvtermprop instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.FeatureCvtermprop")
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
      log.debug("finding FeatureCvtermprop instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from FeatureCvtermprop as model where model." 
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
	
    public FeatureCvtermprop merge(FeatureCvtermprop detachedInstance) {
        log.debug("merging FeatureCvtermprop instance");
        try {
            FeatureCvtermprop result = (FeatureCvtermprop) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(FeatureCvtermprop instance) {
        log.debug("attaching dirty FeatureCvtermprop instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(FeatureCvtermprop instance) {
        log.debug("attaching clean FeatureCvtermprop instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}