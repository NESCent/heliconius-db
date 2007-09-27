package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Ptassayprop.
 * @see org.nescent.heliconius.hibernate.Ptassayprop
 * @author MyEclipse - Hibernate Tools
 */
public class PtassaypropDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(PtassaypropDAO.class);

	//property constants
	public static final String VALUE = "value";
	public static final String RANK = "rank";

    
    public void save(Ptassayprop transientInstance) {
        log.debug("saving Ptassayprop instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Ptassayprop persistentInstance) {
        log.debug("deleting Ptassayprop instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Ptassayprop findById( java.lang.Integer id) {
        log.debug("getting Ptassayprop instance with id: " + id);
        try {
            Ptassayprop instance = (Ptassayprop) getSession()
                    .get("org.nescent.heliconius.hibernate.Ptassayprop", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Ptassayprop instance) {
        log.debug("finding Ptassayprop instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Ptassayprop")
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
      log.debug("finding Ptassayprop instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Ptassayprop as model where model." 
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
	
    public Ptassayprop merge(Ptassayprop detachedInstance) {
        log.debug("merging Ptassayprop instance");
        try {
            Ptassayprop result = (Ptassayprop) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Ptassayprop instance) {
        log.debug("attaching dirty Ptassayprop instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Ptassayprop instance) {
        log.debug("attaching clean Ptassayprop instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}