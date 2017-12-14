package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.service.entities.BugType;
@Stateless @LocalBean

public class BugTypeServiceEJB implements BugTypeService {

private static Logger logger = Logger.getLogger(BugTypeServiceEJB.class.getName());
	
	/* DataService initialization */
	// Inject resource 
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	// Constructor
	public BugTypeServiceEJB() {
	}
	// Init after constructor
	@PostConstruct
	public void init(){
		logger.info("POSTCONSTRUCT-INIT : " + this.em);
	}	
	/* CRUD operations implementation */
	//Create or Update
	@Override
	public BugType addBugType(BugType bugTypeToAdd) {
		em.persist(bugTypeToAdd);
		em.flush();
		em.refresh(bugTypeToAdd);
		return bugTypeToAdd;
	}
	
	//Read
		@Override
	public BugType getBugTypeByID(Integer bugTypeID) {
			
			return em.find(BugType.class, bugTypeID);
		}
	public Collection<BugType> getBugTypes() {
			List<BugType> bugTypes=em.createQuery("SELECT bt FROM BugType bt",BugType.class).getResultList();
			return bugTypes;
		}
	
	//Delete
	public String removeBugType(BugType bugTypeToDelete) {
		bugTypeToDelete=em.merge(bugTypeToDelete);
		em.remove(bugTypeToDelete);
		em.flush();
		return "True";
	}

	@Override
	public String sayrest() {

		return "BugType Service is ON.... ";
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "BugType Service is ON.... ";
	}
	
	

}
