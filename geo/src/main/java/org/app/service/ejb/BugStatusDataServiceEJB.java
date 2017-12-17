package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.service.entities.BugStatus;
import org.app.service.entities.Employees1;
@Path("bugsStatus")
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
	@PUT @Path("/{id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })		
	public BugStatus addBugStatus(BugStatus bugStatusToAdd){
		em.persist(bugStatusToAdd);
		em.flush();
		// transactions are managed by default by container
		em.refresh(bugStatusToAdd);
		return bugStatusToAdd;
	}	
	// READ
	@GET @Path("/{id}")		
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public BugStatus getBugStatusByID(@PathParam("id")Integer bugStatusID) {
		return em.find(BugStatus.class, bugStatusID);
	}	
	@GET 					
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	public Collection<BugStatus> getBugStatus(){
		List<BugStatus> bugStatus = em.createQuery("SELECT bs FROM BugStatus bs", BugStatus.class)
				.getResultList();
		logger.info("**** DEBUG REST bugsStatus.size()= " + bugStatus.size());
		return bugStatus;
	}
	// REMOVE
	@DELETE 				
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Override
	public String removeBugStatus(BugStatus bugStatusToDelete){
		bugStatusToDelete = em.merge(bugStatusToDelete);
		em.remove(bugStatusToDelete);
		em.flush();
		return "True";
	}
	
	
	// Custom READ: custom query
	@GET @Path("/{bugStatusDescription}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@Override
	public BugStatus getBugStatusByDescription(@PathParam("bugStatusDescription")String description) {
		return em.createQuery("SELECT bs FROM BugStatus bs WHERE bs.description = :description", BugStatus.class)
				.setParameter("description", description)
				.getSingleResult();
	}	
	
	//Add new BUGSTATUS
	
	@POST @Path("/new/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) 
	// Aggregate factory method
	public BugStatus addNewBugStatus(@PathParam("id")Integer bugStatusID){
		logger.info("**** DEBUG REST createNewBugStatus POST");
		// create project aggregate
		BugStatus bugStatus = new BugStatus(bugStatusID, "NEW BugStatus" + "." + bugStatusID);
		this.add(bugStatus);
		return bugStatus;
	}
	
		private BugStatus add(BugStatus bugStatus) {
			try {
				em.merge(bugStatus);
				return bugStatus;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {

			}
		
	}
		// Others
	@GET @Path("/test")
	@Produces({ MediaType.TEXT_PLAIN})
	@Override
	public String getMessage() {
		return "BugStatusServiceEJB is ON... ";
	}
	@Override
	public String sayrest() {

		return "BugStatus Service is ON.... ";
	}
}


