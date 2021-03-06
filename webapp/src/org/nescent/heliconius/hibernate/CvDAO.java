package org.nescent.heliconius.hibernate;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Cv.
 * @see org.nescent.heliconius.hibernate.Cv
 * @author MyEclipse - Hibernate Tools
 */
public class CvDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(CvDAO.class);

	//property constants
	public static final String NAME = "name";
	public static final String DEFINITION = "definition";

    
    public void save(Cv transientInstance) {
        log.debug("saving Cv instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Cv persistentInstance) {
        log.debug("deleting Cv instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Cv findById( java.lang.Integer id) {
        log.debug("getting Cv instance with id: " + id);
        try {
            Cv instance = (Cv) getSession()
                    .get("org.nescent.heliconius.hibernate.Cv", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Cv instance) {
        log.debug("finding Cv instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Cv")
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
      log.debug("finding Cv instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Cv as model where model." 
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
	
	public List findByDefinition(Object definition) {
		return findByProperty(DEFINITION, definition);
	}
	
    public Cv merge(Cv detachedInstance) {
        log.debug("merging Cv instance");
        try {
            Cv result = (Cv) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Cv instance) {
        log.debug("attaching dirty Cv instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Cv instance) {
        log.debug("attaching clean Cv instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}