package org.nescent.heliconius.hibernate;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Genotype.
 * @see org.nescent.heliconius.hibernate.Genotype
 * @author MyEclipse - Hibernate Tools
 */
public class GenotypeDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(GenotypeDAO.class);

	//property constants
	public static final String NAME = "name";
	public static final String UNIQUENAME = "uniquename";
	public static final String DESCRIPTION = "description";

    
    public void save(Genotype transientInstance) {
        log.debug("saving Genotype instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Genotype persistentInstance) {
        log.debug("deleting Genotype instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Genotype findById( java.lang.Integer id) {
        log.debug("getting Genotype instance with id: " + id);
        try {
            Genotype instance = (Genotype) getSession()
                    .get("org.nescent.heliconius.hibernate.Genotype", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Genotype instance) {
        log.debug("finding Genotype instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Genotype")
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
      log.debug("finding Genotype instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Genotype as model where model." 
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
	
	public List findByUniquename(Object uniquename) {
		return findByProperty(UNIQUENAME, uniquename);
	}
	
	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}
	
    public Genotype merge(Genotype detachedInstance) {
        log.debug("merging Genotype instance");
        try {
            Genotype result = (Genotype) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Genotype instance) {
        log.debug("attaching dirty Genotype instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Genotype instance) {
        log.debug("attaching clean Genotype instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}