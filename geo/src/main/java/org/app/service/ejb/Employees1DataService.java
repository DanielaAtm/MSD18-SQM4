package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Employees1;
@Remote
public interface Employees1DataService extends EntityRepository<Employees1> {
	//Create or Update a Bug
	Employees1 addEmployee(Employees1 employeeToAdd);
	
	//Delete
	String removeEmployee(Employees1 employeeToDelete);
	
	//Read
	Employees1 getEmployeeID(Integer employeeID);
	Collection<Employees1> getEmployees1();
	
	//Custom Read: custom Query
	Employees1 getEmployeeByName(String employeeName);
	
    //Others
	String getMessage();

	String sayrest();
	
	

}
