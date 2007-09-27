package org.nescent.heliconius.hibernate;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Db.
 * @see org.nescent.heliconius.hibernate.Db
 * @author MyEclipse - Hibernate Tools
 */
public class DbDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(DbDAO.class);

	//property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String URLPREFIX = "urlprefix";
	public static final String URL = "url";

    
    public void save(Db transientInstance) {
        log.debug("saving Db instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Db persistentInstance) {
        log.debug("deleting Db instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Db findById( java.lang.Integer id) {
        log.debug("getting Db instance with id: " + id);
        try {
            Db instance = (Db) getSession()
                    .get("org.nescent.heliconius.hibernate.Db", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Db instance) {
        log.debug("finding Db instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Db")
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
      log.debug("finding Db instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Db as model where model." 
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
	
	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}
	
	public List findByUrlprefix(Object urlprefix) {
		return findByProperty(URLPREFIX, urlprefix);
	}
	
	public List findByUrl(Object url) {
		return findByProperty(URL, url);
	}
	
    public Db merge(Db detachedInstance) {
        log.debug("merging Db instance");
        try {
            Db result = (Db) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Db instance) {
        log.debug("attaching dirty Db instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Db instance) {
        log.debug("attaching clean Db instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}