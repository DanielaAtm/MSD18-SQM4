
package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.service.entities.BugType;
//Implement simple CRUD operations
@SuppressWarnings("unused")
@Remote
public interface BugTypeService extends EntityRepository<BugType> {

//	//Create or Update a Bug
//	BugType addBugType(BugType bugTypeToAdd);
//	
//	//Delete
//	String removeBugType(BugType bugTypeToDelete);
//	
//	//Read
//	BugType getBugTypeByID(Integer bugTypeID);
//	Collection<BugType> getBugTypes();
//	
	
    //Others
	String getMessage();

	String sayrest();

}
