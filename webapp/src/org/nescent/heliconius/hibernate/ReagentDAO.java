package org.nescent.heliconius.hibernate;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Reagent.
 * @see org.nescent.heliconius.hibernate.Reagent
 * @author MyEclipse - Hibernate Tools
 */
public class ReagentDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(ReagentDAO.class);

	//property constants
	public static final String NAME = "name";

    
    public void save(Reagent transientInstance) {
        log.debug("saving Reagent instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Reagent persistentInstance) {
        log.debug("deleting Reagent instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Reagent findById( java.lang.Integer id) {
        log.debug("getting Reagent instance with id: " + id);
        try {
            Reagent instance = (Reagent) getSession()
                    .get("org.nescent.heliconius.hibernate.Reagent", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Reagent instance) {
        log.debug("finding Reagent instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Reagent")
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
      log.debug("finding Reagent instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Reagent as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}
	
    public Reagent merge(Reagent detachedInstance) {
        log.debug("merging Reagent instance");
        try {
            Reagent result = (Reagent) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Reagent instance) {
        log.debug("attaching dirty Reagent instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Reagent instance) {
        log.debug("attaching clean Reagent instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}