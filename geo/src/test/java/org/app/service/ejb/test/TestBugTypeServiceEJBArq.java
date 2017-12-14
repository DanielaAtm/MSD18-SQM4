
package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;


import org.app.service.ejb.BugTypeService;
import org.app.service.ejb.BugTypeServiceEJB;
import org.app.service.entities.BugType;
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
public class TestBugTypeServiceEJBArq {
	private static Logger logger = 
			Logger.getLogger(TestBugTypeServiceEJBArq.class.getName());
	
	@EJB 
	// EJB DataService Ref
	private static BugTypeService service;
	
	@Deployment 
	// Arquilian infrastructure
	public static Archive<?> createDeployment(){
		return ShrinkWrap.create(WebArchive.class,"msd-test.war")
						.addPackage(BugType.class.getPackage())
						.addClass(BugTypeService.class)
						.addClass(BugTypeServiceEJB.class)
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
	public void test4_GetBugTypes() {
		logger.info("DEBUG: Junit TESTING: testGetBugTypes ...");
		
		Collection<BugType> bugTypes = service.getBugTypes();
		assertTrue("Fail to read bugTypes!", bugTypes.size() > 0);
	}
	@Test
	public void test3_AddBugTypes() {
		logger.info("DEBUG: Junit TESTING: testAddBugType ...");

		Integer bugTypeToAdd = 3;
		for (int i=1; i <= bugTypeToAdd; i++){
			service.addBugType(new BugType(null, "BugType_" + (100 + i),null ));
		}
		Collection<BugType> bugTypes = service.getBugTypes();
		assertTrue("Fail to add bugTypes!", bugTypes.size() == bugTypeToAdd);
	}
	
	@Test
	public void test2_DeleteBugType() {
		logger.info("DEBUG: Junit TESTING: testDeleteBugType ...");
		
		Collection<BugType> bugTypes = service.getBugTypes();
		for (BugType bt: bugTypes)
			service.removeBugType(bt);
		Collection<BugType> bugTypesAfterDelete = service.getBugTypes();
		assertTrue("Fail to read bugTypes!", bugTypesAfterDelete.size() == 0);
	}	
}
