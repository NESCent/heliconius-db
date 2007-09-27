package org.nescent.heliconius.hibernate;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Featureprop.
 * @see org.nescent.heliconius.hibernate.Featureprop
 * @author MyEclipse - Hibernate Tools
 */
public class FeaturepropDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(FeaturepropDAO.class);

	//property constants
	public static final String VALUE = "value";
	public static final String RANK = "rank";

    
    public void save(Featureprop transientInstance) {
        log.debug("saving Featureprop instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Featureprop persistentInstance) {
        log.debug("deleting Featureprop instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Featureprop findById( java.lang.Integer id) {
        log.debug("getting Featureprop instance with id: " + id);
        try {
            Featureprop instance = (Featureprop) getSession()
                    .get("org.nescent.heliconius.hibernate.Featureprop", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Featureprop instance) {
        log.debug("finding Featureprop instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Featureprop")
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
      log.debug("finding Featureprop instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Featureprop as model where model." 
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
	
    public Featureprop merge(Featureprop detachedInstance) {
        log.debug("merging Featureprop instance");
        try {
            Featureprop result = (Featureprop) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Featureprop instance) {
        log.debug("attaching dirty Featureprop instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Featureprop instance) {
        log.debug("attaching clean Featureprop instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}