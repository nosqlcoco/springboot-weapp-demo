package com.weapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weapp.entity.auth.AccessLog;
import com.weapp.repository.AccessLogRepository;

@Service
@Transactional(readOnly=true)
public class AccessLogService {
	
	@Autowired
	private AccessLogRepository accessLogRepository;
	
	@Transactional(readOnly=false)
	public void save(AccessLog accessLog) {
		accessLogRepository.save(accessLog);
	}
}
