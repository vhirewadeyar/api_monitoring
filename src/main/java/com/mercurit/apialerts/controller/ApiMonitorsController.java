package com.mercurit.apialerts.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mercurit.apialerts.domain.ApiMonitors;
import com.mercurit.apialerts.errors.NullExceptionError;
import com.mercurit.apialerts.security.SecurityUtil;
import com.mercurit.apialerts.service.ApiMonitorsService;

@RestController
@RequestMapping("/apialerts")
public class ApiMonitorsController {
	
	private ApiMonitorsService apiMonitorsService;
	
	public ApiMonitorsController(ApiMonitorsService apiMonitorsService) {
		this.apiMonitorsService=apiMonitorsService;
	}
	
	@PostMapping("/apimonitors")
	public ResponseEntity<ApiMonitors> createApiMonitors(@RequestBody ApiMonitors apiMonitors,@RequestParam(value="projectId")String projectId) throws NullExceptionError{
		apiMonitors.setCreatedBy(SecurityUtil.getCurrentUserLogin().get().toString());
		return ResponseEntity.ok(apiMonitorsService.save(apiMonitors,projectId));
		
		
	}
	@GetMapping("/apimonitors")
	public ResponseEntity<List<ApiMonitors>> findAllMonitors(){
		return ResponseEntity.ok(apiMonitorsService.findAllMonitors());
	}

}
