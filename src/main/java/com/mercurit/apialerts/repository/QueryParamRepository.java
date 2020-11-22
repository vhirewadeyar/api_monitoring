package com.mercurit.apialerts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercurit.apialerts.domain.QueryParam;


public interface QueryParamRepository extends JpaRepository<QueryParam,String> {

}
