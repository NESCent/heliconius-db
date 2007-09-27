package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class ReagentRelationship.
 * @see org.nescent.heliconius.hibernate.ReagentRelationship
 * @author MyEclipse - Hibernate Tools
 */
public class ReagentRelationshipDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(ReagentRelationshipDAO.class);

	//property constants

    
    public void save(ReagentRelationship transientInstance) {
        log.debug("saving ReagentRelationship instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(ReagentRelationship persistentInstance) {
        log.debug("deleting ReagentRelationship instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ReagentRelationship findById( java.lang.Integer id) {
        log.debug("getting ReagentRelationship instance with id: " + id);
        try {
            ReagentRelationship instance = (ReagentRelationship) getSession()
                    .get("org.nescent.heliconius.hibernate.ReagentRelationship", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(ReagentRelationship instance) {
        log.debug("finding ReagentRelationship instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.ReagentRelationship")
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
      log.debug("finding ReagentRelationship instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from ReagentRelationship as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public ReagentRelationship merge(ReagentRelationship detachedInstance) {
        log.debug("merging ReagentRelationship instance");
        try {
            ReagentRelationship result = (ReagentRelationship) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ReagentRelationship instance) {
        log.debug("attaching dirty ReagentRelationship instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ReagentRelationship instance) {
        log.debug("attaching clean ReagentRelationship instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}