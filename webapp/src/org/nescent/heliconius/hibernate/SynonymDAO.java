package org.nescent.heliconius.hibernate;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Synonym.
 * @see org.nescent.heliconius.hibernate.Synonym
 * @author MyEclipse - Hibernate Tools
 */
public class SynonymDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(SynonymDAO.class);

	//property constants
	public static final String NAME = "name";
	public static final String SYNONYM_SGML = "synonymSgml";

    
    public void save(Synonym transientInstance) {
        log.debug("saving Synonym instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Synonym persistentInstance) {
        log.debug("deleting Synonym instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Synonym findById( java.lang.Integer id) {
        log.debug("getting Synonym instance with id: " + id);
        try {
            Synonym instance = (Synonym) getSession()
                    .get("org.nescent.heliconius.hibernate.Synonym", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Synonym instance) {
        log.debug("finding Synonym instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Synonym")
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
      log.debug("finding Synonym instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Synonym as model where model." 
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
	
	public List findBySynonymSgml(Object synonymSgml) {
		return findByProperty(SYNONYM_SGML, synonymSgml);
	}
	
    public Synonym merge(Synonym detachedInstance) {
        log.debug("merging Synonym instance");
        try {
            Synonym result = (Synonym) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Synonym instance) {
        log.debug("attaching dirty Synonym instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Synonym instance) {
        log.debug("attaching clean Synonym instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}