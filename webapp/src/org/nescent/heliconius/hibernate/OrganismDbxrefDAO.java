package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class OrganismDbxref.
 * @see org.nescent.heliconius.hibernate.OrganismDbxref
 * @author MyEclipse - Hibernate Tools
 */
public class OrganismDbxrefDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(OrganismDbxrefDAO.class);

	//property constants

    
    public void save(OrganismDbxref transientInstance) {
        log.debug("saving OrganismDbxref instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(OrganismDbxref persistentInstance) {
        log.debug("deleting OrganismDbxref instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public OrganismDbxref findById( java.lang.Integer id) {
        log.debug("getting OrganismDbxref instance with id: " + id);
        try {
            OrganismDbxref instance = (OrganismDbxref) getSession()
                    .get("org.nescent.heliconius.hibernate.OrganismDbxref", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(OrganismDbxref instance) {
        log.debug("finding OrganismDbxref instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.OrganismDbxref")
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
      log.debug("finding OrganismDbxref instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from OrganismDbxref as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public OrganismDbxref merge(OrganismDbxref detachedInstance) {
        log.debug("merging OrganismDbxref instance");
        try {
            OrganismDbxref result = (OrganismDbxref) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(OrganismDbxref instance) {
        log.debug("attaching dirty OrganismDbxref instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(OrganismDbxref instance) {
        log.debug("attaching clean OrganismDbxref instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}