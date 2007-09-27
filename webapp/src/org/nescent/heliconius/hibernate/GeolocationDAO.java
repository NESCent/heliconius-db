package org.nescent.heliconius.hibernate;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Geolocation.
 * @see org.nescent.heliconius.hibernate.Geolocation
 * @author MyEclipse - Hibernate Tools
 */
public class GeolocationDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(GeolocationDAO.class);

	//property constants
	public static final String DESCRIPTION = "description";
	public static final String COORDINATE_XML = "coordinateXml";
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longitude";
	public static final String GEODETIC_DATUM = "geodeticDatum";
	public static final String ALTITUDE = "altitude";
	public static final String ALTITUDE_DEV = "altitudeDev";

    
    public void save(Geolocation transientInstance) {
        log.debug("saving Geolocation instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Geolocation persistentInstance) {
        log.debug("deleting Geolocation instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Geolocation findById( java.lang.Integer id) {
        log.debug("getting Geolocation instance with id: " + id);
        try {
            Geolocation instance = (Geolocation) getSession()
                    .get("org.nescent.heliconius.hibernate.Geolocation", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Geolocation instance) {
        log.debug("finding Geolocation instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.nescent.heliconius.hibernate.Geolocation")
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
      log.debug("finding Geolocation instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Geolocation as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}
	
	public List findByCoordinateXml(Object coordinateXml) {
		return findByProperty(COORDINATE_XML, coordinateXml);
	}
	
	public List findByLatitude(Object latitude) {
		return findByProperty(LATITUDE, latitude);
	}
	
	public List findByLongitude(Object longitude) {
		return findByProperty(LONGITUDE, longitude);
	}
	
	public List findByGeodeticDatum(Object geodeticDatum) {
		return findByProperty(GEODETIC_DATUM, geodeticDatum);
	}
	
	public List findByAltitude(Object altitude) {
		return findByProperty(ALTITUDE, altitude);
	}
	
	public List findByAltitudeDev(Object altitudeDev) {
		return findByProperty(ALTITUDE_DEV, altitudeDev);
	}
	
    public Geolocation merge(Geolocation detachedInstance) {
        log.debug("merging Geolocation instance");
        try {
            Geolocation result = (Geolocation) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Geolocation instance) {
        log.debug("attaching dirty Geolocation instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Geolocation instance) {
        log.debug("attaching clean Geolocation instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}