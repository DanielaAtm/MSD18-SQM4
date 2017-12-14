package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.service.entities.Project;

@Stateless @LocalBean
public class ProjectServiceEJB implements ProjectService {

private static Logger logger = Logger.getLogger(ProjectServiceEJB.class.getName());
	
	/* DataService initialization */
	// Inject resource 
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	// Constructor
	public ProjectServiceEJB() {
	}
	// Init after constructor
	@PostConstruct
	public void init(){
		logger.info("POSTCONSTRUCT-INIT : " + this.em);
	}	
	
	@Override
	public Project addProject(Project projectToAdd) {
		em.persist(projectToAdd);
		em.flush();
		em.refresh(projectToAdd);
		return projectToAdd;
	}

	@Override
	public String removeProject(Project projectToDelete) {
		projectToDelete=em.merge(projectToDelete);
		em.remove(projectToDelete);
		em.flush();
		return "True";	
	}

	@Override
	public Project getProjectByID(Integer projectID) {
		return em.find(Project.class, projectID);
	}

	@Override
	public Collection<Project> getProjects() {
		List<Project> projects=em.createQuery("SELECT p FROM Project p",Project.class).getResultList();
		return projects;
	}

	@Override
	public Project getProjectByName(String projectName) {
		return em.createQuery("SELECT p FROM Project p WHERE p.projectName= :projectName", Project.class).setParameter("projectName", projectName).getSingleResult();

	}

	@Override
	public String getMessage() {
		return "Bug Service is ON.... ";		
	}

	@Override
	public String sayrest() {
		return "Bug Service is ON.... ";
	}

}
