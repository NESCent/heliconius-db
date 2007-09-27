package org.nescent.heliconius.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Politlocation.
 * @see org.nescent.heliconius.hibernate.Politlocation
 * @author MyEclipse - Hibernate Tools
 */
public class PolitlocationDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(PolitlocationDAO.class);

	//property constants
	public static final String POSTALCODE = "postalcode";

    
    public void save(Politlocation transientInstance) {
        log.debug("saving Politlocation instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Politlocation persistentInstance) {
        log.debug("deleting Politlocation instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Politlocation findById( java.lang.Integer id) {
        log.debug("getting Politlocation instance with id: " + id);
        try {
            Politlocation instance = (Politlocation) getSession()
                    .get("org.nescent.heliconius.hibernate.Politlocation", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Politlocation instance) {
        log.debug("finding Politlocation instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Politlocation")
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
      log.debug("finding Politlocation instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Politlocation as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByPostalcode(Object postalcode) {
		return findByProperty(POSTALCODE, postalcode);
	}
	
    public Politlocation merge(Politlocation detachedInstance) {
        log.debug("merging Politlocation instance");
        try {
            Politlocation result = (Politlocation) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Politlocation instance) {
        log.debug("attaching dirty Politlocation instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Politlocation instance) {
        log.debug("attaching clean Politlocation instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}