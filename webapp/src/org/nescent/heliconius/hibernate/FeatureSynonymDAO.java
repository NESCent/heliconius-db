package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class FeatureSynonym.
 * @see org.nescent.heliconius.hibernate.FeatureSynonym
 * @author MyEclipse - Hibernate Tools
 */
public class FeatureSynonymDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(FeatureSynonymDAO.class);

	//property constants
	public static final String IS_CURRENT = "isCurrent";
	public static final String IS_INTERNAL = "isInternal";

    
    public void save(FeatureSynonym transientInstance) {
        log.debug("saving FeatureSynonym instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(FeatureSynonym persistentInstance) {
        log.debug("deleting FeatureSynonym instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public FeatureSynonym findById( java.lang.Integer id) {
        log.debug("getting FeatureSynonym instance with id: " + id);
        try {
            FeatureSynonym instance = (FeatureSynonym) getSession()
                    .get("org.nescent.heliconius.hibernate.FeatureSynonym", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(FeatureSynonym instance) {
        log.debug("finding FeatureSynonym instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.FeatureSynonym")
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
      log.debug("finding FeatureSynonym instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from FeatureSynonym as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByIsCurrent(Object isCurrent) {
		return findByProperty(IS_CURRENT, isCurrent);
	}
	
	public List findByIsInternal(Object isInternal) {
		return findByProperty(IS_INTERNAL, isInternal);
	}
	
    public FeatureSynonym merge(FeatureSynonym detachedInstance) {
        log.debug("merging FeatureSynonym instance");
        try {
            FeatureSynonym result = (FeatureSynonym) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(FeatureSynonym instance) {
        log.debug("attaching dirty FeatureSynonym instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(FeatureSynonym instance) {
        log.debug("attaching clean FeatureSynonym instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}