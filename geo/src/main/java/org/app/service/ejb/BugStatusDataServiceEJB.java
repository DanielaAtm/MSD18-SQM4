package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.service.entities.BugStatus;

@Stateless @LocalBean
public class BugStatusDataServiceEJB implements BugStatusDataService{
	private static Logger logger = Logger.getLogger(BugStatusDataServiceEJB.class.getName());
	
	/* DataService initialization */
	// Inject resource 
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	// Constructor
	public BugStatusDataServiceEJB() {
	}
	// Init after constructor
	@PostConstruct
	public void init(){
		logger.info("POSTCONSTRUCT-INIT : " + this.em);
	}		

	/* CRUD operations implementation */
	// CREATE or UPDATE
	@Override
	public BugStatus addBugStatus(BugStatus bugStatusToAdd){
		em.persist(bugStatusToAdd);
		em.flush();
		// transactions are managed by default by container
		em.refresh(bugStatusToAdd);
		return bugStatusToAdd;
	}	
	// READ
	@Override
	public BugStatus getBugStatusByID(Integer bugStatusID) {
		return em.find(BugStatus.class, bugStatusID);
	}	
	public Collection<BugStatus> getBugStatus(){
		List<BugStatus> bugStatus = em.createQuery("SELECT bs FROM BugStatus bs", BugStatus.class)
				.getResultList();
		return bugStatus;
	}
	// REMOVE
	@Override
	public String removeBugStatus(BugStatus bugStatusToDelete){
		bugStatusToDelete = em.merge(bugStatusToDelete);
		em.remove(bugStatusToDelete);
		em.flush();
		return "True";
	}
	
	// Custom READ: custom query
	@Override
	public BugStatus getBugStatusByDescription(String description) {
		return em.createQuery("SELECT bs FROM BugStatus bs WHERE bs.description = :description", BugStatus.class)
				.setParameter("description", description)
				.getSingleResult();
	}	
	
	// Others
	@Override
	public String getMessage() {
		return "BugStatusServiceEJB is ON... ";
	}
	@Override
	public String sayrest() {

		return "BugStatus Service is ON.... ";
	}
}


