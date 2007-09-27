package org.nescent.heliconius.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class BiotypePhenotype.
 * @see org.nescent.heliconius.hibernate.BiotypePhenotype
 * @author MyEclipse - Hibernate Tools
 */
public class BiotypePhenotypeDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(BiotypePhenotypeDAO.class);

	//property constants
	public static final String NOTES = "notes";

    
    public void save(BiotypePhenotype transientInstance) {
        log.debug("saving BiotypePhenotype instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(BiotypePhenotype persistentInstance) {
        log.debug("deleting BiotypePhenotype instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public BiotypePhenotype findById( java.lang.Integer id) {
        log.debug("getting BiotypePhenotype instance with id: " + id);
        try {
            BiotypePhenotype instance = (BiotypePhenotype) getSession()
                    .get("org.nescent.heliconius.hibernate.BiotypePhenotype", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(BiotypePhenotype instance) {
        log.debug("finding BiotypePhenotype instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.BiotypePhenotype")
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
      log.debug("finding BiotypePhenotype instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from BiotypePhenotype as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByNotes(Object notes) {
		return findByProperty(NOTES, notes);
	}
	
    public BiotypePhenotype merge(BiotypePhenotype detachedInstance) {
        log.debug("merging BiotypePhenotype instance");
        try {
            BiotypePhenotype result = (BiotypePhenotype) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(BiotypePhenotype instance) {
        log.debug("attaching dirty BiotypePhenotype instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(BiotypePhenotype instance) {
        log.debug("attaching clean BiotypePhenotype instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}