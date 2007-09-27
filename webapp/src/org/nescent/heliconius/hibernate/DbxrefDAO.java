package org.nescent.heliconius.hibernate;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Dbxref.
 * @see org.nescent.heliconius.hibernate.Dbxref
 * @author MyEclipse - Hibernate Tools
 */
public class DbxrefDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(DbxrefDAO.class);

	//property constants
	public static final String ACCESSION = "accession";
	public static final String VERSION = "version";
	public static final String DESCRIPTION = "description";

    
    public void save(Dbxref transientInstance) {
        log.debug("saving Dbxref instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Dbxref persistentInstance) {
        log.debug("deleting Dbxref instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Dbxref findById( java.lang.Integer id) {
        log.debug("getting Dbxref instance with id: " + id);
        try {
            Dbxref instance = (Dbxref) getSession()
                    .get("org.nescent.heliconius.hibernate.Dbxref", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Dbxref instance) {
        log.debug("finding Dbxref instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Dbxref")
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
      log.debug("finding Dbxref instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Dbxref as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByAccession(Object accession) {
		return findByProperty(ACCESSION, accession);
	}
	
	public List findByVersion(Object version) {
		return findByProperty(VERSION, version);
	}
	
	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}
	
    public Dbxref merge(Dbxref detachedInstance) {
        log.debug("merging Dbxref instance");
        try {
            Dbxref result = (Dbxref) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Dbxref instance) {
        log.debug("attaching dirty Dbxref instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Dbxref instance) {
        log.debug("attaching clean Dbxref instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}