package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Crossexperimentprop.
 * @see org.nescent.heliconius.hibernate.Crossexperimentprop
 * @author MyEclipse - Hibernate Tools
 */
public class CrossexperimentpropDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(CrossexperimentpropDAO.class);

	//property constants
	public static final String VALUE = "value";
	public static final String RANK = "rank";

    
    public void save(Crossexperimentprop transientInstance) {
        log.debug("saving Crossexperimentprop instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Crossexperimentprop persistentInstance) {
        log.debug("deleting Crossexperimentprop instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Crossexperimentprop findById( java.lang.Integer id) {
        log.debug("getting Crossexperimentprop instance with id: " + id);
        try {
            Crossexperimentprop instance = (Crossexperimentprop) getSession()
                    .get("org.nescent.heliconius.hibernate.Crossexperimentprop", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Crossexperimentprop instance) {
        log.debug("finding Crossexperimentprop instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Crossexperimentprop")
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
      log.debug("finding Crossexperimentprop instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Crossexperimentprop as model where model." 
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
	
    public Crossexperimentprop merge(Crossexperimentprop detachedInstance) {
        log.debug("merging Crossexperimentprop instance");
        try {
            Crossexperimentprop result = (Crossexperimentprop) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Crossexperimentprop instance) {
        log.debug("attaching dirty Crossexperimentprop instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Crossexperimentprop instance) {
        log.debug("attaching clean Crossexperimentprop instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}