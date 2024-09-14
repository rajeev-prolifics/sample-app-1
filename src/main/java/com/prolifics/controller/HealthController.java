package com.prolifics.controller;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/health")
@Slf4j
public class HealthController {
	private static final String INITIALIZED_AT = "initializedAt";
	private static final String STATUS = "status";
	private Map<String, Object> dataMap;
	
	@GetMapping
	public Map<String, Object> getContext(){
		if(dataMap == null || dataMap.isEmpty()) {
			log.info("Initializing the dataMap....");
			dataMap = new LinkedHashMap<>();
			dataMap.putAll(System.getenv());
			dataMap.put(INITIALIZED_AT, LocalDateTime.now());
			dataMap.put(STATUS, "New Container");
			dataMap.put("identifier",System.currentTimeMillis());
			log.info("Initialized Successfully at " + dataMap.get(INITIALIZED_AT));
		}else {
			log.info("Using Cached version of dataMap.This was initialized at " + dataMap.get(INITIALIZED_AT));
			dataMap.put(STATUS, "Existing Container");
		}
		dataMap.put("host", System.getProperty("user.name"));
		return dataMap;
	}
}
