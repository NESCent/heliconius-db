package org.nescent.heliconius.hibernate;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Ptassay.
 * @see org.nescent.heliconius.hibernate.Ptassay
 * @author MyEclipse - Hibernate Tools
 */
public class PtassayDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(PtassayDAO.class);

	//property constants
	public static final String NAME = "name";

    
    public void save(Ptassay transientInstance) {
        log.debug("saving Ptassay instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Ptassay persistentInstance) {
        log.debug("deleting Ptassay instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Ptassay findById( java.lang.Integer id) {
        log.debug("getting Ptassay instance with id: " + id);
        try {
            Ptassay instance = (Ptassay) getSession()
                    .get("org.nescent.heliconius.hibernate.Ptassay", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Ptassay instance) {
        log.debug("finding Ptassay instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Ptassay")
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
      log.debug("finding Ptassay instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Ptassay as model where model." 
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
	
    public Ptassay merge(Ptassay detachedInstance) {
        log.debug("merging Ptassay instance");
        try {
            Ptassay result = (Ptassay) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Ptassay instance) {
        log.debug("attaching dirty Ptassay instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Ptassay instance) {
        log.debug("attaching clean Ptassay instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}