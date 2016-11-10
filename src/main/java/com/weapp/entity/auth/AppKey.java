package com.weapp.entity.auth;

import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * api认证实体
 * @author xiaoqiang
 *
 */
@Document(collection="t_appkeys")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppKey {
	@Id
	private String id;
	
	private String appId;
	
	private String secretKey;
	
	private Date createDate;
	
	private Date validDate;
	
	private Boolean disabled;
	
	private Map<String,Integer> api;

	public AppKey(String appId, String secretKey, Date createDate, Date validDate, Boolean disabled,
			Map<String, Integer> api) {
		super();
		this.appId = appId;
		this.secretKey = secretKey;
		this.createDate = createDate;
		this.validDate = validDate;
		this.disabled = disabled;
		this.api = api;
	}
	
	
}
