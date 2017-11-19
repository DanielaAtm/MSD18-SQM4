package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.service.ejb.BugStatusDataService;
import org.app.service.ejb.BugStatusDataServiceEJB;
import org.app.service.entities.BugStatus;
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
public class TestBugStatusDataServiceEJBArq {
	private static Logger logger = 
			Logger.getLogger(TestBugStatusDataServiceEJBArq.class.getName());
	
	@EJB // EJB DataService Ref
	private static BugStatusDataService service;
	
	@Deployment // Arquilian infrastructure
	public static Archive<?> createDeployment() {
	        return ShrinkWrap
	                .create(WebArchive.class, "msd-test.war")
	                .addPackage(Feature.class.getPackage())
	                .addClass(BugStatusDataService.class)
	                .addClass(BugStatusDataServiceEJB.class)
	                .addAsResource("META-INF/persistence.xml")
	                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	
	@Test
	public void test1_GetMessage() {
		logger.info("DEBUG: Junit TESTING: getMessage ...");
		String response = service.getMessage();
		assertNotNull("Data Service failed!", response);
		logger.info("DEBUG: EJB Response ..." + response);
	}

	@Test
	public void test4_GetBugStatus() {
		logger.info("DEBUG: Junit TESTING: testGetBugStatus ...");
		
		Collection<BugStatus> bugStatus = service.getBugStatus();
		assertTrue("Fail to read bugStatus!", bugStatus.size() > 0);
	}

	@Test
	public void test3_AddBugStatus() {
		logger.info("DEBUG: Junit TESTING: testAddBugStatus...");
		
		Integer bugStatusToAdd = 3;
		for (int i=1; i <= bugStatusToAdd; i++){
			//service.addBugStatus(new BugStatus(100 + i, "BugStatus_" + (100 + i)));
			service.addBugStatus(new BugStatus(null, "BugStatus_" + (100 + i)));
		}
		Collection<BugStatus> bugStatus = service.getBugStatus();
		assertTrue("Fail to add BugStatus!", bugStatus.size() == bugStatusToAdd);
	}

	@Test
	public void test2_DeleteBugStatus() {
		logger.info("DEBUG: Junit TESTING: testDeleteBugStatus ...");
		
		Collection<BugStatus> bugStatus = service.getBugStatus();
		for (BugStatus bs: bugStatus)
			service.removeBugStatus(bs);
		Collection<BugStatus> bugStatusAfterDelete = service.getBugStatus();
		assertTrue("Fail to read BugStatus!", bugStatusAfterDelete.size() == 0);
	}	
}
/* http://localhost:8080/SCRUM-S2/data/features */