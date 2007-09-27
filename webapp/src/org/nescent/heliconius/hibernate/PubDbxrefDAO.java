package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class PubDbxref.
 * @see org.nescent.heliconius.hibernate.PubDbxref
 * @author MyEclipse - Hibernate Tools
 */
public class PubDbxrefDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(PubDbxrefDAO.class);

	//property constants
	public static final String IS_CURRENT = "isCurrent";

    
    public void save(PubDbxref transientInstance) {
        log.debug("saving PubDbxref instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(PubDbxref persistentInstance) {
        log.debug("deleting PubDbxref instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public PubDbxref findById( java.lang.Integer id) {
        log.debug("getting PubDbxref instance with id: " + id);
        try {
            PubDbxref instance = (PubDbxref) getSession()
                    .get("org.nescent.heliconius.hibernate.PubDbxref", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(PubDbxref instance) {
        log.debug("finding PubDbxref instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.PubDbxref")
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
      log.debug("finding PubDbxref instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from PubDbxref as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByIsCurrent(Object isCurrent) {
		return findByProperty(IS_CURRENT, isCurrent);
	}
	
    public PubDbxref merge(PubDbxref detachedInstance) {
        log.debug("merging PubDbxref instance");
        try {
            PubDbxref result = (PubDbxref) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(PubDbxref instance) {
        log.debug("attaching dirty PubDbxref instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(PubDbxref instance) {
        log.debug("attaching clean PubDbxref instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}