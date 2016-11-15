package com.weapp.entity.auth;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * API接口管理
 * @author xiaoqiang
 *
 */
@Document(collection="t_apis")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiInfo {
	@Id
	private String id;
	/*接口名称*/
	private String name;
	/*接口地址*/
	private String uri;
	/*权限之和
	 * Get = 1
	 * POST = 2 
	 * PUT = 4
	 * DELETE = 8
	 */
	private Integer crud;
	/*每天调用次数上限*/
	private Integer accessLimit;
	/*版本号*/	
	private String version;
	/*是否可用*/
	private boolean disabled;
	/*解密算法*/	
	private String algorithm;
	
	private Date createDate;
	public ApiInfo(String name,String uri, Integer accessLimit, String version, boolean disabled, String algorithm, Date createDate) {
		super();
		this.name = name;
		this.uri = uri;
		this.accessLimit = accessLimit;
		this.version = version;
		this.disabled = disabled;
		this.algorithm = algorithm;
		this.createDate = createDate;
	}
}
