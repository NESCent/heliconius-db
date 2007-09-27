package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Gtassayprop.
 * @see org.nescent.heliconius.hibernate.Gtassayprop
 * @author MyEclipse - Hibernate Tools
 */
public class GtassaypropDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(GtassaypropDAO.class);

	//property constants
	public static final String VALUE = "value";
	public static final String RANK = "rank";

    
    public void save(Gtassayprop transientInstance) {
        log.debug("saving Gtassayprop instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Gtassayprop persistentInstance) {
        log.debug("deleting Gtassayprop instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Gtassayprop findById( java.lang.Integer id) {
        log.debug("getting Gtassayprop instance with id: " + id);
        try {
            Gtassayprop instance = (Gtassayprop) getSession()
                    .get("org.nescent.heliconius.hibernate.Gtassayprop", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Gtassayprop instance) {
        log.debug("finding Gtassayprop instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Gtassayprop")
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
      log.debug("finding Gtassayprop instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Gtassayprop as model where model." 
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
	
    public Gtassayprop merge(Gtassayprop detachedInstance) {
        log.debug("merging Gtassayprop instance");
        try {
            Gtassayprop result = (Gtassayprop) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Gtassayprop instance) {
        log.debug("attaching dirty Gtassayprop instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Gtassayprop instance) {
        log.debug("attaching clean Gtassayprop instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}