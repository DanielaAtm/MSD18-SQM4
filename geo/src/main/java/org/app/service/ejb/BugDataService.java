package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Bug;
//Implement simple CRUD operations
@Remote
public interface BugDataService {

	//Create or Update a Bug
	Bug addBug(Bug bugToAdd);
	
	//Delete
	String removeBug(Bug bugToDelete);
	
	//Read
	Bug getBugByBugID(Integer bugID);
	Collection<Bug> getBugs();
	
	//Custom Read: custom Query
	Bug getBugByTitle(String bugTitle);
	
    //Others
	String getMessage();

	String sayrest();

}
