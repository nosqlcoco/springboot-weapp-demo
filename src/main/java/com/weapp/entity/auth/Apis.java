package com.weapp.entity.auth;

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
public class Apis {
	@Id
	private String id;
	/*接口地址*/
	private String uri;
	/*每天调用次数上限*/
	private Integer limit;
	public Apis(String uri, Integer limit) {
		super();
		this.uri = uri;
		this.limit = limit;
	}
}
