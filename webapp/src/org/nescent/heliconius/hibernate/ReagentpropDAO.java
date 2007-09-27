package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Reagentprop.
 * @see org.nescent.heliconius.hibernate.Reagentprop
 * @author MyEclipse - Hibernate Tools
 */
public class ReagentpropDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(ReagentpropDAO.class);

	//property constants
	public static final String VALUE = "value";
	public static final String RANK = "rank";

    
    public void save(Reagentprop transientInstance) {
        log.debug("saving Reagentprop instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Reagentprop persistentInstance) {
        log.debug("deleting Reagentprop instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Reagentprop findById( java.lang.Integer id) {
        log.debug("getting Reagentprop instance with id: " + id);
        try {
            Reagentprop instance = (Reagentprop) getSession()
                    .get("org.nescent.heliconius.hibernate.Reagentprop", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Reagentprop instance) {
        log.debug("finding Reagentprop instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Reagentprop")
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
      log.debug("finding Reagentprop instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Reagentprop as model where model." 
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
	
    public Reagentprop merge(Reagentprop detachedInstance) {
        log.debug("merging Reagentprop instance");
        try {
            Reagentprop result = (Reagentprop) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Reagentprop instance) {
        log.debug("attaching dirty Reagentprop instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Reagentprop instance) {
        log.debug("attaching clean Reagentprop instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}