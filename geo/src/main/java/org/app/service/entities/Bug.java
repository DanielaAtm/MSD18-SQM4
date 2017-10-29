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
	private Integer assignerID;
	@Temporal(TemporalType.DATE)
	private Date createdDate ;
	@Temporal(TemporalType.DATE)
	private Date updatedDate;
	@Temporal(TemporalType.DATE)
	private Date dueDate;
	private Integer status;
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
	public Integer getAssignerID() {
		return assignerID;
	}
	public void setAssignerID(Integer assignerID) {
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assignerID == null) ? 0 : assignerID.hashCode());
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
		if (assignerID == null) {
			if (other.assignerID != null)
				return false;
		} else if (!assignerID.equals(other.assignerID))
			return false;
		return true;
	}
	public Bug(Integer bugID, String bugDescription, String bugTitle, Project projectID, Integer assignerID,
			Date createdDate, Date updatedDate, Date dueDate, Integer status) {
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
	}
	public Bug() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
