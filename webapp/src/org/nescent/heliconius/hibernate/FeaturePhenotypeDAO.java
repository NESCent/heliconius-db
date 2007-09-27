package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class FeaturePhenotype.
 * @see org.nescent.heliconius.hibernate.FeaturePhenotype
 * @author MyEclipse - Hibernate Tools
 */
public class FeaturePhenotypeDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(FeaturePhenotypeDAO.class);

	//property constants

    
    public void save(FeaturePhenotype transientInstance) {
        log.debug("saving FeaturePhenotype instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(FeaturePhenotype persistentInstance) {
        log.debug("deleting FeaturePhenotype instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public FeaturePhenotype findById( java.lang.Integer id) {
        log.debug("getting FeaturePhenotype instance with id: " + id);
        try {
            FeaturePhenotype instance = (FeaturePhenotype) getSession()
                    .get("org.nescent.heliconius.hibernate.FeaturePhenotype", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(FeaturePhenotype instance) {
        log.debug("finding FeaturePhenotype instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.FeaturePhenotype")
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
      log.debug("finding FeaturePhenotype instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from FeaturePhenotype as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public FeaturePhenotype merge(FeaturePhenotype detachedInstance) {
        log.debug("merging FeaturePhenotype instance");
        try {
            FeaturePhenotype result = (FeaturePhenotype) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(FeaturePhenotype instance) {
        log.debug("attaching dirty FeaturePhenotype instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(FeaturePhenotype instance) {
        log.debug("attaching clean FeaturePhenotype instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}