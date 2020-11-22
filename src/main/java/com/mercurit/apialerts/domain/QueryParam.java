package com.mercurit.apialerts.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mercurit.apialerts.security.SecurityUtil;

@Entity
@Table(name = "queryparam")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QueryParam implements Serializable {
	


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String queryParamUuid=UUID.randomUUID().toString().replace("-", "");
	
	@Column(name="queryparam_key")
	private String queryParamKey;
	
	@Column(name="queryparam_value")
	private String queryParamValue;
	
	@Column(name="queryParam_status")
	private boolean queryParamStatus = true;
	
	@Column(name="created_date")
	private Date creadtedDate=new Date();
	
	@Column(name="modified_date")
	private Date modifiedDate=new Date();
	
	@Column(name="created_by")
	private String createdBy;
	
	@ManyToOne
	@JsonIgnore
	private ApiMonitors apiMonitors;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQueryParamUuid() {
		return queryParamUuid;
	}

	public void setQueryParamUuid(String queryParamUuid) {
		this.queryParamUuid = queryParamUuid;
	}

	public String getQueryParamKey() {
		return queryParamKey;
	}

	public void setQueryParamKey(String queryParamKey) {
		this.queryParamKey = queryParamKey;
	}

	public String getQueryParamValue() {
		return queryParamValue;
	}

	public void setQueryParamValue(String queryParamValue) {
		this.queryParamValue = queryParamValue;
	}

	public boolean isQueryParamStatus() {
		return queryParamStatus;
	}

	public void setQueryParamStatus(boolean queryParamStatus) {
		this.queryParamStatus = queryParamStatus;
	}

	public Date getCreadtedDate() {
		return creadtedDate;
	}

	public void setCreadtedDate(Date creadtedDate) {
		this.creadtedDate = creadtedDate;
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

	public ApiMonitors getApiMonitors() {
		return apiMonitors;
	}

	public void setApiMonitors(ApiMonitors apiMonitors) {
		this.apiMonitors = apiMonitors;
	}

	@Override
	public String toString() {
		return "QueryParam [id=" + id + ", queryParamUuid=" + queryParamUuid + ", queryParamKey=" + queryParamKey
				+ ", queryParamValue=" + queryParamValue + ", queryParamStatus=" + queryParamStatus + ", creadtedDate="
				+ creadtedDate + ", modifiedDate=" + modifiedDate + ", createdBy=" + createdBy + ", apiMonitors="
				+ apiMonitors + "]";
	}


	
	
	

}
