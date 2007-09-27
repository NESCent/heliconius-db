package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class PhenotypeCvterm.
 * @see org.nescent.heliconius.hibernate.PhenotypeCvterm
 * @author MyEclipse - Hibernate Tools
 */
public class PhenotypeCvtermDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(PhenotypeCvtermDAO.class);

	//property constants

    
    public void save(PhenotypeCvterm transientInstance) {
        log.debug("saving PhenotypeCvterm instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(PhenotypeCvterm persistentInstance) {
        log.debug("deleting PhenotypeCvterm instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public PhenotypeCvterm findById( java.lang.Integer id) {
        log.debug("getting PhenotypeCvterm instance with id: " + id);
        try {
            PhenotypeCvterm instance = (PhenotypeCvterm) getSession()
                    .get("org.nescent.heliconius.hibernate.PhenotypeCvterm", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(PhenotypeCvterm instance) {
        log.debug("finding PhenotypeCvterm instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.PhenotypeCvterm")
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
      log.debug("finding PhenotypeCvterm instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from PhenotypeCvterm as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public PhenotypeCvterm merge(PhenotypeCvterm detachedInstance) {
        log.debug("merging PhenotypeCvterm instance");
        try {
            PhenotypeCvterm result = (PhenotypeCvterm) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(PhenotypeCvterm instance) {
        log.debug("attaching dirty PhenotypeCvterm instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(PhenotypeCvterm instance) {
        log.debug("attaching clean PhenotypeCvterm instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}