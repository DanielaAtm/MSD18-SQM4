package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;

import java.util.logging.Logger;

import javax.ejb.EJB;


import org.app.service.ejb.MembersDataService;
import org.app.service.ejb.MembersDataServiceEJB;
import org.app.service.entities.Members;
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
public class TestMembersServiceEJBArq {
	private static Logger logger = 
			Logger.getLogger(TestMembersServiceEJBArq.class.getName());
	
	@EJB 
	// EJB DataService Ref
	private static MembersDataService service;
	
	@Deployment 
	// Arquilian infrastructure
	public static Archive<?> createDeployment(){
		return ShrinkWrap.create(WebArchive.class,"msd-test.war")
						.addPackage(Members.class.getPackage())
						.addClass(MembersDataService.class)
						.addClass(MembersDataServiceEJB.class)
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

//	@Test
//	public void test2_DeleteMember() {
//		logger.info("DEBUG: Junit TESTING: testDeleteMember ...");
//		
//		Collection<Members> members = service.getMembers();
//		for (Members m: members)
//			service.removeMember(m);
//		Collection<Members> membersAfterDelete = service.getMembers();
//		assertTrue("Fail to read bugs!", membersAfterDelete.size() == 0);
//	}	
}
