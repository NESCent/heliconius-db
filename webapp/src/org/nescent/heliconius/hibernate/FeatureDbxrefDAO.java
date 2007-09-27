package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class FeatureDbxref.
 * @see org.nescent.heliconius.hibernate.FeatureDbxref
 * @author MyEclipse - Hibernate Tools
 */
public class FeatureDbxrefDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(FeatureDbxrefDAO.class);

	//property constants
	public static final String IS_CURRENT = "isCurrent";

    
    public void save(FeatureDbxref transientInstance) {
        log.debug("saving FeatureDbxref instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(FeatureDbxref persistentInstance) {
        log.debug("deleting FeatureDbxref instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public FeatureDbxref findById( java.lang.Integer id) {
        log.debug("getting FeatureDbxref instance with id: " + id);
        try {
            FeatureDbxref instance = (FeatureDbxref) getSession()
                    .get("org.nescent.heliconius.hibernate.FeatureDbxref", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(FeatureDbxref instance) {
        log.debug("finding FeatureDbxref instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.FeatureDbxref")
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
      log.debug("finding FeatureDbxref instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from FeatureDbxref as model where model." 
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
	
    public FeatureDbxref merge(FeatureDbxref detachedInstance) {
        log.debug("merging FeatureDbxref instance");
        try {
            FeatureDbxref result = (FeatureDbxref) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(FeatureDbxref instance) {
        log.debug("attaching dirty FeatureDbxref instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(FeatureDbxref instance) {
        log.debug("attaching clean FeatureDbxref instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}