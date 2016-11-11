package com.weapp.entity.auth;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * API访问日志
 * @author xiaoqiang
 *
 */
@Document(collection="t_access_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessLog {
	@Id
	private String id;
	/*api名称*/	
	private String apiName;
	/*访问时间*/	
	private Date accessDate;
	/*请求参数*/	
	private String reqParam;
	/*返回参数*/	
	private String resParam;
	/*异常内容*/	
	private String exp;

	public AccessLog(String apiName, Date accessDate, String reqParam, String resParam, String exp) {
		super();
		this.apiName = apiName;
		this.accessDate = accessDate;
		this.reqParam = reqParam;
		this.resParam = resParam;
		this.exp = exp;
	}
	
}
