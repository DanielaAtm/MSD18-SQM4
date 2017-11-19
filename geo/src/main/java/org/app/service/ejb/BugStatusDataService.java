package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.BugStatus;

// Implement simple CRUD Operations
@Remote
public interface BugStatusDataService{
	// CREATE or UPDATE
	BugStatus addBugStatus(BugStatus bugStatusToAdd);

	// DELETE
	String removeBugStatus(BugStatus bugStatusToDelete);

	// READ
	BugStatus getBugStatusByID(Integer bugStatusID);
	Collection<BugStatus> getBugStatus();
	
	// Custom READ: custom query
	BugStatus getBugStatusByDescription(String description);
	
	// Others
	
	String getMessage();
	String sayrest();
}

