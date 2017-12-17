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
import org.app.service.entities.BugType;
@Path("bugTypes")
@Stateless @LocalBean

public class BugTypeServiceEJB extends EntityRepositoryBase<BugType> implements BugTypeService {

private static Logger logger = Logger.getLogger(BugTypeServiceEJB.class.getName());
	
	@EJB // injected DataService
	private BugTypeService bugTypeService;
 
	@PostConstruct
	public void init(){
		logger.info("POSTCONSTRUCT-INIT : " + this.em);
	}	
	/* CRUD operations implementation */
	//Create or Update
	@Override
	@GET 				
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Collection<BugType> toCollection() {
		logger.info("**** DEBUG REST toCollection()");
		return super.toCollection();
	}	
	
	@GET @Path("/{id}") 	
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	public BugType getById(@PathParam("id") Integer bugTypeId){ 
		BugType bugType = super.getById(bugTypeId);
		logger.info("**** DEBUG REST getById(" + bugTypeId +") = " + bugType);
		return bugType;
	}	
	@POST @Path("/new/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) 
	public BugType addNewBugType(@PathParam("id")Integer bugTypeId){
		logger.info("**** DEBUG REST createNewBugType POST");
		// create project aggregate
		BugType bugType = new BugType(bugTypeId, "NEW BugType" + "." + bugTypeId,null);
		this.add(bugType);
		return bugType;
	}
	
	@POST 					
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	public Collection<BugType> addIntoCollection(BugType bugType) {
		// save aggregate
		super.add(bugType);
		logger.info("**** DEBUG REST save aggregate POST");
		// return updated collection
		return super.toCollection();
	}
	
	@PUT @Path("/{id}") 	
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	@Override
	public BugType add(BugType bugType) {
		logger.info("**** DEBUG REST restore aggregation-relation PUT");
		logger.info("**** DEBUG REST save aggregate PUT");
		bugType = super.add(bugType);
		// return updated collection	
		return bugType;
	}	
	
	@DELETE 				
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	public Collection<BugType> removeFromCollection(BugType bugType) {
		logger.info("DEBUG: called REMOVE - bugType: " + bugType);
		super.remove(bugType);
		return super.toCollection();
	}
	
	@DELETE @Path("/{id}") 		
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) 
	public void remove(@PathParam("id")Integer bugTypeId) {
		logger.info("DEBUG: called REMOVE - ById() for projects >>>>>>>>>>>>>> simplified ! + id");
		BugType bugType = super.getById(bugTypeId);
		super.remove(bugType);
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
