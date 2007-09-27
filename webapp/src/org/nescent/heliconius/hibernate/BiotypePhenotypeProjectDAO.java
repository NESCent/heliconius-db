package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class BiotypePhenotypeProject.
 * @see org.nescent.heliconius.hibernate.BiotypePhenotypeProject
 * @author MyEclipse - Hibernate Tools
 */
public class BiotypePhenotypeProjectDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(BiotypePhenotypeProjectDAO.class);

	//property constants

    
    public void save(BiotypePhenotypeProject transientInstance) {
        log.debug("saving BiotypePhenotypeProject instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(BiotypePhenotypeProject persistentInstance) {
        log.debug("deleting BiotypePhenotypeProject instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public BiotypePhenotypeProject findById( java.lang.Integer id) {
        log.debug("getting BiotypePhenotypeProject instance with id: " + id);
        try {
            BiotypePhenotypeProject instance = (BiotypePhenotypeProject) getSession()
                    .get("org.nescent.heliconius.hibernate.BiotypePhenotypeProject", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(BiotypePhenotypeProject instance) {
        log.debug("finding BiotypePhenotypeProject instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.BiotypePhenotypeProject")
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
      log.debug("finding BiotypePhenotypeProject instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from BiotypePhenotypeProject as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public BiotypePhenotypeProject merge(BiotypePhenotypeProject detachedInstance) {
        log.debug("merging BiotypePhenotypeProject instance");
        try {
            BiotypePhenotypeProject result = (BiotypePhenotypeProject) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(BiotypePhenotypeProject instance) {
        log.debug("attaching dirty BiotypePhenotypeProject instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(BiotypePhenotypeProject instance) {
        log.debug("attaching clean BiotypePhenotypeProject instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}