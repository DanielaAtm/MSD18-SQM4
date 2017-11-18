package org.app.service.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Bug {
	@Id @GeneratedValue
	private Integer bugID;
	private String bugDescription;
	private String bugTitle;
	@ManyToOne
	private Project projectID;
	@ManyToOne
	private Employees1 assignerID;
	@Temporal(TemporalType.DATE)
	private Date createdDate ;
	@Temporal(TemporalType.DATE)
	private Date updatedDate;
	@Temporal(TemporalType.DATE)
	private Date dueDate;
	@ManyToOne
	private BugStatus status;
	@ManyToOne
	private BugType type;
	@ManyToOne
	private Feature feature;
	public Integer getBugID() {
		return bugID;
	}
	public void setBugID(Integer bugID) {
		this.bugID = bugID;
	}
	public String getBugDescription() {
		return bugDescription;
	}
	public void setBugDescription(String bugDescription) {
		this.bugDescription = bugDescription;
	}
	public String getBugTitle() {
		return bugTitle;
	}
	public void setBugTitle(String bugTitle) {
		this.bugTitle = bugTitle;
	}
	public Project getProjectID() {
		return projectID;
	}
	public void setProjectID(Project projectID) {
		this.projectID = projectID;
	}
	public Employees1 getAssignerID() {
		return assignerID;
	}
	public void setAssignerID(Employees1 assignerID) {
		this.assignerID = assignerID;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public BugStatus getStatus() {
		return status;
	}
	public void setStatus(BugStatus status) {
		this.status = status;
	}
	public BugType getType() {
		return type;
	}
	public void setType(BugType type) {
		this.type = type;
	}
	public Feature getFeature() {
		return feature;
	}
	public void setFeature(Feature feature) {
		this.feature = feature;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bugID == null) ? 0 : bugID.hashCode());
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
		Bug other = (Bug) obj;
		if (bugID == null) {
			if (other.bugID != null)
				return false;
		} else if (!bugID.equals(other.bugID))
			return false;
		return true;
	}
	public Bug(Integer bugID, String bugDescription, String bugTitle, Project projectID, Employees1 assignerID,
			Date createdDate, Date updatedDate, Date dueDate, BugStatus status, BugType type, Feature feature) {
		super();
		this.bugID = bugID;
		this.bugDescription = bugDescription;
		this.bugTitle = bugTitle;
		this.projectID = projectID;
		this.assignerID = assignerID;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.dueDate = dueDate;
		this.status = status;
		this.type = type;
		this.feature = feature;
	}
	public Bug() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}