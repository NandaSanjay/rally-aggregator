package com.san.rallyaggregator.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RallyItem {
	@Id
	private String Id;
	private String name;
	private String requirement;
	private String severity;
	private String state;
	private String fixedIn;
	private String submitter;
	private String owner;
	private String color;

	public RallyItem() {
		super();
	}

	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "RallyItem [Id=" + Id + ", name=" + name + ", status=" + requirement
				+ ", type=" + severity + ", owner=" + owner + ", submitter="
				+ fixedIn + ", state=" + owner + "]";
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return requirement;
	}
	public void setStatus(String status) {
		this.requirement = status;
	}
	public String getType() {
		return severity;
	}
	public void setType(String type) {
		this.severity = type;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getSubmitter() {
		return fixedIn;
	}
	public void setSubmitter(String submitter) {
		this.fixedIn = submitter;
	}
	public String getState() {
		return owner;
	}
	public void setState(String state) {
		this.owner = state;
	}
	public String getRequirement() {
		return requirement;
	}
	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getFixedIn() {
		return fixedIn;
	}
	public void setFixedIn(String fixedIn) {
		this.fixedIn = fixedIn;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	

}
