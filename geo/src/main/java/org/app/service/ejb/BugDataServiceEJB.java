package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.service.entities.Bug;
@Stateless @LocalBean

public class BugDataServiceEJB implements BugDataService {

private static Logger logger = Logger.getLogger(BugDataServiceEJB.class.getName());
	
	/* DataService initialization */
	// Inject resource 
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	// Constructor
	public BugDataServiceEJB() {
	}
	// Init after constructor
	@PostConstruct
	public void init(){
		logger.info("POSTCONSTRUCT-INIT : " + this.em);
	}	
	/* CRUD operations implementation */
	//Create or Update
	@Override
	public Bug addBug(Bug bugToAdd) {
		em.persist(bugToAdd);
		em.flush();
		em.refresh(bugToAdd);
		return bugToAdd;
	}
	
	//Read
		@Override
	public Bug getBugByBugID(Integer bugID) {
			
			return em.find(Bug.class, bugID);
		}
	public Collection<Bug> getBugs() {
			List<Bug> bugs=em.createQuery("SELECT b FROM Bug b",Bug.class).getResultList();
			return bugs;
		}
	
	//Delete
	public String removeBug(Bug bugToDelete) {
		bugToDelete=em.merge(bugToDelete);
		em.remove(bugToDelete);
		em.flush();
		return "True";
	}
	//Custom Read
	@Override
	public Bug getBugByTitle(String bugTitle) {

		return em.createQuery("SELECT b FROM Bug b WHERE b.bugTitle= :bugTitle", Bug.class).setParameter("bugTitle", bugTitle).getSingleResult();
	}

	@Override
	public String sayrest() {

		return "Bug Service is ON.... ";
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Bug Service is ON.... ";
	}
	
	

}
