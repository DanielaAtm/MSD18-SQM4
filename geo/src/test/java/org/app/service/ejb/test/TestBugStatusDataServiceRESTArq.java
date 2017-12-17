package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.app.patterns.EntityRepository;
import org.app.service.ejb.BugDataService;
import org.app.service.ejb.BugStatusDataService;
import org.app.service.entities.Bug;
import org.app.service.entities.BugStatus;
import org.app.service.rest.ApplicationConfig;
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
public class TestBugStatusDataServiceRESTArq {
	private static Logger logger = Logger.getLogger(TestBugStatusDataServiceRESTArq.class.getName());
	
		private static String serviceURL = "http://localhost:8089/geo/rest/bugsStatus";	
		
		@Deployment 
		public static Archive<?> createDeployment() {
		        return ShrinkWrap
		                .create(WebArchive.class, "msd-test.war")
		                .addPackage(BugStatus.class.getPackage())
		                .addPackage(BugStatusDataService.class.getPackage())
		                .addPackage(EntityRepository.class.getPackage())
		                .addPackage(ApplicationConfig.class.getPackage())
		                .addAsResource("META-INF/persistence.xml")
		                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml"); 
		}	
		
		@Test
		public void test1_GetMessage() {
			String resourceURL = serviceURL + "/test";
			logger.info("DEBUG: Junit TESTING: test1_GetMessage ...");
			String response = ClientBuilder.newClient().target(resourceURL).request().get().readEntity(String.class);
			assertNotNull("Data Service failed!", response);
			logger.info("DEBUG: EJB Response ..." + response);
		}

		@Test
		public void test4_GetBugStatus() {
			logger.info("DEBUG: Junit TESTING: test4_GetBugStatus ...");
			Collection<BugStatus> bugsStatus = ClientBuilder.newClient().target(serviceURL).request().get().readEntity(new GenericType<Collection<BugStatus>>(){});
			assertTrue("Fail to read BugStatus!", bugsStatus.size() > 0);
			bugsStatus.stream().forEach(System.out::println);
		}

	//	@Test
		public void test2_GetBugStatusByID() {
			
			String resourceURL=serviceURL+"/1";
			logger.info("DEBUG: JUnit Testing: test2_GetBugStatusByID....");
			BugStatus bugStatus=ClientBuilder.newClient().target(resourceURL).request().accept(MediaType.APPLICATION_JSON).get().readEntity(BugStatus.class);
			assertNotNull("REST Data Service failed!", bugStatus);
			logger.info(">>>>>> DEBUG :REST Response...."+bugStatus);
			
		}
		@Test
		public void test3_AddBugStatus() {
			try {
			logger.info("DEBUG: JUnit Testing: test3_AddBugStatus....");
			Client client=ClientBuilder.newClient();
			Collection<BugStatus> bugsStatus;
			Integer bugStatusToAdd=3;
			BugStatus bugStatus;
			for(int i=1;i<=bugStatusToAdd;i++) {
				bugStatus=new BugStatus(i,"BugStatus_"+(100+i));
				bugsStatus=client.target(serviceURL).request().accept(MediaType.APPLICATION_JSON).post(Entity.entity(bugStatus, MediaType.APPLICATION_JSON)).readEntity(new GenericType<Collection<BugStatus>>(){});
			    assertTrue("Failed to read bugStatus!",bugsStatus.size()>0);
			}
			bugsStatus=client.target(serviceURL).request().get().readEntity(new GenericType<Collection<BugStatus>>(){});
			assertTrue("Fail to add Bug !",bugsStatus.size()>=bugStatusToAdd);
			bugsStatus.stream().forEach(System.out::println);
			
			System.out.println("MERGE");
			}
			catch (Exception ex) {
				System.out.println("xoxo");

			}
		}
		@Test
		public void test3_CreateBugStatus() {
			String resourceURL = serviceURL + "/new/"; 
			logger.info("DEBUG: Junit TESTING: test3_CreateBugStatus ...");
			Client client = ClientBuilder.newClient();
			
			Integer bugStatusToAdd = 3;
			BugStatus bugStatus;
			for (int i=1; i <= bugStatusToAdd; i++){
				bugStatus = ClientBuilder.newClient().target(resourceURL + i)
						.request().accept(MediaType.APPLICATION_JSON)
						.post(null).readEntity(BugStatus.class);
				System.out.println(">>> Created/posted BugStatus: " + bugStatus);
			}

			Collection<BugStatus> bugsStatus = client.target(serviceURL)
					.request().get()
					.readEntity(new GenericType<Collection<BugStatus>>(){});
			
			assertTrue("Fail to add Projects!", bugsStatus.size() >= bugStatusToAdd);
		}	
		
//		@Test
//		public void test2_DeleteBug() {
//			try {
//			String resourceURL = serviceURL + "/";
//			logger.info("DEBUG: Junit TESTING: test2_DeleteBug...");
//			Client client = ClientBuilder.newClient();
//			Collection<Bug> bugs = client.target(serviceURL)
//					.request().get()
//					.readEntity(new GenericType<Collection<Bug>>(){});		
//			
//			for (Bug b: bugs) {
//				client.target(resourceURL + b.getBugID()).request().delete();
//			}
//			
//			Collection<Bug> bugsAfterDelete = client.target(serviceURL)
//					.request().get()
//					.readEntity(new GenericType<Collection<Bug>>(){});	
//			assertTrue("Fail to read Bugs!", bugsAfterDelete.size() == 0);
//			
//			System.out.println("MERGE");
//		}
//		catch (Exception ex) {
//			System.out.println("xoxo");
//
//		}
//		}

}
