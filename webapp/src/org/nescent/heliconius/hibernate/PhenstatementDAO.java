package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Phenstatement.
 * @see org.nescent.heliconius.hibernate.Phenstatement
 * @author MyEclipse - Hibernate Tools
 */
public class PhenstatementDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(PhenstatementDAO.class);

	//property constants

    
    public void save(Phenstatement transientInstance) {
        log.debug("saving Phenstatement instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Phenstatement persistentInstance) {
        log.debug("deleting Phenstatement instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Phenstatement findById( java.lang.Integer id) {
        log.debug("getting Phenstatement instance with id: " + id);
        try {
            Phenstatement instance = (Phenstatement) getSession()
                    .get("org.nescent.heliconius.hibernate.Phenstatement", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Phenstatement instance) {
        log.debug("finding Phenstatement instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Phenstatement")
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
      log.debug("finding Phenstatement instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Phenstatement as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public Phenstatement merge(Phenstatement detachedInstance) {
        log.debug("merging Phenstatement instance");
        try {
            Phenstatement result = (Phenstatement) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Phenstatement instance) {
        log.debug("attaching dirty Phenstatement instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Phenstatement instance) {
        log.debug("attaching clean Phenstatement instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}