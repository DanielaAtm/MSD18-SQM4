package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.ejb.Employees1DataService;
import org.app.service.ejb.Employees1DataServiceEJB;
import org.app.service.entities.Employees1;
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
public class TestEmployees1DataServiceEJBArq {
private static Logger logger = Logger.getLogger(TestEmployees1DataServiceEJBArq.class.getName());
	
	@EJB
	private static Employees1DataService service;
	
	@Deployment // Arquilian infrastructure
	public static Archive<?> createDeployment() {
	        return ShrinkWrap
	                .create(WebArchive.class, "msd-test.war")
	                .addPackage(Employees1.class.getPackage())
	                .addClass(Employees1DataService.class)
	                .addClass(Employees1DataServiceEJB.class)
	                .addClass(EntityRepository.class)
	                .addClass(EntityRepositoryBase.class)
	                .addAsResource("META-INF/persistence.xml")
	                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}	
	
	@Test
	public void test1_GetMessage() {
		logger.info("DEBUG: Junit TESTING: testGetMessage ...");
		String response = service.getMessage();
		assertNotNull("Data Service failed!", response);
		logger.info("DEBUG: EJB Response ..." + response);
	}

	@Test
	public void test4_GetEmployees1() {
		logger.info("DEBUG: Junit TESTING: testGetEmployees1 ...");
		Collection<Employees1> employees = service.toCollection();
		assertTrue("Fail to read Employees!", employees.size() > 0);
	}

	@Test
	public void test3_AddEmployee() {
		logger.info("DEBUG: Junit TESTING: testAddEmployee ...");
		
		Integer employeeToAdd = 3;
		for (int i=1; i <= employeeToAdd; i++)
		{
			service.add(new Employees1(i, "Employee_" + (100 + i), null,null,null));
		}
		Collection<Employees1> employees = service.toCollection();
		assertTrue("Fail to add Employee!", employees.size() == employeeToAdd);
	}

	@Test
	public void test2_DeleteEmployee() {
		logger.info("DEBUG: Junit TESTING: testDeleteEmployee ...");
		
		Collection<Employees1> employees1 = service.toCollection();
		for (Employees1 e: employees1)
			service.remove(e);
		Collection<Employees1> EmployeesAfterDelete = service.toCollection();
		assertTrue("Fail to read Projects!", EmployeesAfterDelete.size() == 0);
	}		

}
