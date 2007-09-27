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
 * Data access object (DAO) for domain model class StockPhenotype.
 * @see org.nescent.heliconius.hibernate.StockPhenotype
 * @author MyEclipse - Hibernate Tools
 */
public class StockPhenotypeDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(StockPhenotypeDAO.class);

	//property constants
	public static final String NOTES = "notes";

    
    public void save(StockPhenotype transientInstance) {
        log.debug("saving StockPhenotype instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(StockPhenotype persistentInstance) {
        log.debug("deleting StockPhenotype instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public StockPhenotype findById( java.lang.Integer id) {
        log.debug("getting StockPhenotype instance with id: " + id);
        try {
            StockPhenotype instance = (StockPhenotype) getSession()
                    .get("org.nescent.heliconius.hibernate.StockPhenotype", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(StockPhenotype instance) {
        log.debug("finding StockPhenotype instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.StockPhenotype")
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
      log.debug("finding StockPhenotype instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from StockPhenotype as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByNotes(Object notes) {
		return findByProperty(NOTES, notes);
	}
	
    public StockPhenotype merge(StockPhenotype detachedInstance) {
        log.debug("merging StockPhenotype instance");
        try {
            StockPhenotype result = (StockPhenotype) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(StockPhenotype instance) {
        log.debug("attaching dirty StockPhenotype instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(StockPhenotype instance) {
        log.debug("attaching clean StockPhenotype instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}