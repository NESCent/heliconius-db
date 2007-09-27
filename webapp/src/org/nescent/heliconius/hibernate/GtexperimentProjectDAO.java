package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class GtexperimentProject.
 * @see org.nescent.heliconius.hibernate.GtexperimentProject
 * @author MyEclipse - Hibernate Tools
 */
public class GtexperimentProjectDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(GtexperimentProjectDAO.class);

	//property constants

    
    public void save(GtexperimentProject transientInstance) {
        log.debug("saving GtexperimentProject instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(GtexperimentProject persistentInstance) {
        log.debug("deleting GtexperimentProject instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public GtexperimentProject findById( java.lang.Integer id) {
        log.debug("getting GtexperimentProject instance with id: " + id);
        try {
            GtexperimentProject instance = (GtexperimentProject) getSession()
                    .get("org.nescent.heliconius.hibernate.GtexperimentProject", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(GtexperimentProject instance) {
        log.debug("finding GtexperimentProject instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.GtexperimentProject")
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
      log.debug("finding GtexperimentProject instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from GtexperimentProject as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public GtexperimentProject merge(GtexperimentProject detachedInstance) {
        log.debug("merging GtexperimentProject instance");
        try {
            GtexperimentProject result = (GtexperimentProject) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(GtexperimentProject instance) {
        log.debug("attaching dirty GtexperimentProject instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(GtexperimentProject instance) {
        log.debug("attaching clean GtexperimentProject instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}