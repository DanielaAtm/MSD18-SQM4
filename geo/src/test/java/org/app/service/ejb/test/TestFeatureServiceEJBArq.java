package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;


import org.app.service.ejb.FeatureService;
import org.app.service.ejb.FeatureServiceEJB;
import org.app.service.entities.Feature;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestFeatureServiceEJBArq {
	private static Logger logger = 
			Logger.getLogger(TestFeatureServiceEJBArq.class.getName());
	
	@EJB 
	// EJB DataService Ref
	private static FeatureService service;
	
	@Deployment 
	// Arquilian infrastructure
	public static Archive<?> createDeployment(){
		return ShrinkWrap.create(WebArchive.class,"msd-test.war")
						.addPackage(Feature.class.getPackage())
						.addClass(FeatureService.class)
						.addClass(FeatureServiceEJB.class)
						.addAsResource("META-INF/persistence.xml")
						.addAsManifestResource(EmptyAsset.INSTANCE,"beans.xml");
	}
	
	@Test
	public void test1_GetMessage() {
		logger.info("DEBUG: Junit TESTING: getMessage ...");
		String response = service.getMessage();
		assertNotNull("Data Service failed!", response);
		logger.info("DEBUG: EJB Response ..." + response);
	}
	@Test
	public void test2_DeleteFeature() {
		logger.info("DEBUG: Junit TESTING: testDeleteFeature ...");
		
		Collection<Feature> features = service.getFeatures();
		for (Feature f: features)
			service.removeFeature(f);
		Collection<Feature> featuresAfterDelete = service.getFeatures();
		assertTrue("Fail to read features!", featuresAfterDelete.size() == 0);
	}	
	@Test
	public void test3_AddFeature() {
		logger.info("DEBUG: Junit TESTING: testAddFeature ...");

		Integer featureToAdd = 3;
		for (int i=1; i <= featureToAdd; i++){
			service.addFeature(new Feature(null, "Feature_" + (100 + i),null));
		}
		Collection<Feature> features = service.getFeatures();
		assertTrue("Fail to add features!", features.size() == featureToAdd);
	}
	@Test
	public void test4_GetFeatures() {
		logger.info("DEBUG: Junit TESTING: testGetFeatures ...");
		
		Collection<Feature> features = service.getFeatures();
		assertTrue("Fail to read bugs!", features.size() > 0);
	}
	
}
