package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Dbxrefprop.
 * @see org.nescent.heliconius.hibernate.Dbxrefprop
 * @author MyEclipse - Hibernate Tools
 */
public class DbxrefpropDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(DbxrefpropDAO.class);

	//property constants
	public static final String VALUE = "value";
	public static final String RANK = "rank";

    
    public void save(Dbxrefprop transientInstance) {
        log.debug("saving Dbxrefprop instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Dbxrefprop persistentInstance) {
        log.debug("deleting Dbxrefprop instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Dbxrefprop findById( java.lang.Integer id) {
        log.debug("getting Dbxrefprop instance with id: " + id);
        try {
            Dbxrefprop instance = (Dbxrefprop) getSession()
                    .get("org.nescent.heliconius.hibernate.Dbxrefprop", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Dbxrefprop instance) {
        log.debug("finding Dbxrefprop instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Dbxrefprop")
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
      log.debug("finding Dbxrefprop instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Dbxrefprop as model where model." 
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
	
    public Dbxrefprop merge(Dbxrefprop detachedInstance) {
        log.debug("merging Dbxrefprop instance");
        try {
            Dbxrefprop result = (Dbxrefprop) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Dbxrefprop instance) {
        log.debug("attaching dirty Dbxrefprop instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Dbxrefprop instance) {
        log.debug("attaching clean Dbxrefprop instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}