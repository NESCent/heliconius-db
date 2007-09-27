package org.nescent.heliconius.hibernate;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class FeatureCvterm.
 * @see org.nescent.heliconius.hibernate.FeatureCvterm
 * @author MyEclipse - Hibernate Tools
 */
public class FeatureCvtermDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(FeatureCvtermDAO.class);

	//property constants
	public static final String IS_NOT = "isNot";

    
    public void save(FeatureCvterm transientInstance) {
        log.debug("saving FeatureCvterm instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(FeatureCvterm persistentInstance) {
        log.debug("deleting FeatureCvterm instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public FeatureCvterm findById( java.lang.Integer id) {
        log.debug("getting FeatureCvterm instance with id: " + id);
        try {
            FeatureCvterm instance = (FeatureCvterm) getSession()
                    .get("org.nescent.heliconius.hibernate.FeatureCvterm", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(FeatureCvterm instance) {
        log.debug("finding FeatureCvterm instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.FeatureCvterm")
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
      log.debug("finding FeatureCvterm instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from FeatureCvterm as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByIsNot(Object isNot) {
		return findByProperty(IS_NOT, isNot);
	}
	
    public FeatureCvterm merge(FeatureCvterm detachedInstance) {
        log.debug("merging FeatureCvterm instance");
        try {
            FeatureCvterm result = (FeatureCvterm) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(FeatureCvterm instance) {
        log.debug("attaching dirty FeatureCvterm instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(FeatureCvterm instance) {
        log.debug("attaching clean FeatureCvterm instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}