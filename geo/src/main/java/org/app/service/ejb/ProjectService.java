package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Project;
@Remote
public interface ProjectService {


	//Create or Update a Bug
	Project addProject(Project projectToAdd);
	
	//Delete
	String removeProject(Project projectToDelete);
	
	//Read
	Project getProjectByID(Integer projectID);
	Collection<Project> getProjects();
	
	//Custom Read: custom Query
	Project getProjectByName(String projectName);
	
    //Others
	String getMessage();

	String sayrest();

}
