package org.app.service.entities;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.PERSIST;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Employees1 implements Serializable{

	@Id @GeneratedValue
	private Integer employeeID;
	
	private String employeeName;
	private String employeeEmail;
	private String designation;
	@ManyToOne
	private Team team;
	@ManyToOne
	private Project project;
	@OneToMany(mappedBy="assignerID", cascade = { ALL, PERSIST })
	private List<Bug> BugList=new ArrayList<Bug>();
	
	public Employees1(Integer employeeID, String employeeName, String employeeEmail, String designation, Team team) {
		super();
		this.employeeID = employeeID;
		this.employeeName = employeeName;
		this.employeeEmail = employeeEmail;
		this.designation = designation;
		this.team = team;
	}
	public Employees1(Integer employeeID, String employeeName, String employeeEmail, String designation, Team team,
			Project project, List<Bug> bugList) {
		super();
		this.employeeID = employeeID;
		this.employeeName = employeeName;
		this.employeeEmail = employeeEmail;
		this.designation = designation;
		this.team = team;
		this.project = project;
		BugList = bugList;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public Integer getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
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
		result = prime * result + ((employeeID == null) ? 0 : employeeID.hashCode());
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
		Employees1 other = (Employees1) obj;
		if (employeeID == null) {
			if (other.employeeID != null)
				return false;
		} else if (!employeeID.equals(other.employeeID))
			return false;
		return true;
	}
	
	public Employees1() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}