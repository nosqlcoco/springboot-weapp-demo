package com.weapp.entity.app;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * App用户实体类
 * @author xiaoqiang
 *
 */
@Document(collection="t_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TUser {
	@Id
	private String id;
	private String appId;
	private String password;
	private String realName;
	private String nickName;

	private String gender;
	private String avatar;
	private String signature;
	
	private String wxOpenId;
	private String wxUnionId;
	
	private String birthday;
	private String address;
	
	private String createDate;
	private String updateDate;
	private String lastLoginDate;
	private Boolean disabled;
	
	private String phone;
	private Boolean isPhoneActive;
	private String email;
	private Boolean isEmailActive;
	
}
