package org.app.service.ejb;

import java.util.Collection;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Bug;

@Path("bugs")
@Stateless @LocalBean

public class BugDataServiceEJB extends EntityRepositoryBase<Bug> implements BugDataService {

	  
private static Logger logger = Logger.getLogger(BugDataServiceEJB.class.getName());
	
	@EJB 
	private BugDataService bugDataService;
 
//	@PersistenceContext(unitName="MSD")
//	private EntityManager em;
//	// Constructor
//	public BugTypeServiceEJB() {
//	}
	// Init after constructor
	@PostConstruct
	public void init(){
		logger.info("POSTCONSTRUCT-INIT : " + this.em);
	}	
	/* CRUD operations implementation */
	//Create or Update
	@Override
	@GET 				
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Collection<Bug> toCollection() {
		logger.info("**** DEBUG REST toCollection()");
		return super.toCollection();
	}	
	
	@GET @Path("/{id}") 	
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	public Bug getById(@PathParam("id") Integer bugID){ 
		Bug bug = super.getById(bugID);
		logger.info("**** DEBUG REST getById(" + bugID +") = " + bugID);
		return bug;
	}	
	@POST @Path("/new/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) 
	public Bug addNewBug(@PathParam("id")Integer bugID){
		logger.info("**** DEBUG REST createNewBug POST");
		Bug bug = new Bug(bugID,null, "NEW Bug" + "." + bugID,null,null,null,null,null,null,null,null);
		this.add(bug);
		return bug;
	}
	
	@POST 					
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	public Collection<Bug> addIntoCollection(Bug bug) {
		// save aggregate
		super.add(bug);
		logger.info("**** DEBUG REST save aggregate POST");
		// return updated collection
		return super.toCollection();
	}
	
	@PUT @Path("/{id}") 	
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	@Override
	public Bug add(Bug bug) {
		logger.info("**** DEBUG REST restore aggregation-relation PUT");
		logger.info("**** DEBUG REST save aggregate PUT");
		bug = super.add(bug);
		// return updated collection	
		return bug;
	}	
	
	@DELETE 				
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	public Collection<Bug> removeFromCollection(Bug bug) {
		logger.info("DEBUG: called REMOVE - bug: " + bug);
		super.remove(bug);
		return super.toCollection();
	}
	
	@DELETE @Path("/{id}") 		
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) 
	public void remove(@PathParam("id")Integer bugID) {
		logger.info("DEBUG: called REMOVE - ById() for bugs >>>>>>>>>>>>>> simplified ! + id");
		Bug bug = super.getById(bugID);
		super.remove(bug);
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
