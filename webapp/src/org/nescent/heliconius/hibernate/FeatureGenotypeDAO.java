package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class FeatureGenotype.
 * @see org.nescent.heliconius.hibernate.FeatureGenotype
 * @author MyEclipse - Hibernate Tools
 */
public class FeatureGenotypeDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(FeatureGenotypeDAO.class);

	//property constants
	public static final String RANK = "rank";
	public static final String CGROUP = "cgroup";

    
    public void save(FeatureGenotype transientInstance) {
        log.debug("saving FeatureGenotype instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(FeatureGenotype persistentInstance) {
        log.debug("deleting FeatureGenotype instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public FeatureGenotype findById( java.lang.Integer id) {
        log.debug("getting FeatureGenotype instance with id: " + id);
        try {
            FeatureGenotype instance = (FeatureGenotype) getSession()
                    .get("org.nescent.heliconius.hibernate.FeatureGenotype", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(FeatureGenotype instance) {
        log.debug("finding FeatureGenotype instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.FeatureGenotype")
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
      log.debug("finding FeatureGenotype instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from FeatureGenotype as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByRank(Object rank) {
		return findByProperty(RANK, rank);
	}
	
	public List findByCgroup(Object cgroup) {
		return findByProperty(CGROUP, cgroup);
	}
	
    public FeatureGenotype merge(FeatureGenotype detachedInstance) {
        log.debug("merging FeatureGenotype instance");
        try {
            FeatureGenotype result = (FeatureGenotype) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(FeatureGenotype instance) {
        log.debug("attaching dirty FeatureGenotype instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(FeatureGenotype instance) {
        log.debug("attaching clean FeatureGenotype instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}