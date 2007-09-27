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
 * Data access object (DAO) for domain model class Stock.
 * @see org.nescent.heliconius.hibernate.Stock
 * @author MyEclipse - Hibernate Tools
 */
public class StockDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(StockDAO.class);

	//property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";

    
    public void save(Stock transientInstance) {
        log.debug("saving Stock instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Stock persistentInstance) {
        log.debug("deleting Stock instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Stock findById( java.lang.Integer id) {
        log.debug("getting Stock instance with id: " + id);
        try {
            Stock instance = (Stock) getSession()
                    .get("org.nescent.heliconius.hibernate.Stock", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Stock instance) {
        log.debug("finding Stock instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Stock")
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
      log.debug("finding Stock instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Stock as model where model." 
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
	
	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}
	
    public Stock merge(Stock detachedInstance) {
        log.debug("merging Stock instance");
        try {
            Stock result = (Stock) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Stock instance) {
        log.debug("attaching dirty Stock instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Stock instance) {
        log.debug("attaching clean Stock instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}