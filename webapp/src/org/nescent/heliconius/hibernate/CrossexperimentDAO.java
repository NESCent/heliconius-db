package org.nescent.heliconius.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Crossexperiment.
 * @see org.nescent.heliconius.hibernate.Crossexperiment
 * @author MyEclipse - Hibernate Tools
 */
public class CrossexperimentDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(CrossexperimentDAO.class);

	//property constants
	public static final String NAME = "name";

    
    public void save(Crossexperiment transientInstance) {
        log.debug("saving Crossexperiment instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Crossexperiment persistentInstance) {
        log.debug("deleting Crossexperiment instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Crossexperiment findById( java.lang.Integer id) {
        log.debug("getting Crossexperiment instance with id: " + id);
        try {
            Crossexperiment instance = (Crossexperiment) getSession()
                    .get("org.nescent.heliconius.hibernate.Crossexperiment", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Crossexperiment instance) {
        log.debug("finding Crossexperiment instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Crossexperiment")
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
      log.debug("finding Crossexperiment instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Crossexperiment as model where model." 
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
	
    public Crossexperiment merge(Crossexperiment detachedInstance) {
        log.debug("merging Crossexperiment instance");
        try {
            Crossexperiment result = (Crossexperiment) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Crossexperiment instance) {
        log.debug("attaching dirty Crossexperiment instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Crossexperiment instance) {
        log.debug("attaching clean Crossexperiment instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}