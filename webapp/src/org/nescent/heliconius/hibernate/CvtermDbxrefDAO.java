package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class CvtermDbxref.
 * @see org.nescent.heliconius.hibernate.CvtermDbxref
 * @author MyEclipse - Hibernate Tools
 */
public class CvtermDbxrefDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(CvtermDbxrefDAO.class);

	//property constants
	public static final String IS_FOR_DEFINITION = "isForDefinition";

    
    public void save(CvtermDbxref transientInstance) {
        log.debug("saving CvtermDbxref instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(CvtermDbxref persistentInstance) {
        log.debug("deleting CvtermDbxref instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public CvtermDbxref findById( java.lang.Integer id) {
        log.debug("getting CvtermDbxref instance with id: " + id);
        try {
            CvtermDbxref instance = (CvtermDbxref) getSession()
                    .get("org.nescent.heliconius.hibernate.CvtermDbxref", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(CvtermDbxref instance) {
        log.debug("finding CvtermDbxref instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.CvtermDbxref")
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
      log.debug("finding CvtermDbxref instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from CvtermDbxref as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByIsForDefinition(Object isForDefinition) {
		return findByProperty(IS_FOR_DEFINITION, isForDefinition);
	}
	
    public CvtermDbxref merge(CvtermDbxref detachedInstance) {
        log.debug("merging CvtermDbxref instance");
        try {
            CvtermDbxref result = (CvtermDbxref) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(CvtermDbxref instance) {
        log.debug("attaching dirty CvtermDbxref instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(CvtermDbxref instance) {
        log.debug("attaching clean CvtermDbxref instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}