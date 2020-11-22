package com.mercurit.apialerts.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercurit.apialerts.domain.ApiMonitors;
import com.mercurit.apialerts.domain.Project;
import com.mercurit.apialerts.errors.NullExceptionError;
import com.mercurit.apialerts.repository.ApiMonitorsRepository;
import com.mercurit.apialerts.service.ApiMonitorsService;
import com.mercurit.apialerts.service.ProjectService;

@Service
@Transactional
public class ApiMonitorsServiceImpl implements ApiMonitorsService{

	
	private ApiMonitorsRepository apiMonitorsRepository;
	private ProjectService projectService;
	
	public ApiMonitorsServiceImpl(ApiMonitorsRepository apiMonitorsRepository,ProjectService projectService) {
		this.apiMonitorsRepository=apiMonitorsRepository;
		this.projectService=projectService;
	}
	
	@Override
	public ApiMonitors save(ApiMonitors apiMonitors,String projectId) throws NullExceptionError {
		Project project=projectService.getProjectByUuid(projectId);
		if(project==null) {
			throw new NullExceptionError("Project Not Found");
		}
		apiMonitors.setProject(project);
		return apiMonitorsRepository.save(apiMonitors);
	}
	
	@Override
	public List<ApiMonitors> findAllMonitors(){
		return apiMonitorsRepository.findAll();
	}
}
