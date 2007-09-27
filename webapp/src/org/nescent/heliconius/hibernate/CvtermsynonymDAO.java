package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Cvtermsynonym.
 * @see org.nescent.heliconius.hibernate.Cvtermsynonym
 * @author MyEclipse - Hibernate Tools
 */
public class CvtermsynonymDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(CvtermsynonymDAO.class);

	//property constants
	public static final String SYNONYM = "synonym";

    
    public void save(Cvtermsynonym transientInstance) {
        log.debug("saving Cvtermsynonym instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Cvtermsynonym persistentInstance) {
        log.debug("deleting Cvtermsynonym instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Cvtermsynonym findById( java.lang.Integer id) {
        log.debug("getting Cvtermsynonym instance with id: " + id);
        try {
            Cvtermsynonym instance = (Cvtermsynonym) getSession()
                    .get("org.nescent.heliconius.hibernate.Cvtermsynonym", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Cvtermsynonym instance) {
        log.debug("finding Cvtermsynonym instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Cvtermsynonym")
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
      log.debug("finding Cvtermsynonym instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Cvtermsynonym as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findBySynonym(Object synonym) {
		return findByProperty(SYNONYM, synonym);
	}
	
    public Cvtermsynonym merge(Cvtermsynonym detachedInstance) {
        log.debug("merging Cvtermsynonym instance");
        try {
            Cvtermsynonym result = (Cvtermsynonym) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Cvtermsynonym instance) {
        log.debug("attaching dirty Cvtermsynonym instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Cvtermsynonym instance) {
        log.debug("attaching clean Cvtermsynonym instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}