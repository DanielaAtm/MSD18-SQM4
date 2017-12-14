package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.app.service.entities.Team;

@Stateless @LocalBean
public class TeamServiceEJB implements TeamService {
private static Logger logger = Logger.getLogger(TeamServiceEJB.class.getName());
	
	/* DataService initialization */
	// Inject resource 
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	// Constructor
	public TeamServiceEJB() {
	}
	// Init after constructor
	@PostConstruct
	public void init(){
		logger.info("POSTCONSTRUCT-INIT : " + this.em);
	}	
	@Override
	public Team addTeam(Team teamToAdd) {
		em.persist(teamToAdd);
		em.flush();
		em.refresh(teamToAdd);
		return teamToAdd;
	}

	@Override
	public String removeTeam(Team teamToDelete) {
		teamToDelete=em.merge(teamToDelete);
		em.remove(teamToDelete);
		em.flush();
		return "True";
	}

	@Override
	public Team getTeamByID(Integer teamID) {
		return em.find(Team.class, teamID);
	}

	@Override
	public Collection<Team> getTeams() {
		List<Team> teams=em.createQuery("SELECT t FROM Team t",Team.class).getResultList();
		return teams;
	}

	@Override
	public Team getTeamByName(String teamName) {
		return em.createQuery("SELECT t FROM Team t WHERE t.teamName= :teamName", Team.class).setParameter("teamName", teamName).getSingleResult();

	}

	@Override
	public String getMessage() {
		return "Bug Service is ON.... ";

	}

	@Override
	public String sayrest() {
		// TODO Auto-generated method stub
		return "Bug Service is ON.... ";
	}

	
}
