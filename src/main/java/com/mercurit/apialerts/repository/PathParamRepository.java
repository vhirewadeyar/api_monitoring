package com.mercurit.apialerts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercurit.apialerts.domain.PathParam;

public interface PathParamRepository extends JpaRepository<PathParam,String> {

}
