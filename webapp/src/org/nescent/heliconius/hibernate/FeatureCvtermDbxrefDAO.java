package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class FeatureCvtermDbxref.
 * @see org.nescent.heliconius.hibernate.FeatureCvtermDbxref
 * @author MyEclipse - Hibernate Tools
 */
public class FeatureCvtermDbxrefDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(FeatureCvtermDbxrefDAO.class);

	//property constants

    
    public void save(FeatureCvtermDbxref transientInstance) {
        log.debug("saving FeatureCvtermDbxref instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(FeatureCvtermDbxref persistentInstance) {
        log.debug("deleting FeatureCvtermDbxref instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public FeatureCvtermDbxref findById( java.lang.Integer id) {
        log.debug("getting FeatureCvtermDbxref instance with id: " + id);
        try {
            FeatureCvtermDbxref instance = (FeatureCvtermDbxref) getSession()
                    .get("org.nescent.heliconius.hibernate.FeatureCvtermDbxref", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(FeatureCvtermDbxref instance) {
        log.debug("finding FeatureCvtermDbxref instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.FeatureCvtermDbxref")
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
      log.debug("finding FeatureCvtermDbxref instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from FeatureCvtermDbxref as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public FeatureCvtermDbxref merge(FeatureCvtermDbxref detachedInstance) {
        log.debug("merging FeatureCvtermDbxref instance");
        try {
            FeatureCvtermDbxref result = (FeatureCvtermDbxref) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(FeatureCvtermDbxref instance) {
        log.debug("attaching dirty FeatureCvtermDbxref instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(FeatureCvtermDbxref instance) {
        log.debug("attaching clean FeatureCvtermDbxref instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}