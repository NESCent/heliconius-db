package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class PubRelationship.
 * @see org.nescent.heliconius.hibernate.PubRelationship
 * @author MyEclipse - Hibernate Tools
 */
public class PubRelationshipDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(PubRelationshipDAO.class);

	//property constants

    
    public void save(PubRelationship transientInstance) {
        log.debug("saving PubRelationship instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(PubRelationship persistentInstance) {
        log.debug("deleting PubRelationship instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public PubRelationship findById( java.lang.Integer id) {
        log.debug("getting PubRelationship instance with id: " + id);
        try {
            PubRelationship instance = (PubRelationship) getSession()
                    .get("org.nescent.heliconius.hibernate.PubRelationship", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(PubRelationship instance) {
        log.debug("finding PubRelationship instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.PubRelationship")
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
      log.debug("finding PubRelationship instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from PubRelationship as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public PubRelationship merge(PubRelationship detachedInstance) {
        log.debug("merging PubRelationship instance");
        try {
            PubRelationship result = (PubRelationship) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(PubRelationship instance) {
        log.debug("attaching dirty PubRelationship instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(PubRelationship instance) {
        log.debug("attaching clean PubRelationship instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}