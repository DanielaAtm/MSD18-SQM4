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
import org.app.service.entities.Bug;
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
public class TestBugDataServiceRESTArq {
	private static Logger logger = Logger.getLogger(TestBugDataServiceRESTArq.class.getName());
	
		private static String serviceURL = "http://localhost:8089/geo/rest/bugs";	
		
		@Deployment
		public static Archive<?> createDeployment() {
		        return ShrinkWrap
		                .create(WebArchive.class, "msd-test.war")
		                .addPackage(Bug.class.getPackage())
		                .addPackage(BugDataService.class.getPackage())
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
		public void test4_GetBugs() {
			logger.info("DEBUG: Junit TESTING: test4_GetBugs ...");
			Collection<Bug> bugs = ClientBuilder.newClient()
					.target(serviceURL)
					.request().get()
					.readEntity(new GenericType<Collection<Bug>>(){});
			assertTrue("Fail to read Bugs!", bugs.size() > 0);
			bugs.stream().forEach(System.out::println);
		}

		@Test
		public void test3_AddBug() {
			try {
			logger.info("DEBUG: Junit TESTING: test3_AddBug ...");
			Client client = ClientBuilder.newClient();
			Collection<Bug> bugs;
			Integer bugToAdd = 3;
			Bug bug;
			for (int i=1; i <= bugToAdd; i++){
				bug = new Bug(i, null,"BugType_" + (100 + i),null,null,null,null,null,null,null,null);
				bugs = client.target(serviceURL)
					.request().accept(MediaType.APPLICATION_JSON)
					.post(Entity.entity(bug, MediaType.APPLICATION_JSON))
					.readEntity(new GenericType<Collection<Bug>>(){});
				assertTrue("Fail to read Bugs!", bugs.size() > 0);
			}
			bugs = client.target(serviceURL)
					.request().get()
					.readEntity(new GenericType<Collection<Bug>>(){});
			assertTrue("Fail to add Bugs!", bugs.size() >= bugToAdd);
			bugs.stream().forEach(System.out::println);
			}
			catch (Exception ex) {
				// TODO: handle exception
			}
		}

		@Test
		public void test3_CreateBugType() {
			try {
			String resourceURL = serviceURL + "/new/";
			logger.info("DEBUG: Junit TESTING: test3_CreateBug ...");
			Client client = ClientBuilder.newClient();
			Integer bugToAdd = 3;
			Bug bug;
			for (int i=1; i <= bugToAdd; i++){
				bug = ClientBuilder.newClient().target(resourceURL + i)
						.request().accept(MediaType.APPLICATION_JSON)
						.post(null).readEntity(Bug.class);
				System.out.println(">>> Created/posted BugTye: " + bug);
			}
			Collection<Bug> bugs = client.target(serviceURL)
					.request().get()
					.readEntity(new GenericType<Collection<Bug>>(){});
			
			assertTrue("Fail to add Bugs!", bugs.size() >= bugToAdd);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}	
		@Test
		public void test2_DeleteBug() {
			String resourceURL = serviceURL + "/";
			logger.info("DEBUG: Junit TESTING: test2_DeleteBug...");
			Client client = ClientBuilder.newClient();
			Collection<Bug> bugs = client.target(serviceURL)
					.request().get()
					.readEntity(new GenericType<Collection<Bug>>(){});		
			
			for (Bug b: bugs) {
				client.target(resourceURL + b.getBugID()).request().delete();
			}
			
			Collection<Bug> bugsAfterDelete = client.target(serviceURL)
					.request().get()
					.readEntity(new GenericType<Collection<Bug>>(){});	
			assertTrue("Fail to read Bugs!", bugsAfterDelete.size() == 0);
		}
		
		//@Test
		public void test1_GetByID() {
			try {
			String resourceURL = serviceURL + "/1";
			logger.info("DEBUG: Junit TESTING: test1_GetMessage ...");
			
			Bug bug = ClientBuilder.newClient().target(resourceURL)
					.request().accept(MediaType.APPLICATION_JSON)
					.get().readEntity(Bug.class);
			
			assertNotNull("REST Data Service failed!", bug);
			logger.info(">>>>>> DEBUG: REST Response ..." + bug);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}	
		
		//@Test
		public void test5_UpdateBugType() {
			try {
			String resourceURL = serviceURL + "/1"; 
			logger.info("************* DEBUG: Junit TESTING: test5_UpdateBug... :" + resourceURL);
			Client client = ClientBuilder.newClient();
			Bug bug = client.target(resourceURL)
					.request().accept(MediaType.APPLICATION_JSON)
					.get().readEntity(Bug.class);
			
			assertNotNull("REST Data Service failed!", bug);
			logger.info(">>> Initial Project: " + bug);
			bug.setBugTitle(bug.getBugTitle() + "_UPDATED_JSON");
			bug = client.target(resourceURL)
					.request().accept(MediaType.APPLICATION_JSON)
					.put(Entity.entity(bug, MediaType.APPLICATION_JSON))
					.readEntity(Bug.class);
			
			logger.info(">>> Updated Project: " + bug);
			
			assertNotNull("REST Data Service failed!", bug);
			}
			catch (Exception ex) {
				// TODO: handle exception
			}
		}	

}
