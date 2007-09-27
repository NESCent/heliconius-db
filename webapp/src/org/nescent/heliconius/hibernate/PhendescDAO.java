package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Phendesc.
 * @see org.nescent.heliconius.hibernate.Phendesc
 * @author MyEclipse - Hibernate Tools
 */
public class PhendescDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(PhendescDAO.class);

	//property constants
	public static final String DESCRIPTION = "description";

    
    public void save(Phendesc transientInstance) {
        log.debug("saving Phendesc instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Phendesc persistentInstance) {
        log.debug("deleting Phendesc instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Phendesc findById( java.lang.Integer id) {
        log.debug("getting Phendesc instance with id: " + id);
        try {
            Phendesc instance = (Phendesc) getSession()
                    .get("org.nescent.heliconius.hibernate.Phendesc", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Phendesc instance) {
        log.debug("finding Phendesc instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Phendesc")
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
      log.debug("finding Phendesc instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Phendesc as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}
	
    public Phendesc merge(Phendesc detachedInstance) {
        log.debug("merging Phendesc instance");
        try {
            Phendesc result = (Phendesc) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Phendesc instance) {
        log.debug("attaching dirty Phendesc instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Phendesc instance) {
        log.debug("attaching clean Phendesc instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}