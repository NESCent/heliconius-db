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
 * Data access object (DAO) for domain model class Individual.
 * @see org.nescent.heliconius.hibernate.Individual
 * @author MyEclipse - Hibernate Tools
 */
public class IndividualDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(IndividualDAO.class);

	//property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String IS_CAPTIVITY_REARED = "isCaptivityReared";

    
    public void save(Individual transientInstance) {
        log.debug("saving Individual instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Individual persistentInstance) {
        log.debug("deleting Individual instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Individual findById( java.lang.Integer id) {
        log.debug("getting Individual instance with id: " + id);
        try {
            Individual instance = (Individual) getSession()
                    .get("org.nescent.heliconius.hibernate.Individual", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Individual instance) {
        log.debug("finding Individual instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Individual")
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
      log.debug("finding Individual instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Individual as model where model." 
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
	
	public List findByIsCaptivityReared(Object isCaptivityReared) {
		return findByProperty(IS_CAPTIVITY_REARED, isCaptivityReared);
	}
	
    public Individual merge(Individual detachedInstance) {
        log.debug("merging Individual instance");
        try {
            Individual result = (Individual) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Individual instance) {
        log.debug("attaching dirty Individual instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Individual instance) {
        log.debug("attaching clean Individual instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}