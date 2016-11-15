package com.weapp.entity.auth;

import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * app认证实体
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
	/*分发的应用ID*/
	private String appId;
	/*密钥*/	
	private String secretKey;
	/*创建日期*/	
	private Date createDate;
	/*有效截止日期，为2030-01-01 00：00:00表示无日期限制*/	
	private Date validDate;
	/*应用权限等级*/	
	private String appGrade;
	/*是否禁用*/	
	private Boolean disabled;
	/*拥有的api，及调用次数上限*/	
	private Map<String, Map<String,Integer>> apis;

	public AppKey(String appId, String secretKey, Date createDate, Date validDate, String appGrade, Boolean disabled,
			Map<String, Map<String,Integer>> apis) {
		super();
		this.appId = appId;
		this.secretKey = secretKey;
		this.createDate = createDate;
		this.validDate = validDate;
		this.appGrade = appGrade;
		this.disabled = disabled;
		this.apis = apis;
	}
}
