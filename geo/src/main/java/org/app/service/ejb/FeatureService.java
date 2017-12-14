package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Feature;

@Remote
public interface FeatureService {

	//Create or Update a Bug
		Feature addFeature(Feature featureToAdd);
		
		//Delete
		String removeFeature(Feature featureToDelete);
		
		//Read
		Feature getFeatureByID(Integer featureID);
		Collection<Feature> getFeatures();
		
		//Custom Read: custom Query
		Feature getFeatureByName(String featureName);
		
	    //Others
		String getMessage();

		String sayrest();
	
}
