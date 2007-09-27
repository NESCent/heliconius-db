package org.nescent.heliconius.hibernate;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Featureloc.
 * @see org.nescent.heliconius.hibernate.Featureloc
 * @author MyEclipse - Hibernate Tools
 */
public class FeaturelocDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(FeaturelocDAO.class);

	//property constants
	public static final String FMIN = "fmin";
	public static final String IS_FMIN_PARTIAL = "isFminPartial";
	public static final String FMAX = "fmax";
	public static final String IS_FMAX_PARTIAL = "isFmaxPartial";
	public static final String STRAND = "strand";
	public static final String PHASE = "phase";
	public static final String RESIDUE_INFO = "residueInfo";
	public static final String LOCGROUP = "locgroup";
	public static final String RANK = "rank";

    
    public void save(Featureloc transientInstance) {
        log.debug("saving Featureloc instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Featureloc persistentInstance) {
        log.debug("deleting Featureloc instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Featureloc findById( java.lang.Integer id) {
        log.debug("getting Featureloc instance with id: " + id);
        try {
            Featureloc instance = (Featureloc) getSession()
                    .get("org.nescent.heliconius.hibernate.Featureloc", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Featureloc instance) {
        log.debug("finding Featureloc instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Featureloc")
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
      log.debug("finding Featureloc instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Featureloc as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByFmin(Object fmin) {
		return findByProperty(FMIN, fmin);
	}
	
	public List findByIsFminPartial(Object isFminPartial) {
		return findByProperty(IS_FMIN_PARTIAL, isFminPartial);
	}
	
	public List findByFmax(Object fmax) {
		return findByProperty(FMAX, fmax);
	}
	
	public List findByIsFmaxPartial(Object isFmaxPartial) {
		return findByProperty(IS_FMAX_PARTIAL, isFmaxPartial);
	}
	
	public List findByStrand(Object strand) {
		return findByProperty(STRAND, strand);
	}
	
	public List findByPhase(Object phase) {
		return findByProperty(PHASE, phase);
	}
	
	public List findByResidueInfo(Object residueInfo) {
		return findByProperty(RESIDUE_INFO, residueInfo);
	}
	
	public List findByLocgroup(Object locgroup) {
		return findByProperty(LOCGROUP, locgroup);
	}
	
	public List findByRank(Object rank) {
		return findByProperty(RANK, rank);
	}
	
    public Featureloc merge(Featureloc detachedInstance) {
        log.debug("merging Featureloc instance");
        try {
            Featureloc result = (Featureloc) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Featureloc instance) {
        log.debug("attaching dirty Featureloc instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Featureloc instance) {
        log.debug("attaching clean Featureloc instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}