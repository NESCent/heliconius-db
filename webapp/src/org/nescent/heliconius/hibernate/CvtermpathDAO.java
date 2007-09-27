package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Cvtermpath.
 * @see org.nescent.heliconius.hibernate.Cvtermpath
 * @author MyEclipse - Hibernate Tools
 */
public class CvtermpathDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(CvtermpathDAO.class);

	//property constants
	public static final String PATHDISTANCE = "pathdistance";

    
    public void save(Cvtermpath transientInstance) {
        log.debug("saving Cvtermpath instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Cvtermpath persistentInstance) {
        log.debug("deleting Cvtermpath instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Cvtermpath findById( java.lang.Integer id) {
        log.debug("getting Cvtermpath instance with id: " + id);
        try {
            Cvtermpath instance = (Cvtermpath) getSession()
                    .get("org.nescent.heliconius.hibernate.Cvtermpath", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Cvtermpath instance) {
        log.debug("finding Cvtermpath instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Cvtermpath")
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
      log.debug("finding Cvtermpath instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Cvtermpath as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByPathdistance(Object pathdistance) {
		return findByProperty(PATHDISTANCE, pathdistance);
	}
	
    public Cvtermpath merge(Cvtermpath detachedInstance) {
        log.debug("merging Cvtermpath instance");
        try {
            Cvtermpath result = (Cvtermpath) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Cvtermpath instance) {
        log.debug("attaching dirty Cvtermpath instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Cvtermpath instance) {
        log.debug("attaching clean Cvtermpath instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}