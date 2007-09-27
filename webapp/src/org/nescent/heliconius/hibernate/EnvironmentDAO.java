package org.nescent.heliconius.hibernate;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Environment.
 * @see org.nescent.heliconius.hibernate.Environment
 * @author MyEclipse - Hibernate Tools
 */
public class EnvironmentDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(EnvironmentDAO.class);

	//property constants
	public static final String UNIQUENAME = "uniquename";
	public static final String DESCRIPTION = "description";

    
    public void save(Environment transientInstance) {
        log.debug("saving Environment instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Environment persistentInstance) {
        log.debug("deleting Environment instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Environment findById( java.lang.Integer id) {
        log.debug("getting Environment instance with id: " + id);
        try {
            Environment instance = (Environment) getSession()
                    .get("org.nescent.heliconius.hibernate.Environment", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Environment instance) {
        log.debug("finding Environment instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Environment")
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
      log.debug("finding Environment instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Environment as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByUniquename(Object uniquename) {
		return findByProperty(UNIQUENAME, uniquename);
	}
	
	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}
	
    public Environment merge(Environment detachedInstance) {
        log.debug("merging Environment instance");
        try {
            Environment result = (Environment) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Environment instance) {
        log.debug("attaching dirty Environment instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Environment instance) {
        log.debug("attaching clean Environment instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}