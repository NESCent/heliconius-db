package org.nescent.heliconius.hibernate;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Phenotype.
 * @see org.nescent.heliconius.hibernate.Phenotype
 * @author MyEclipse - Hibernate Tools
 */
public class PhenotypeDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(PhenotypeDAO.class);

	//property constants
	public static final String UNIQUENAME = "uniquename";
	public static final String VALUE = "value";

    
    public void save(Phenotype transientInstance) {
        log.debug("saving Phenotype instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Phenotype persistentInstance) {
        log.debug("deleting Phenotype instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Phenotype findById( java.lang.Integer id) {
        log.debug("getting Phenotype instance with id: " + id);
        try {
            Phenotype instance = (Phenotype) getSession()
                    .get("org.nescent.heliconius.hibernate.Phenotype", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Phenotype instance) {
        log.debug("finding Phenotype instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Phenotype")
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
      log.debug("finding Phenotype instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Phenotype as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByUniquename(Object uniquename) {
		return findByProperty(UNIQUENAME, uniquename);
	}
	
	public List findByValue(Object value) {
		return findByProperty(VALUE, value);
	}
	
    public Phenotype merge(Phenotype detachedInstance) {
        log.debug("merging Phenotype instance");
        try {
            Phenotype result = (Phenotype) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Phenotype instance) {
        log.debug("attaching dirty Phenotype instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Phenotype instance) {
        log.debug("attaching clean Phenotype instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}