package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class ContactRelationship.
 * @see org.nescent.heliconius.hibernate.ContactRelationship
 * @author MyEclipse - Hibernate Tools
 */
public class ContactRelationshipDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(ContactRelationshipDAO.class);

	//property constants

    
    public void save(ContactRelationship transientInstance) {
        log.debug("saving ContactRelationship instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(ContactRelationship persistentInstance) {
        log.debug("deleting ContactRelationship instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ContactRelationship findById( java.lang.Integer id) {
        log.debug("getting ContactRelationship instance with id: " + id);
        try {
            ContactRelationship instance = (ContactRelationship) getSession()
                    .get("org.nescent.heliconius.hibernate.ContactRelationship", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(ContactRelationship instance) {
        log.debug("finding ContactRelationship instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.ContactRelationship")
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
      log.debug("finding ContactRelationship instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from ContactRelationship as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public ContactRelationship merge(ContactRelationship detachedInstance) {
        log.debug("merging ContactRelationship instance");
        try {
            ContactRelationship result = (ContactRelationship) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ContactRelationship instance) {
        log.debug("attaching dirty ContactRelationship instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ContactRelationship instance) {
        log.debug("attaching clean ContactRelationship instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}