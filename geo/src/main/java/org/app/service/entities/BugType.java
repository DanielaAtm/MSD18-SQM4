package org.app.service.entities;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class BugType implements Serializable {

	@Id @GeneratedValue
	private Integer bugTypeId;
	private String bugTypeTitle;
	private String bugTypeDescription;
	public Integer getBugTypeId() {
		return bugTypeId;
	}
	public void setBugTypeId(Integer bugTypeId) {
		this.bugTypeId = bugTypeId;
	}
	public String getBugTypeTitle() {
		return bugTypeTitle;
	}
	public void setBugTypeTitle(String bugTypeTitle) {
		this.bugTypeTitle = bugTypeTitle;
	}
	public String getBugTypeDescription() {
		return bugTypeDescription;
	}
	public void setBugTypeDescription(String bugTypeDescription) {
		this.bugTypeDescription = bugTypeDescription;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bugTypeId == null) ? 0 : bugTypeId.hashCode());
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
		BugType other = (BugType) obj;
		if (bugTypeId == null) {
			if (other.bugTypeId != null)
				return false;
		} else if (!bugTypeId.equals(other.bugTypeId))
			return false;
		return true;
	}
	public BugType(Integer bugTypeId, String bugTypeTitle, String bugTypeDescription) {
		super();
		this.bugTypeId = bugTypeId;
		this.bugTypeTitle = bugTypeTitle;
		this.bugTypeDescription = bugTypeDescription;
	}
	public BugType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}