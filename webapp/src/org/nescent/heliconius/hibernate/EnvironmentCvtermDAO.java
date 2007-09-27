package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class EnvironmentCvterm.
 * @see org.nescent.heliconius.hibernate.EnvironmentCvterm
 * @author MyEclipse - Hibernate Tools
 */
public class EnvironmentCvtermDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(EnvironmentCvtermDAO.class);

	//property constants

    
    public void save(EnvironmentCvterm transientInstance) {
        log.debug("saving EnvironmentCvterm instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(EnvironmentCvterm persistentInstance) {
        log.debug("deleting EnvironmentCvterm instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public EnvironmentCvterm findById( java.lang.Integer id) {
        log.debug("getting EnvironmentCvterm instance with id: " + id);
        try {
            EnvironmentCvterm instance = (EnvironmentCvterm) getSession()
                    .get("org.nescent.heliconius.hibernate.EnvironmentCvterm", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(EnvironmentCvterm instance) {
        log.debug("finding EnvironmentCvterm instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.EnvironmentCvterm")
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
      log.debug("finding EnvironmentCvterm instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from EnvironmentCvterm as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public EnvironmentCvterm merge(EnvironmentCvterm detachedInstance) {
        log.debug("merging EnvironmentCvterm instance");
        try {
            EnvironmentCvterm result = (EnvironmentCvterm) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(EnvironmentCvterm instance) {
        log.debug("attaching dirty EnvironmentCvterm instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(EnvironmentCvterm instance) {
        log.debug("attaching clean EnvironmentCvterm instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}