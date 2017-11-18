package org.app.service.entities;


import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
@Embeddable

public class Members {
	@ManyToOne
	private Employees1 employeeID;
	@ManyToOne
	private Team teamID;
	private String role;
	public Members(Employees1 employeeID, Team teamID, String role) {
		super();
		this.employeeID = employeeID;
		this.teamID = teamID;
		this.role = role;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Employees1 getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(Employees1 employeeID) {
		this.employeeID = employeeID;
	}
	public Team getTeamID() {
		return teamID;
	}
	public void setTeamID(Team teamID) {
		this.teamID = teamID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeeID == null) ? 0 : employeeID.hashCode());
		result = prime * result + ((teamID == null) ? 0 : teamID.hashCode());
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
		Members other = (Members) obj;
		if (employeeID == null) {
			if (other.employeeID != null)
				return false;
		} else if (!employeeID.equals(other.employeeID))
			return false;
		if (teamID == null) {
			if (other.teamID != null)
				return false;
		} else if (!teamID.equals(other.teamID))
			return false;
		return true;
	}
	
	public Members() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}