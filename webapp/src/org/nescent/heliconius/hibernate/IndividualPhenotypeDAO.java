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
 * Data access object (DAO) for domain model class IndividualPhenotype.
 * @see org.nescent.heliconius.hibernate.IndividualPhenotype
 * @author MyEclipse - Hibernate Tools
 */
public class IndividualPhenotypeDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(IndividualPhenotypeDAO.class);

	//property constants
	public static final String NOTES = "notes";

    
    public void save(IndividualPhenotype transientInstance) {
        log.debug("saving IndividualPhenotype instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(IndividualPhenotype persistentInstance) {
        log.debug("deleting IndividualPhenotype instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public IndividualPhenotype findById( java.lang.Integer id) {
        log.debug("getting IndividualPhenotype instance with id: " + id);
        try {
            IndividualPhenotype instance = (IndividualPhenotype) getSession()
                    .get("org.nescent.heliconius.hibernate.IndividualPhenotype", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(IndividualPhenotype instance) {
        log.debug("finding IndividualPhenotype instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.IndividualPhenotype")
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
      log.debug("finding IndividualPhenotype instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from IndividualPhenotype as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByNotes(Object notes) {
		return findByProperty(NOTES, notes);
	}
	
    public IndividualPhenotype merge(IndividualPhenotype detachedInstance) {
        log.debug("merging IndividualPhenotype instance");
        try {
            IndividualPhenotype result = (IndividualPhenotype) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(IndividualPhenotype instance) {
        log.debug("attaching dirty IndividualPhenotype instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(IndividualPhenotype instance) {
        log.debug("attaching clean IndividualPhenotype instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}