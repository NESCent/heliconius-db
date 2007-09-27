package org.nescent.heliconius.hibernate;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Contact.
 * @see org.nescent.heliconius.hibernate.Contact
 * @author MyEclipse - Hibernate Tools
 */
public class ContactDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(ContactDAO.class);

	//property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";

    
    public void save(Contact transientInstance) {
        log.debug("saving Contact instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Contact persistentInstance) {
        log.debug("deleting Contact instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Contact findById( java.lang.Integer id) {
        log.debug("getting Contact instance with id: " + id);
        try {
            Contact instance = (Contact) getSession()
                    .get("org.nescent.heliconius.hibernate.Contact", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Contact instance) {
        log.debug("finding Contact instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Contact")
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
      log.debug("finding Contact instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Contact as model where model." 
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
	
    public Contact merge(Contact detachedInstance) {
        log.debug("merging Contact instance");
        try {
            Contact result = (Contact) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Contact instance) {
        log.debug("attaching dirty Contact instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Contact instance) {
        log.debug("attaching clean Contact instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}