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
import com.mercurit.apialerts.security.SecurityUtil;

@Entity
@Table(name = "header")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Headers implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String headersUuid=UUID.randomUUID().toString().replace("-", "");
	
	@Column(name="header_key")
	private String headerKey;
	
	@Column(name="header_value")
	private String headerValue;
	
	@Column(name="header_status")
	private boolean headerStatus = true;
	
	@Column(name="created_date")
	private Date creadtedDate=new Date();
	
	@Column(name="modified_date")
	private Date modifiedDate=new Date();
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="isAssert_header")
	private boolean isAssertHeader=false;
	
	@ManyToOne(targetEntity= ApiMonitors.class)
	@JsonIgnore
	private ApiMonitors apiMonitors;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHeadersUuid() {
		return headersUuid;
	}

	public void setHeadersUuid(String headersUuid) {
		this.headersUuid = headersUuid;
	}

	public String getHeaderKey() {
		return headerKey;
	}

	public void setHeaderKey(String headerKey) {
		this.headerKey = headerKey;
	}

	public String getHeaderValue() {
		return headerValue;
	}

	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}

	public boolean isHeaderStatus() {
		return headerStatus;
	}

	public void setHeaderStatus(boolean headerStatus) {
		this.headerStatus = headerStatus;
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

	public boolean isAssertHeader() {
		return isAssertHeader;
	}

	public void setAssertHeader(boolean isAssertHeader) {
		this.isAssertHeader = isAssertHeader;
	}

	public ApiMonitors getApiMonitors() {
		return apiMonitors;
	}

	public void setApiMonitors(ApiMonitors apiMonitors) {
		this.apiMonitors = apiMonitors;
	}

	@Override
	public String toString() {
		return "Headers [id=" + id + ", headersUuid=" + headersUuid + ", headerKey=" + headerKey + ", headerValue="
				+ headerValue + ", headerStatus=" + headerStatus + ", creadtedDate=" + creadtedDate + ", modifiedDate="
				+ modifiedDate + ", createdBy=" + createdBy + ", isAssertHeader=" + isAssertHeader + ", apiMonitors="
				+ apiMonitors + "]";
	}

		
	
	
	


}
