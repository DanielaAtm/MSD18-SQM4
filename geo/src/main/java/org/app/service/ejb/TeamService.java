package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;
import org.app.service.entities.Team;

@Remote
public interface TeamService {

	
	//Create or Update a Bug
		Team addTeam(Team teamToAdd);
		
		//Delete
		String removeTeam(Team teamToDelete);
		
		//Read
		Team getTeamByID(Integer teamID);
		Collection<Team> getTeams();
		
		//Custom Read: custom Query
		Team getTeamByName(String teamName);
		
	    //Others
		String getMessage();

		String sayrest();
}
