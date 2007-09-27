package org.nescent.heliconius.hibernate;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Cvterm.
 * @see org.nescent.heliconius.hibernate.Cvterm
 * @author MyEclipse - Hibernate Tools
 */
public class CvtermDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(CvtermDAO.class);

	//property constants
	public static final String NAME = "name";
	public static final String DEFINITION = "definition";
	public static final String IS_OBSOLETE = "isObsolete";
	public static final String IS_RELATIONSHIPTYPE = "isRelationshiptype";

    
    public void save(Cvterm transientInstance) {
        log.debug("saving Cvterm instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Cvterm persistentInstance) {
        log.debug("deleting Cvterm instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Cvterm findById( java.lang.Integer id) {
        log.debug("getting Cvterm instance with id: " + id);
        try {
            Cvterm instance = (Cvterm) getSession()
                    .get("org.nescent.heliconius.hibernate.Cvterm", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Cvterm instance) {
        log.debug("finding Cvterm instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Cvterm")
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
      log.debug("finding Cvterm instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Cvterm as model where model." 
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
	
	public List findByIsObsolete(Object isObsolete) {
		return findByProperty(IS_OBSOLETE, isObsolete);
	}
	
	public List findByIsRelationshiptype(Object isRelationshiptype) {
		return findByProperty(IS_RELATIONSHIPTYPE, isRelationshiptype);
	}
	
    public Cvterm merge(Cvterm detachedInstance) {
        log.debug("merging Cvterm instance");
        try {
            Cvterm result = (Cvterm) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Cvterm instance) {
        log.debug("attaching dirty Cvterm instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Cvterm instance) {
        log.debug("attaching clean Cvterm instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}