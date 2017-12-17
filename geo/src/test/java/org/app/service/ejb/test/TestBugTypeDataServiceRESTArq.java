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
import org.app.service.ejb.BugTypeService;
import org.app.service.entities.BugType;
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
public class TestBugTypeDataServiceRESTArq {
	private static Logger logger = Logger.getLogger(TestBugTypeDataServiceRESTArq.class.getName());

	private static String serviceURL = "http://localhost:8089/geo/rest/bugTypes";	
	
	@Deployment
	public static Archive<?> createDeployment() {
	        return ShrinkWrap
	                .create(WebArchive.class, "msd-test.war")
	                .addPackage(BugType.class.getPackage())
	                .addPackage(BugTypeService.class.getPackage())
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
	public void test4_GetBugTypes() {
		logger.info("DEBUG: Junit TESTING: test4_GetBugTypes ...");
		Collection<BugType> bugTypes = ClientBuilder.newClient()
				.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<BugType>>(){});
		assertTrue("Fail to read BugTypes!", bugTypes.size() > 0);
		bugTypes.stream().forEach(System.out::println);
	}

	@Test
	public void test3_AddBugType() {
		try {
		logger.info("DEBUG: Junit TESTING: test3_AddBugType ...");
		Client client = ClientBuilder.newClient();
		Collection<BugType> bugTypes;
		Integer bugTypesToAdd = 3;
		BugType bugType;
		for (int i=1; i <= bugTypesToAdd; i++){
			bugType = new BugType(i, "BugType_" + (100 + i),null);
			bugTypes = client.target(serviceURL)
				.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(bugType, MediaType.APPLICATION_JSON))
				.readEntity(new GenericType<Collection<BugType>>(){});
			assertTrue("Fail to read BugTypes!", bugTypes.size() > 0);
		}
		bugTypes = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<BugType>>(){});
		assertTrue("Fail to add BugTypes!", bugTypes.size() >= bugTypesToAdd);
		bugTypes.stream().forEach(System.out::println);
		}
		catch (Exception ex) {
			// TODO: handle exception
		}
	}

	@Test
	public void test3_CreateBugType() {
		try {
		String resourceURL = serviceURL + "/new/";
		logger.info("DEBUG: Junit TESTING: test3_CreateBugType ...");
		Client client = ClientBuilder.newClient();
		Integer bugTypesToAdd = 3;
		BugType bugType;
		for (int i=1; i <= bugTypesToAdd; i++){
			bugType = ClientBuilder.newClient().target(resourceURL + i)
					.request().accept(MediaType.APPLICATION_JSON)
					.post(null).readEntity(BugType.class);
			System.out.println(">>> Created/posted BugTye: " + bugType);
		}
		Collection<BugType> bugTypes = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<BugType>>(){});
		
		assertTrue("Fail to add Projects!", bugTypes.size() >= bugTypesToAdd);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}	
	@Test
	public void test2_DeleteBugType() {
		String resourceURL = serviceURL + "/";
		logger.info("DEBUG: Junit TESTING: test2_DeleteBugType ...");
		Client client = ClientBuilder.newClient();
		Collection<BugType> bugTypes = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<BugType>>(){});		
		
		for (BugType bt: bugTypes) {
			client.target(resourceURL + bt.getBugTypeId()).request().delete();
		}
		
		Collection<BugType> bugTypesAfterDelete = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<BugType>>(){});	
		assertTrue("Fail to read BugTypes!", bugTypesAfterDelete.size() == 0);
	}
	
	//@Test
	public void test1_GetByID() {
		try {
		String resourceURL = serviceURL + "/1";
		logger.info("DEBUG: Junit TESTING: test1_GetMessage ...");
		
		BugType bugType = ClientBuilder.newClient().target(resourceURL)
				.request().accept(MediaType.APPLICATION_JSON)
				.get().readEntity(BugType.class);
		
		assertNotNull("REST Data Service failed!", bugType);
		logger.info(">>>>>> DEBUG: REST Response ..." + bugType);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}	
	
	//@Test
	public void test5_UpdateBugType() {
		try {
		String resourceURL = serviceURL + "/1"; 
		logger.info("************* DEBUG: Junit TESTING: test5_UpdateBugType ... :" + resourceURL);
		Client client = ClientBuilder.newClient();
		BugType bugType = client.target(resourceURL)
				.request().accept(MediaType.APPLICATION_JSON)
				.get().readEntity(BugType.class);
		
		assertNotNull("REST Data Service failed!", bugType);
		logger.info(">>> Initial Project: " + bugType);
		bugType.setBugTypeTitle(bugType.getBugTypeTitle() + "_UPDATED_JSON");
		bugType = client.target(resourceURL)
				//.request().accept(MediaType.APPLICATION_XML).header("Content-Type", "application/xml")
				.request().accept(MediaType.APPLICATION_JSON)
				.put(Entity.entity(bugType, MediaType.APPLICATION_JSON))
				.readEntity(BugType.class);
		
		logger.info(">>> Updated Project: " + bugType);
		
		assertNotNull("REST Data Service failed!", bugType);
		}
		catch (Exception ex) {
			// TODO: handle exception
		}
	}	
	
}
