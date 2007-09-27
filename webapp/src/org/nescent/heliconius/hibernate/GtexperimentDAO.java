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
 * Data access object (DAO) for domain model class Gtexperiment.
 * @see org.nescent.heliconius.hibernate.Gtexperiment
 * @author MyEclipse - Hibernate Tools
 */
public class GtexperimentDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(GtexperimentDAO.class);

	//property constants
	public static final String NOTES = "notes";

    
    public void save(Gtexperiment transientInstance) {
        log.debug("saving Gtexperiment instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Gtexperiment persistentInstance) {
        log.debug("deleting Gtexperiment instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Gtexperiment findById( java.lang.Integer id) {
        log.debug("getting Gtexperiment instance with id: " + id);
        try {
            Gtexperiment instance = (Gtexperiment) getSession()
                    .get("org.nescent.heliconius.hibernate.Gtexperiment", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Gtexperiment instance) {
        log.debug("finding Gtexperiment instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Gtexperiment")
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
      log.debug("finding Gtexperiment instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Gtexperiment as model where model." 
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
	
    public Gtexperiment merge(Gtexperiment detachedInstance) {
        log.debug("merging Gtexperiment instance");
        try {
            Gtexperiment result = (Gtexperiment) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Gtexperiment instance) {
        log.debug("attaching dirty Gtexperiment instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Gtexperiment instance) {
        log.debug("attaching clean Gtexperiment instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}