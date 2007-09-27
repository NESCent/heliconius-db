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
 * Data access object (DAO) for domain model class Feature.
 * @see org.nescent.heliconius.hibernate.Feature
 * @author MyEclipse - Hibernate Tools
 */
public class FeatureDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(FeatureDAO.class);

	//property constants
	public static final String NAME = "name";
	public static final String UNIQUENAME = "uniquename";
	public static final String RESIDUES = "residues";
	public static final String SEQLEN = "seqlen";
	public static final String MD5CHECKSUM = "md5checksum";
	public static final String IS_ANALYSIS = "isAnalysis";
	public static final String IS_OBSOLETE = "isObsolete";

    
    public void save(Feature transientInstance) {
        log.debug("saving Feature instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Feature persistentInstance) {
        log.debug("deleting Feature instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Feature findById( java.lang.Integer id) {
        log.debug("getting Feature instance with id: " + id);
        try {
            Feature instance = (Feature) getSession()
                    .get("org.nescent.heliconius.hibernate.Feature", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Feature instance) {
        log.debug("finding Feature instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Feature")
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
      log.debug("finding Feature instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Feature as model where model." 
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
	
	public List findByUniquename(Object uniquename) {
		return findByProperty(UNIQUENAME, uniquename);
	}
	
	public List findByResidues(Object residues) {
		return findByProperty(RESIDUES, residues);
	}
	
	public List findBySeqlen(Object seqlen) {
		return findByProperty(SEQLEN, seqlen);
	}
	
	public List findByMd5checksum(Object md5checksum) {
		return findByProperty(MD5CHECKSUM, md5checksum);
	}
	
	public List findByIsAnalysis(Object isAnalysis) {
		return findByProperty(IS_ANALYSIS, isAnalysis);
	}
	
	public List findByIsObsolete(Object isObsolete) {
		return findByProperty(IS_OBSOLETE, isObsolete);
	}
	
    public Feature merge(Feature detachedInstance) {
        log.debug("merging Feature instance");
        try {
            Feature result = (Feature) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Feature instance) {
        log.debug("attaching dirty Feature instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Feature instance) {
        log.debug("attaching clean Feature instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}