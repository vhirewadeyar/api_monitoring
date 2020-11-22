package com.mercurit.apialerts.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "project")
public class Project implements Serializable
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	private String projectUuid=UUID.randomUUID().toString().replace("-", "");
	
	@Column(name="project_name")
	private String projectName;
	
	@Column(name="monitor_description")
	private String monitorDescription;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="project_status")
	private boolean projectStatus=true;
	
	@OneToMany(mappedBy="project",fetch= FetchType.EAGER,cascade=CascadeType.ALL)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public Set<ApiMonitors> monitors=new HashSet<>();
	
	@ManyToOne(targetEntity= Users.class)
	@JsonIgnore
	public Users user;
	
	@Column(name="Created_by")
	private String createdBy;
	
	@Column(name="Modified_by")
	private String modifiedBy;
	
	@Column(name="Created_date")
	private Date createdDate=new Date();
	
	@Column(name="Modified_date")
	private Date modifiedDate;
	
	@Transient
	@JsonSerialize
	@JsonDeserialize
	private String successMessage;
	
	

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProjectUuid() {
		return projectUuid;
	}

	public void setProjectUuid(String projectUuid) {
		this.projectUuid = projectUuid;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getMonitorDescription() {
		return monitorDescription;
	}

	public void setMonitorDescription(String monitorDescription) {
		this.monitorDescription = monitorDescription;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(boolean projectStatus) {
		this.projectStatus = projectStatus;
	}

	public Set<ApiMonitors> getMonitors() {
		return monitors;
	}

	public void setMonitors(Set<ApiMonitors> monitors) {
		this.monitors = monitors;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", projectUuid=" + projectUuid + ", projectName=" + projectName
				+ ", monitorDescription=" + monitorDescription + ", userName=" + userName + ", projectStatus="
				+ projectStatus + ", monitors=" + monitors + ", user=" + user + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
				+ ", successMessage=" + successMessage + "]";
	}





	
	

}
