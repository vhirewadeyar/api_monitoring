package com.mercurit.apialerts.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercurit.apialerts.domain.Project;
import com.mercurit.apialerts.errors.CustomExceptionError;
import com.mercurit.apialerts.security.SecurityUtil;
import com.mercurit.apialerts.service.ProjectService;

@RestController
@RequestMapping("/apialerts")
public class ProjectController {
	
	private ProjectService projectService;
	
	public ProjectController(ProjectService projectService) {
		this.projectService=projectService;
	}
	
	@PostMapping("/project")
	public ResponseEntity<Project> createProject(@RequestBody Project project){
		
		
		String userName=SecurityUtil.getCurrentUserLogin().get();
		project.setUserName(userName);
		project.setCreatedBy(userName);
		project.setModifiedBy(userName);
		Project result=projectService.createProject(project);
		result.setSuccessMessage("Project created");
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/project")
	public ResponseEntity<List<Project>> getAllProject(){
		return ResponseEntity.ok(projectService.getAllProjects());
	}

	@GetMapping("/project/{username}")
	public ResponseEntity<List<Project>> getProjectByUser(@PathVariable String username) throws CustomExceptionError{
		if(username.isEmpty()) {
			throw new CustomExceptionError("User name not found");
		}
		return ResponseEntity.ok(projectService.getProjectByUser(username));
	}
	
	@DeleteMapping("/project/{id}")
	public String deleteProject(@PathVariable long id){
		projectService.deleteProject(id);
		return "project deleted";
	}
}
