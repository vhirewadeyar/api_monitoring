package com.mercurit.apialerts.service;

import java.util.List;

import com.mercurit.apialerts.domain.Project;

public interface ProjectService {
	
	
	Project createProject(Project project);
	
	Project updateProject(Project project);
	
	List<Project> getAllProjects();
	
	void deleteProject(long id);
	
	List<Project> getProjectByUser(String userName);

	Project getProjectByUuid(String uuid);

}
