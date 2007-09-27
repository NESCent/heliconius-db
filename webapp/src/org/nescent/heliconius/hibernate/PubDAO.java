package org.nescent.heliconius.hibernate;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Pub.
 * @see org.nescent.heliconius.hibernate.Pub
 * @author MyEclipse - Hibernate Tools
 */
public class PubDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(PubDAO.class);

	//property constants
	public static final String TITLE = "title";
	public static final String VOLUMETITLE = "volumetitle";
	public static final String VOLUME = "volume";
	public static final String SERIES_NAME = "seriesName";
	public static final String ISSUE = "issue";
	public static final String PYEAR = "pyear";
	public static final String PAGES = "pages";
	public static final String MINIREF = "miniref";
	public static final String UNIQUENAME = "uniquename";
	public static final String IS_OBSOLETE = "isObsolete";
	public static final String PUBLISHER = "publisher";
	public static final String PUBPLACE = "pubplace";

    
    public void save(Pub transientInstance) {
        log.debug("saving Pub instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Pub persistentInstance) {
        log.debug("deleting Pub instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Pub findById( java.lang.Integer id) {
        log.debug("getting Pub instance with id: " + id);
        try {
            Pub instance = (Pub) getSession()
                    .get("org.nescent.heliconius.hibernate.Pub", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Pub instance) {
        log.debug("finding Pub instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Pub")
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
      log.debug("finding Pub instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Pub as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}
	
	public List findByVolumetitle(Object volumetitle) {
		return findByProperty(VOLUMETITLE, volumetitle);
	}
	
	public List findByVolume(Object volume) {
		return findByProperty(VOLUME, volume);
	}
	
	public List findBySeriesName(Object seriesName) {
		return findByProperty(SERIES_NAME, seriesName);
	}
	
	public List findByIssue(Object issue) {
		return findByProperty(ISSUE, issue);
	}
	
	public List findByPyear(Object pyear) {
		return findByProperty(PYEAR, pyear);
	}
	
	public List findByPages(Object pages) {
		return findByProperty(PAGES, pages);
	}
	
	public List findByMiniref(Object miniref) {
		return findByProperty(MINIREF, miniref);
	}
	
	public List findByUniquename(Object uniquename) {
		return findByProperty(UNIQUENAME, uniquename);
	}
	
	public List findByIsObsolete(Object isObsolete) {
		return findByProperty(IS_OBSOLETE, isObsolete);
	}
	
	public List findByPublisher(Object publisher) {
		return findByProperty(PUBLISHER, publisher);
	}
	
	public List findByPubplace(Object pubplace) {
		return findByProperty(PUBPLACE, pubplace);
	}
	
    public Pub merge(Pub detachedInstance) {
        log.debug("merging Pub instance");
        try {
            Pub result = (Pub) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Pub instance) {
        log.debug("attaching dirty Pub instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Pub instance) {
        log.debug("attaching clean Pub instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}