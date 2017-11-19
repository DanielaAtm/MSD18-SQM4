package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.service.ejb.BugDataService;
import org.app.service.ejb.BugDataServiceEJB;
import org.app.service.entities.Bug;
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
public class TestBugDataServiceEJBArq {
	private static Logger logger = 
			Logger.getLogger(TestBugDataServiceEJBArq.class.getName());
	
	@EJB 
	// EJB DataService Ref
	private static BugDataService service;
	
	@Deployment 
	// Arquilian infrastructure
	public static Archive<?> createDeployment(){
		return ShrinkWrap.create(WebArchive.class,"msd-test.war")
						.addPackage(Bug.class.getPackage())
						.addClass(BugDataService.class)
						.addClass(BugDataServiceEJB.class)
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
	public void test4_GetBugs() {
		logger.info("DEBUG: Junit TESTING: testGetBugs ...");
		
		Collection<Bug> bugs = service.getBugs();
		assertTrue("Fail to read bugs!", bugs.size() > 0);
	}
	@Test
	public void test3_AddBug() {
		logger.info("DEBUG: Junit TESTING: testAddBug ...");

		Integer bugToAdd = 3;
		for (int i=1; i <= bugToAdd; i++){
			//service.addBug(new Bug(100 + i, "Bug_" + (100 + i)));
			service.addBug(new Bug(null,null, "Bug_" + (100 + i),null,null,null,null,null,null,null,null ));
		}
		Collection<Bug> bugs = service.getBugs();
		assertTrue("Fail to add bugs!", bugs.size() == bugToAdd);
	}
	
	@Test
	public void test2_DeleteBug() {
		logger.info("DEBUG: Junit TESTING: testDeleteBug ...");
		
		Collection<Bug> bugs = service.getBugs();
		for (Bug b: bugs)
			service.removeBug(b);
		Collection<Bug> bugsAfterDelete = service.getBugs();
		assertTrue("Fail to read bugs!", bugsAfterDelete.size() == 0);
	}	
}
