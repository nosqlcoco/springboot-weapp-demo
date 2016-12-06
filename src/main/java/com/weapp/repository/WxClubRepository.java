package com.weapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.weapp.common.util.MongoPageable;
import com.weapp.entity.wxclub.Article;

public interface WxClubRepository extends MongoRepository<Article, String> {

	List<Article> findByGroupPath(String groupPath);

	List<Article> findByTitleLike(String title, MongoPageable page);
	
}
