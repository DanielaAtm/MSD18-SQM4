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
import org.app.service.ejb.Employees1DataService;
import org.app.service.entities.Employees1;
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
public class TestEmployees1DataServiceRESTArq {
	private static Logger logger=Logger.getLogger(TestEmployees1DataServiceRESTArq.class.getName());
	private static String serviceURL="http://localhost:8089/geo/rest/employees";
	
	@Deployment
	public static Archive<?> createDeployment() {
	        return ShrinkWrap
	                .create(WebArchive.class, "msd-test.war")
	                .addPackage(Employees1.class.getPackage())
	                .addPackage(Employees1DataService.class.getPackage())
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
	public void test4_GetEmployees() {
		logger.info("DEBUG: Junit TESTING: test4_GetEmployees ...");
		Collection<Employees1> employees = ClientBuilder.newClient()
				.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Employees1>>(){});
		assertTrue("Fail to read Employees!", employees.size() > 0);
		employees.stream().forEach(System.out::println);
	}

	@Test
	public void test3_AddEmployee() {

		logger.info("DEBUG: Junit TESTING: test3_AddBugType ...");
		Client client = ClientBuilder.newClient();
		Collection<Employees1> employees;
		Integer employeeToAdd = 3;
		Employees1 employee;
		for (int i=1; i <= employeeToAdd; i++){
			employee = new Employees1(i, "Employee_" + (100 + i),null,null,null,null,null);
			employees = client.target(serviceURL)
				.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(employee, MediaType.APPLICATION_JSON))
				.readEntity(new GenericType<Collection<Employees1>>(){});
			assertTrue("Fail to read Employees!", employees.size() > 0);
		}
		employees = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Employees1>>(){});
		assertTrue("Fail to add Employees!", employees.size() >= employeeToAdd);
		employees.stream().forEach(System.out::println);

	}

	@Test
	public void test3_CreateEmployee() {
		
		String resourceURL = serviceURL + "/new/";
		logger.info("DEBUG: Junit TESTING: test3_CreateEmployee ...");
		Client client = ClientBuilder.newClient();
		Integer employeeToAdd = 3;
		Employees1 employee;
		for (int i=1; i <= employeeToAdd; i++){
			employee = ClientBuilder.newClient().target(resourceURL + i)
					.request().accept(MediaType.APPLICATION_JSON)
					.post(null).readEntity(Employees1.class);
			System.out.println(">>> Created/posted Employee: " + employee);
		}
		Collection<Employees1> employees = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Employees1>>(){});
		
		assertTrue("Fail to add Employees!", employees.size() >= employeeToAdd);
		
	}	
	@Test
	public void test2_DeleteEmployee() {
		String resourceURL = serviceURL + "/";
		logger.info("DEBUG: Junit TESTING: test2_DeleteEmployee ...");
		Client client = ClientBuilder.newClient();
		Collection<Employees1> employees = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Employees1>>(){});		
		
		for (Employees1 e: employees) {
			client.target(resourceURL + e.getEmployeeID()).request().delete();
		}
		
		Collection<Employees1> employeesAfterDelete = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Employees1>>(){});	
		assertTrue("Fail to read Employees!", employeesAfterDelete.size() == 0);
	}
	
	//@Test
	public void test1_GetByID() {
		try {
		String resourceURL = serviceURL + "/1";
		logger.info("DEBUG: Junit TESTING: test1_GetMessage ...");
		
		Employees1 employee = ClientBuilder.newClient().target(resourceURL)
				.request().accept(MediaType.APPLICATION_JSON)
				.get().readEntity(Employees1.class);
		
		assertNotNull("REST Data Service failed!", employee);
		logger.info(">>>>>> DEBUG: REST Response ..." + employee);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}	
	
	//@Test
	public void test5_UpdateEmployee() {
		try {
		String resourceURL = serviceURL + "/1"; 
		logger.info("************* DEBUG: Junit TESTING: test5_UpdateEmployee ... :" + resourceURL);
		Client client = ClientBuilder.newClient();
		Employees1 employee = client.target(resourceURL)
				.request().accept(MediaType.APPLICATION_JSON)
				.get().readEntity(Employees1.class);
		
		assertNotNull("REST Data Service failed!", employee);
		logger.info(">>> Initial Project: " + employee);
		employee.setEmployeeName(employee.getEmployeeName()+ "_UPDATED_JSON");
		employee = client.target(resourceURL)
				.request().accept(MediaType.APPLICATION_JSON)
				.put(Entity.entity(employee, MediaType.APPLICATION_JSON))
				.readEntity(Employees1.class);
		
		logger.info(">>> Updated Project: " + employee);
		
		assertNotNull("REST Data Service failed!", employee);
		}
		catch (Exception ex) {
			// TODO: handle exception
		}
	}	

}
