package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Employees1;
import org.app.service.entities.Members;

@Remote
public interface MembersDataService {
	
	Members addMember(Members memberToAdd);
	
	String removeMember(Members memberToDelete);
	
	Members getMemberByEmployeeID(Employees1 employeeID);
	Collection<Members> getMembers();
	
	String sayRest();

	String getMessage();
	

}
