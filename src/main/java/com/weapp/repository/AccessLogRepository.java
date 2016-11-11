package com.weapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.weapp.entity.auth.AccessLog;
/**
 * 访问日志操作
 * @author xiaoqiang
 *
 */
public interface AccessLogRepository extends MongoRepository<AccessLog, String> {

}
