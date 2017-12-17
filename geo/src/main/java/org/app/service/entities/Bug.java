package org.app.service.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@SuppressWarnings("serial")
@XmlRootElement(name="bugs") 
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class Bug implements Serializable{
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
	@XmlElement
	public Integer getBugID() {
		return bugID;
	}
	public void setBugID(Integer bugID) {
		this.bugID = bugID;
	}
	@XmlElement
	public String getBugDescription() {
		return bugDescription;
	}
	public void setBugDescription(String bugDescription) {
		this.bugDescription = bugDescription;
	}
	@XmlElement
	public String getBugTitle() {
		return bugTitle;
	}
	public void setBugTitle(String bugTitle) {
		this.bugTitle = bugTitle;
	}
	@XmlElement
	public Project getProjectID() {
		return projectID;
	}
	public void setProjectID(Project projectID) {
		this.projectID = projectID;
	}
	@XmlElement
	public Employees1 getAssignerID() {
		return assignerID;
	}
	public void setAssignerID(Employees1 assignerID) {
		this.assignerID = assignerID;
	}
	@XmlElement
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@XmlElement
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	@XmlElement
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	@XmlElement
	public BugStatus getStatus() {
		return status;
	}
	public void setStatus(BugStatus status) {
		this.status = status;
	}
	@XmlElement
	public BugType getType() {
		return type;
	}
	public void setType(BugType type) {
		this.type = type;
	}
	@XmlElement
	public Feature getFeature() {
		return feature;
	}
	public void setFeature(Feature feature) {
		this.feature = feature;
	}
	
	/* Rest Resource URL*/
	public static String BASE_URL = "http://localhost:8089/geo/rest/bugs/";
	@XmlElement(name = "link")
    public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL + this.getBugID();
        return new AtomLink(restUrl, "get-bug");
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