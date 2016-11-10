package com.weapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.weapp.entity.auth.AppKey;

public interface AppKeyRepository extends MongoRepository<AppKey, String> {
	
}
