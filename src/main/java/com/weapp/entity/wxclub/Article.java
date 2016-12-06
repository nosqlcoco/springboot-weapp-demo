package com.weapp.entity.wxclub;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="t_articles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
	@Id
	private String id;
	
	private String path;
	
	private String groupPath;
	
	private String title;
	
	private String digest;
	
	private String createTime;
	
	private Integer browers;
	
	private Integer comments;
	
	private Map<String,String> author;
	
	private String mark;
	
	private String content;
}
