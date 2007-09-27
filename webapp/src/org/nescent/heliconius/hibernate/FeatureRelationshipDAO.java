package org.nescent.heliconius.hibernate;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class FeatureRelationship.
 * @see org.nescent.heliconius.hibernate.FeatureRelationship
 * @author MyEclipse - Hibernate Tools
 */
public class FeatureRelationshipDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(FeatureRelationshipDAO.class);

	//property constants
	public static final String VALUE = "value";
	public static final String RANK = "rank";

    
    public void save(FeatureRelationship transientInstance) {
        log.debug("saving FeatureRelationship instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(FeatureRelationship persistentInstance) {
        log.debug("deleting FeatureRelationship instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public FeatureRelationship findById( java.lang.Integer id) {
        log.debug("getting FeatureRelationship instance with id: " + id);
        try {
            FeatureRelationship instance = (FeatureRelationship) getSession()
                    .get("org.nescent.heliconius.hibernate.FeatureRelationship", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(FeatureRelationship instance) {
        log.debug("finding FeatureRelationship instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.FeatureRelationship")
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
      log.debug("finding FeatureRelationship instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from FeatureRelationship as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByValue(Object value) {
		return findByProperty(VALUE, value);
	}
	
	public List findByRank(Object rank) {
		return findByProperty(RANK, rank);
	}
	
    public FeatureRelationship merge(FeatureRelationship detachedInstance) {
        log.debug("merging FeatureRelationship instance");
        try {
            FeatureRelationship result = (FeatureRelationship) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(FeatureRelationship instance) {
        log.debug("attaching dirty FeatureRelationship instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(FeatureRelationship instance) {
        log.debug("attaching clean FeatureRelationship instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}