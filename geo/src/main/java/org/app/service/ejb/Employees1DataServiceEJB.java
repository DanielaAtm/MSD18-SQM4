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
import org.app.service.entities.Employees1;
@Path("employees")
@Stateless @LocalBean
public class Employees1DataServiceEJB extends EntityRepositoryBase<Employees1> implements Employees1DataService {

private static Logger logger = Logger.getLogger(Employees1DataServiceEJB.class.getName());
@EJB 
private Employees1DataService employees1DataService;
@PostConstruct
public void init(){
	logger.info("POSTCONSTRUCT-INIT : " + this.em);
}	
/* CRUD operations implementation */
//Create or Update
@Override
@GET 				
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public Collection<Employees1> toCollection() {
	logger.info("**** DEBUG REST toCollection()");
	return super.toCollection();
}	

@GET @Path("/{id}") 	
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
public Employees1 getById(@PathParam("id") Integer employeeID){ 
	Employees1 employee = super.getById(employeeID);
	logger.info("**** DEBUG REST getById(" + employeeID +") = " + employee);
	return employee;
}	
@POST @Path("/new/{id}")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) 
public Employees1 addNewEmployee(@PathParam("id")Integer employeeID){
	logger.info("**** DEBUG REST createNewEmployee POST");
	// create project aggregate
	Employees1 employee = new Employees1(employeeID, "NEW Employee" + "." + employeeID,null,null,null);
	this.add(employee);
	return employee;
}

@POST 					
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
public Collection<Employees1> addIntoCollection(Employees1 employee) {
	super.add(employee);
	logger.info("**** DEBUG REST save aggregate POST");
	return super.toCollection();
}

@PUT @Path("/{id}") 	
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
@Override
public Employees1 add(Employees1 employee) {
	logger.info("**** DEBUG REST restore aggregation-relation PUT");
	logger.info("**** DEBUG REST save aggregate PUT");
	employee = super.add(employee);
	return employee;
}	

@DELETE 				
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
public Collection<Employees1> removeFromCollection(Employees1 employee) {
	logger.info("DEBUG: called REMOVE - employee: " + employee);
	super.remove(employee);
	return super.toCollection();
}

@DELETE @Path("/{id}") 		
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) 
public void remove(@PathParam("id")Integer employeeID) {
	logger.info("DEBUG: called REMOVE - ById() for employees >>>>>>>>>>>>>> simplified ! + id");
	Employees1 employee = super.getById(employeeID);
	super.remove(employee);
}

@GET @Path("/test") // Check if resource is up ...
@Produces({ MediaType.TEXT_PLAIN})
@Override
public String getMessage() {
	// TODO Auto-generated method stub
	return "Employees1 Sprint DataService is working...";
}

@Override
public String sayrest() {
	// TODO Auto-generated method stub
	return null;
}	

	
}
