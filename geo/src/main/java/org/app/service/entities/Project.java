package org.app.service.entities;

import java.io.Serializable;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.PERSIST;

@SuppressWarnings("serial")
@Entity
public class Project implements Serializable{

	@Id @GeneratedValue
	private Integer projectID;
	private String projectName;
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@OneToMany(mappedBy="projectID", cascade = { ALL, PERSIST })
	private List<Bug> BugList=new ArrayList<Bug>();
	@OneToMany(mappedBy="project", cascade = { ALL, PERSIST })
	private List<Employees1> Employees1List=new ArrayList<Employees1>();
	public Integer getProjectID() {
		return projectID;
	}
	public void setProjectID(Integer projectID) {
		this.projectID = projectID;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public List<Bug> getBugList() {
		return BugList;
	}
	public void setBugList(List<Bug> bugList) {
		BugList = bugList;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((projectID == null) ? 0 : projectID.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (projectID == null) {
			if (other.projectID != null)
				return false;
		} else if (!projectID.equals(other.projectID))
			return false;
		return true;
	}
	public Project(Integer projectID, String projectName, Date startDate, List<Bug> bugList) {
		super();
		this.projectID = projectID;
		this.projectName = projectName;
		this.startDate = startDate;
		BugList = bugList;
	}
	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}