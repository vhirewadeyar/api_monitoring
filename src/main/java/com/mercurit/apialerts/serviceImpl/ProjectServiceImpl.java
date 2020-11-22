package com.mercurit.apialerts.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercurit.apialerts.domain.Project;
import com.mercurit.apialerts.repository.ProjectRepository;
import com.mercurit.apialerts.service.ProjectService;


@Service
@Transactional
public class ProjectServiceImpl  implements ProjectService{
	
	private ProjectRepository projectRepository;
	
	
	public ProjectServiceImpl(ProjectRepository projectRepository) {
		this.projectRepository=projectRepository;
	}

	@Override
	public Project createProject(Project project) {
		return projectRepository.save(project);
		
	}
	
	@Override
	public Project updateProject(Project project) {
		project.setModifiedDate(new Date());
		return projectRepository.save(project);
		
	}
	
	@Override
	public void deleteProject(long id) {
		projectRepository.deleteProjectById(id);
	}
	
	@Override
	public List<Project> getAllProjects(){
		return projectRepository.findAll();
	}
	
	@Override 
	public List<Project> getProjectByUser(String userName){
		return projectRepository.findProjectByUser(userName);
	}
	
	@Override
	public Project getProjectByUuid(String uuid) {
		return projectRepository.findProjectByUuid(uuid);
		
	}
	
}
