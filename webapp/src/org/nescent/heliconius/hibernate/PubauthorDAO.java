package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Pubauthor.
 * @see org.nescent.heliconius.hibernate.Pubauthor
 * @author MyEclipse - Hibernate Tools
 */
public class PubauthorDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(PubauthorDAO.class);

	//property constants
	public static final String RANK = "rank";
	public static final String EDITOR = "editor";
	public static final String SURNAME = "surname";
	public static final String GIVENNAMES = "givennames";
	public static final String SUFFIX = "suffix";

    
    public void save(Pubauthor transientInstance) {
        log.debug("saving Pubauthor instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Pubauthor persistentInstance) {
        log.debug("deleting Pubauthor instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Pubauthor findById( java.lang.Integer id) {
        log.debug("getting Pubauthor instance with id: " + id);
        try {
            Pubauthor instance = (Pubauthor) getSession()
                    .get("org.nescent.heliconius.hibernate.Pubauthor", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Pubauthor instance) {
        log.debug("finding Pubauthor instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Pubauthor")
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
      log.debug("finding Pubauthor instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Pubauthor as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByRank(Object rank) {
		return findByProperty(RANK, rank);
	}
	
	public List findByEditor(Object editor) {
		return findByProperty(EDITOR, editor);
	}
	
	public List findBySurname(Object surname) {
		return findByProperty(SURNAME, surname);
	}
	
	public List findByGivennames(Object givennames) {
		return findByProperty(GIVENNAMES, givennames);
	}
	
	public List findBySuffix(Object suffix) {
		return findByProperty(SUFFIX, suffix);
	}
	
    public Pubauthor merge(Pubauthor detachedInstance) {
        log.debug("merging Pubauthor instance");
        try {
            Pubauthor result = (Pubauthor) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Pubauthor instance) {
        log.debug("attaching dirty Pubauthor instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Pubauthor instance) {
        log.debug("attaching clean Pubauthor instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}