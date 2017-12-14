package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.service.entities.Feature;

@Stateless @LocalBean
public class FeatureServiceEJB implements FeatureService {

private static Logger logger = Logger.getLogger(FeatureServiceEJB.class.getName());
	
	/* DataService initialization */
	// Inject resource 
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	// Constructor
	public FeatureServiceEJB() {
	}
	// Init after constructor
	@PostConstruct
	public void init(){
		logger.info("POSTCONSTRUCT-INIT : " + this.em);
	}	
	@Override
	public Feature addFeature(Feature featureToAdd) {
		em.persist(featureToAdd);
		em.flush();
		em.refresh(featureToAdd);
		return featureToAdd;
	}

	@Override
	public String removeFeature(Feature featureToDelete) {
		featureToDelete=em.merge(featureToDelete);
		em.remove(featureToDelete);
		em.flush();
		return "True";
	}

	@Override
	public Feature getFeatureByID(Integer featureID) {
		return em.find(Feature.class, featureID);
		
	}

	@Override
	public Collection<Feature> getFeatures() {
		List<Feature> features=em.createQuery("SELECT f FROM Feature f",Feature.class).getResultList();
		return features;
	}

	@Override
	public Feature getFeatureByName(String featureName) {
		return em.createQuery("SELECT f FROM Feature f WHERE f.featureName= :featureName", Feature.class).setParameter("featureName", featureName).getSingleResult();

	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Feature Service is ON.... ";
	}

	@Override
	public String sayrest() {
		// TODO Auto-generated method stub
		return "Feature Service is ON.... ";
	}

}
