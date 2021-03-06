package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class IndividualBiotype.
 * @see org.nescent.heliconius.hibernate.IndividualBiotype
 * @author MyEclipse - Hibernate Tools
 */
public class IndividualBiotypeDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(IndividualBiotypeDAO.class);

	//property constants

    
    public void save(IndividualBiotype transientInstance) {
        log.debug("saving IndividualBiotype instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(IndividualBiotype persistentInstance) {
        log.debug("deleting IndividualBiotype instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public IndividualBiotype findById( java.lang.Integer id) {
        log.debug("getting IndividualBiotype instance with id: " + id);
        try {
            IndividualBiotype instance = (IndividualBiotype) getSession()
                    .get("org.nescent.heliconius.hibernate.IndividualBiotype", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(IndividualBiotype instance) {
        log.debug("finding IndividualBiotype instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.IndividualBiotype")
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
      log.debug("finding IndividualBiotype instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from IndividualBiotype as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public IndividualBiotype merge(IndividualBiotype detachedInstance) {
        log.debug("merging IndividualBiotype instance");
        try {
            IndividualBiotype result = (IndividualBiotype) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(IndividualBiotype instance) {
        log.debug("attaching dirty IndividualBiotype instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(IndividualBiotype instance) {
        log.debug("attaching clean IndividualBiotype instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}