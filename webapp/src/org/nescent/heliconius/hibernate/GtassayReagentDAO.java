package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class GtassayReagent.
 * @see org.nescent.heliconius.hibernate.GtassayReagent
 * @author MyEclipse - Hibernate Tools
 */
public class GtassayReagentDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(GtassayReagentDAO.class);

	//property constants
	public static final String TYPE_ID = "typeId";

    
    public void save(GtassayReagent transientInstance) {
        log.debug("saving GtassayReagent instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(GtassayReagent persistentInstance) {
        log.debug("deleting GtassayReagent instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public GtassayReagent findById( java.lang.Integer id) {
        log.debug("getting GtassayReagent instance with id: " + id);
        try {
            GtassayReagent instance = (GtassayReagent) getSession()
                    .get("org.nescent.heliconius.hibernate.GtassayReagent", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(GtassayReagent instance) {
        log.debug("finding GtassayReagent instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.GtassayReagent")
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
      log.debug("finding GtassayReagent instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from GtassayReagent as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByTypeId(Object typeId) {
		return findByProperty(TYPE_ID, typeId);
	}
	
    public GtassayReagent merge(GtassayReagent detachedInstance) {
        log.debug("merging GtassayReagent instance");
        try {
            GtassayReagent result = (GtassayReagent) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(GtassayReagent instance) {
        log.debug("attaching dirty GtassayReagent instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(GtassayReagent instance) {
        log.debug("attaching clean GtassayReagent instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}