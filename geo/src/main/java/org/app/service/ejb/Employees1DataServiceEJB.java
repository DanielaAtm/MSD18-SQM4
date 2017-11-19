package org.app.service.ejb;

import java.util.Collection;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Employees1;
@Stateless @LocalBean
public class Employees1DataServiceEJB extends EntityRepositoryBase<Employees1> implements Employees1DataService {

private static Logger logger = Logger.getLogger(Employees1DataServiceEJB.class.getName());
	
	@PostConstruct
	public void init() {
		logger.info("POSTCONSTRUCT-INIT injected EntityManager: " + this.em);
	}
	@Override
	public Employees1 addEmployee(Employees1 employeeToAdd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String removeEmployee(Employees1 employeeToDelete) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employees1 getEmployeeID(Integer employeeID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Employees1> getEmployees1() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employees1 getEmployeeByName(String employeeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Bug Service is ON.... ";
	}

	@Override
	public String sayrest() {
		// TODO Auto-generated method stub
		return "Bug Service is ON.... ";
	}

	
}
