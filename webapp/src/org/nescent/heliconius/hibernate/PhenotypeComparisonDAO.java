package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class PhenotypeComparison.
 * @see org.nescent.heliconius.hibernate.PhenotypeComparison
 * @author MyEclipse - Hibernate Tools
 */
public class PhenotypeComparisonDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(PhenotypeComparisonDAO.class);

	//property constants

    
    public void save(PhenotypeComparison transientInstance) {
        log.debug("saving PhenotypeComparison instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(PhenotypeComparison persistentInstance) {
        log.debug("deleting PhenotypeComparison instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public PhenotypeComparison findById( java.lang.Integer id) {
        log.debug("getting PhenotypeComparison instance with id: " + id);
        try {
            PhenotypeComparison instance = (PhenotypeComparison) getSession()
                    .get("org.nescent.heliconius.hibernate.PhenotypeComparison", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(PhenotypeComparison instance) {
        log.debug("finding PhenotypeComparison instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.PhenotypeComparison")
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
      log.debug("finding PhenotypeComparison instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from PhenotypeComparison as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public PhenotypeComparison merge(PhenotypeComparison detachedInstance) {
        log.debug("merging PhenotypeComparison instance");
        try {
            PhenotypeComparison result = (PhenotypeComparison) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(PhenotypeComparison instance) {
        log.debug("attaching dirty PhenotypeComparison instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(PhenotypeComparison instance) {
        log.debug("attaching clean PhenotypeComparison instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}