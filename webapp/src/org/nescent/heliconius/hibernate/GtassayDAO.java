package org.nescent.heliconius.hibernate;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Gtassay.
 * @see org.nescent.heliconius.hibernate.Gtassay
 * @author MyEclipse - Hibernate Tools
 */
public class GtassayDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(GtassayDAO.class);

	//property constants
	public static final String NAME = "name";

    
    public void save(Gtassay transientInstance) {
        log.debug("saving Gtassay instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Gtassay persistentInstance) {
        log.debug("deleting Gtassay instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Gtassay findById( java.lang.Integer id) {
        log.debug("getting Gtassay instance with id: " + id);
        try {
            Gtassay instance = (Gtassay) getSession()
                    .get("org.nescent.heliconius.hibernate.Gtassay", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Gtassay instance) {
        log.debug("finding Gtassay instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Gtassay")
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
      log.debug("finding Gtassay instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Gtassay as model where model." 
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
	
    public Gtassay merge(Gtassay detachedInstance) {
        log.debug("merging Gtassay instance");
        try {
            Gtassay result = (Gtassay) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Gtassay instance) {
        log.debug("attaching dirty Gtassay instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Gtassay instance) {
        log.debug("attaching clean Gtassay instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}