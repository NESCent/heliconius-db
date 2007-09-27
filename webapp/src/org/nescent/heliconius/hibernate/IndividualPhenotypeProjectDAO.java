package org.nescent.heliconius.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class IndividualPhenotypeProject.
 * @see org.nescent.heliconius.hibernate.IndividualPhenotypeProject
 * @author MyEclipse - Hibernate Tools
 */
public class IndividualPhenotypeProjectDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(IndividualPhenotypeProjectDAO.class);

	//property constants

    
    public void save(IndividualPhenotypeProject transientInstance) {
        log.debug("saving IndividualPhenotypeProject instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(IndividualPhenotypeProject persistentInstance) {
        log.debug("deleting IndividualPhenotypeProject instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public IndividualPhenotypeProject findById( java.lang.Integer id) {
        log.debug("getting IndividualPhenotypeProject instance with id: " + id);
        try {
            IndividualPhenotypeProject instance = (IndividualPhenotypeProject) getSession()
                    .get("org.nescent.heliconius.hibernate.IndividualPhenotypeProject", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(IndividualPhenotypeProject instance) {
        log.debug("finding IndividualPhenotypeProject instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.IndividualPhenotypeProject")
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
      log.debug("finding IndividualPhenotypeProject instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from IndividualPhenotypeProject as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public IndividualPhenotypeProject merge(IndividualPhenotypeProject detachedInstance) {
        log.debug("merging IndividualPhenotypeProject instance");
        try {
            IndividualPhenotypeProject result = (IndividualPhenotypeProject) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(IndividualPhenotypeProject instance) {
        log.debug("attaching dirty IndividualPhenotypeProject instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(IndividualPhenotypeProject instance) {
        log.debug("attaching clean IndividualPhenotypeProject instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}