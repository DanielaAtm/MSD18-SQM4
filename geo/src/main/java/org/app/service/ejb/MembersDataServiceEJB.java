package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.service.entities.Employees1;
import org.app.service.entities.Members;

@Stateless @LocalBean
public class MembersDataServiceEJB implements MembersDataService {

private static Logger logger = Logger.getLogger(MembersDataServiceEJB.class.getName());
	
	/* DataService initialization */
	// Inject resource 
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	// Constructor
	public MembersDataServiceEJB() {
	}
	// Init after constructor
	@PostConstruct
	public void init(){
		logger.info("POSTCONSTRUCT-INIT : " + this.em);
	}	
	
	@Override
	public Members addMember(Members memberToAdd) {
		em.persist(memberToAdd);
		em.flush();
		em.refresh(memberToAdd);
		return memberToAdd;
	}

	@Override
	public String removeMember(Members memberToDelete) {
		memberToDelete=em.merge(memberToDelete);
		em.remove(memberToDelete);
		em.flush();
		return "True";
	}

	@Override
	public Members getMemberByEmployeeID(Employees1 employeeID) {
		return em.find(Members.class, employeeID);
	}

	@Override
	public Collection<Members> getMembers() {
		List<Members> members=em.createQuery("SELECT m FROM Members m",Members.class).getResultList();
		return members;
	}

	@Override
	public String sayRest() {

		return "Members Service is ON.... ";
	}
	@Override
	public String getMessage() {
		return "Members Service is ON.... ";
	}
	
	

}
