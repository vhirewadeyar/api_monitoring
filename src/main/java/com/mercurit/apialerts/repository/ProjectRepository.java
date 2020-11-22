package com.mercurit.apialerts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mercurit.apialerts.domain.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project,String> {

	@Modifying
	@Query("update Project p Set p.projectStatus=0 where p.id=:id")
	public void deleteProjectById(@Param("id")Long id);
	
	@Query("Select p from Project p where p.projectStatus=1 and p.userName=:user")
	public List<Project> findProjectByUser(@Param("user")String user);
	
	@Query("Select p from Project p where p.projectUuid=:uuid and p.projectStatus=1")
	public Project findProjectByUuid(@Param("uuid")String uuid);
	
}
