package org.app.service.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="bugStatus") 
@XmlAccessorType(XmlAccessType.NONE)
@SuppressWarnings("serial")
@Entity
public class BugStatus implements Serializable {

	@Id @GeneratedValue
	private Integer bugStatusID;
	private String description;
	@XmlElement
	public Integer getBugStatusID() {
		return bugStatusID;
	}
	public void setBugStatusID(Integer bugStatusID) {
		this.bugStatusID = bugStatusID;
	}
	@XmlElement
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public static String BASE_URL = "http://localhost:8089/geo/rest/bugsStatus/";
	@XmlElement(name = "link")
    public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL + this.getBugStatusID();
        return new AtomLink(restUrl, "get-bugStatus");
    }	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bugStatusID == null) ? 0 : bugStatusID.hashCode());
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
		BugStatus other = (BugStatus) obj;
		if (bugStatusID == null) {
			if (other.bugStatusID != null)
				return false;
		} else if (!bugStatusID.equals(other.bugStatusID))
			return false;
		return true;
	}
	public BugStatus(Integer bugStatusID, String description) {
		super();
		this.bugStatusID = bugStatusID;
		this.description = description;
	}
	public BugStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}