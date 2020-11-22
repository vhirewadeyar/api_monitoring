package com.mercurit.apialerts.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pathParam")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PathParam  implements Serializable{


	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String pathParamUuid=UUID.randomUUID().toString().replace("-", "");
	
	@Column(name="pathparam_key")
	private String pathParamKey;
	
	@Column(name="pathparam_value")
	private String pathParamValue;
	
	@Column(name="pathparam_status")
	private boolean pathParamStatus=true;
	
	@Column(name="created_date")
	private Date createdDate = new Date();
	
	@Column(name="modified_date")
	private Date modifiedDate;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="isJsonpath_assertion")
	private boolean isJsonPathAssertion=false;
	
	@ManyToOne(targetEntity= ApiMonitors.class)
	@JsonIgnore
	private ApiMonitors apiMonitors;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPathParamUuid() {
		return pathParamUuid;
	}

	public void setPathParamUuid(String pathParamUuid) {
		this.pathParamUuid = pathParamUuid;
	}

	public String getPathParamKey() {
		return pathParamKey;
	}

	public void setPathParamKey(String pathParamKey) {
		this.pathParamKey = pathParamKey;
	}

	public String getPathParamValue() {
		return pathParamValue;
	}

	public void setPathParamValue(String pathParamValue) {
		this.pathParamValue = pathParamValue;
	}

	public boolean isPathParamStatus() {
		return pathParamStatus;
	}

	public void setPathParamStatus(boolean pathParamStatus) {
		this.pathParamStatus = pathParamStatus;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public boolean isJsonPathAssertion() {
		return isJsonPathAssertion;
	}

	public void setJsonPathAssertion(boolean isJsonPathAssertion) {
		this.isJsonPathAssertion = isJsonPathAssertion;
	}

	public ApiMonitors getApiMonitors() {
		return apiMonitors;
	}

	public void setApiMonitors(ApiMonitors apiMonitors) {
		this.apiMonitors = apiMonitors;
	}


	@Override
	public String toString() {
		return "PathParam [id=" + id + ", pathParamUuid=" + pathParamUuid + ", pathParamKey=" + pathParamKey
				+ ", pathParamValue=" + pathParamValue + ", pathParamStatus=" + pathParamStatus + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + ", createdBy=" + createdBy + ", isJsonPathAssertion="
				+ isJsonPathAssertion + ", apiMonitors=" + apiMonitors + "]";
	}



	
}
