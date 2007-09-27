package org.nescent.heliconius.hibernate;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Specimen.
 * @see org.nescent.heliconius.hibernate.Specimen
 * @author MyEclipse - Hibernate Tools
 */
public class SpecimenDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(SpecimenDAO.class);

	//property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String IDENTIFIER = "identifier";
	public static final String STORAGE_LOCATION = "storageLocation";

    
    public void save(Specimen transientInstance) {
        log.debug("saving Specimen instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Specimen persistentInstance) {
        log.debug("deleting Specimen instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Specimen findById( java.lang.Integer id) {
        log.debug("getting Specimen instance with id: " + id);
        try {
            Specimen instance = (Specimen) getSession()
                    .get("org.nescent.heliconius.hibernate.Specimen", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Specimen instance) {
        log.debug("finding Specimen instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Specimen")
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
      log.debug("finding Specimen instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Specimen as model where model." 
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
	
	public List findByIdentifier(Object identifier) {
		return findByProperty(IDENTIFIER, identifier);
	}
	
	public List findByStorageLocation(Object storageLocation) {
		return findByProperty(STORAGE_LOCATION, storageLocation);
	}
	
    public Specimen merge(Specimen detachedInstance) {
        log.debug("merging Specimen instance");
        try {
            Specimen result = (Specimen) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Specimen instance) {
        log.debug("attaching dirty Specimen instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Specimen instance) {
        log.debug("attaching clean Specimen instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}