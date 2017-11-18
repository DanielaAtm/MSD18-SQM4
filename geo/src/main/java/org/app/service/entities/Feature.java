package org.app.service.entities;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.PERSIST;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Feature {
	@Id  @GeneratedValue
	private Integer featureID;
	private String featureName;
	private String description;
	@OneToMany(mappedBy="feature", cascade = { ALL, PERSIST })
	private List<Bug> BugList=new ArrayList<Bug>();
	public Integer getFeatureID() {
		return featureID;
	}
	public void setFeatureID(Integer featureID) {
		this.featureID = featureID;
	}
	public String getFeatureName() {
		return featureName;
	}
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((featureID == null) ? 0 : featureID.hashCode());
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
		Feature other = (Feature) obj;
		if (featureID == null) {
			if (other.featureID != null)
				return false;
		} else if (!featureID.equals(other.featureID))
			return false;
		return true;
	}
	public Feature(Integer featureID, String featureName, String description) {
		super();
		this.featureID = featureID;
		this.featureName = featureName;
		this.description = description;
	}
	public Feature() {
		super();
		// TODO Auto-generated constructor stub
	}
}