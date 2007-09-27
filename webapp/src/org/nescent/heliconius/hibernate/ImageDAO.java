package org.nescent.heliconius.hibernate;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Image.
 * @see org.nescent.heliconius.hibernate.Image
 * @author MyEclipse - Hibernate Tools
 */
public class ImageDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(ImageDAO.class);

	//property constants
	public static final String IDENTIFIER = "identifier";
	public static final String URI = "uri";

    
    public void save(Image transientInstance) {
        log.debug("saving Image instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Image persistentInstance) {
        log.debug("deleting Image instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Image findById( java.lang.Integer id) {
        log.debug("getting Image instance with id: " + id);
        try {
            Image instance = (Image) getSession()
                    .get("org.nescent.heliconius.hibernate.Image", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Image instance) {
        log.debug("finding Image instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Image")
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
      log.debug("finding Image instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Image as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByIdentifier(Object identifier) {
		return findByProperty(IDENTIFIER, identifier);
	}
	
	public List findByUri(Object uri) {
		return findByProperty(URI, uri);
	}
	
    public Image merge(Image detachedInstance) {
        log.debug("merging Image instance");
        try {
            Image result = (Image) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Image instance) {
        log.debug("attaching dirty Image instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Image instance) {
        log.debug("attaching clean Image instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}