package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class StockPhenotypeProject.
 * @see org.nescent.heliconius.hibernate.StockPhenotypeProject
 * @author MyEclipse - Hibernate Tools
 */
public class StockPhenotypeProjectDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(StockPhenotypeProjectDAO.class);

	//property constants

    
    public void save(StockPhenotypeProject transientInstance) {
        log.debug("saving StockPhenotypeProject instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(StockPhenotypeProject persistentInstance) {
        log.debug("deleting StockPhenotypeProject instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public StockPhenotypeProject findById( java.lang.Integer id) {
        log.debug("getting StockPhenotypeProject instance with id: " + id);
        try {
            StockPhenotypeProject instance = (StockPhenotypeProject) getSession()
                    .get("org.nescent.heliconius.hibernate.StockPhenotypeProject", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(StockPhenotypeProject instance) {
        log.debug("finding StockPhenotypeProject instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.StockPhenotypeProject")
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
      log.debug("finding StockPhenotypeProject instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from StockPhenotypeProject as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public StockPhenotypeProject merge(StockPhenotypeProject detachedInstance) {
        log.debug("merging StockPhenotypeProject instance");
        try {
            StockPhenotypeProject result = (StockPhenotypeProject) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(StockPhenotypeProject instance) {
        log.debug("attaching dirty StockPhenotypeProject instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(StockPhenotypeProject instance) {
        log.debug("attaching clean StockPhenotypeProject instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}