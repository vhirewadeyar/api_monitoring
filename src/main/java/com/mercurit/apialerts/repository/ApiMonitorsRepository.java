package com.mercurit.apialerts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercurit.apialerts.domain.ApiMonitors;


public interface ApiMonitorsRepository extends JpaRepository<ApiMonitors,String> {

}
