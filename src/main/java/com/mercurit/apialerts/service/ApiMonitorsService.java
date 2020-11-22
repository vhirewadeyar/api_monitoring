package com.mercurit.apialerts.service;

import java.util.List;

import com.mercurit.apialerts.domain.ApiMonitors;
import com.mercurit.apialerts.errors.NullExceptionError;

public interface ApiMonitorsService {

	public ApiMonitors save(ApiMonitors apiMonitor, String projectId) throws NullExceptionError;
	
	public List<ApiMonitors> findAllMonitors();
	
}

