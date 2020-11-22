 package com.mercurit.apialerts.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
@Entity
@Table(name = "apiMonitors")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ApiMonitors implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String monitorsUuid=UUID.randomUUID().toString().replace("-", "");

	@Column(name="monitor_name")
	private String monitorName;

	@Column(name="context_path")
	private String contextPath;
	
	@Column(name="protocol")
	private String protocol;
	
	@Column(name="endpoint")
	private String endpoint;
	
	@Column(name="port")
	private String port="0";
	
	@Column(name="method")
	private String method;
	
	@Column(name="body")
	private String body;
	
	@OneToMany(mappedBy="apiMonitors",fetch= FetchType.LAZY,cascade=CascadeType.ALL)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Headers> header=new ArrayList<>();
	
	@OneToMany(mappedBy="apiMonitors",fetch= FetchType.LAZY,cascade=CascadeType.ALL)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<PathParam> pathParam=new ArrayList<>();
	
	@OneToMany(mappedBy="apiMonitors",fetch= FetchType.LAZY,cascade=CascadeType.ALL)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<QueryParam> queryParam=new ArrayList<>();
	
	@ManyToOne(targetEntity= Project.class)
	@JsonIgnore
	private Project project;

	@Column(name="created_date")
	private Date createdDate=new Date();
	
	@Column(name="modified_date")
	private Date modifiedDate;
	
	@Column(name="is_Active")
	private boolean isActive=true;
	
	@Column(name="created_by")
	private String createdBy;
	
	

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMonitorsUuid() {
		return monitorsUuid;
	}

	public void setMonitorsUuid(String monitorsUuid) {
		this.monitorsUuid = monitorsUuid;
	}

	public String getMonitorName() {
		return monitorName;
	}

	public void setMonitorName(String monitorName) {
		this.monitorName = monitorName;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public List<Headers> getHeader() {
		return header;
	}

	public void setHeader(List<Headers> header) {
		this.header = header;
	}

	public List<PathParam> getPathParam() {
		return pathParam;
	}

	public void setPathParam(List<PathParam> pathParam) {
		this.pathParam = pathParam;
	}

	public List<QueryParam> getQueryParam() {
		return queryParam;
	}

	public void setQueryParam(List<QueryParam> queryParam) {
		this.queryParam = queryParam;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	
	@Override
	public String toString() {
		return "ApiMonitors [id=" + id + ", monitorsUuid=" + monitorsUuid + ", monitorName=" + monitorName
				+ ", contextPath=" + contextPath + ", protocol=" + protocol + ", endpoint=" + endpoint + ", port="
				+ port + ", method=" + method + ", body=" + body + ", header=" + header + ", pathParam=" + pathParam
				+ ", queryParam=" + queryParam + ", project=" + project + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", isActive=" + isActive + ", createdBy=" + createdBy + "]";
	}
	
	
}
