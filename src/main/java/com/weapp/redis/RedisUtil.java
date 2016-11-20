package com.weapp.redis;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
/**
 * Jedis增删改查操作
 * @author xiaoqiang
 *
 * @param <T>
 */
@Service
public class RedisUtil {
	@Autowired(required=true)
	private RedisTemplate<String,String> redisTemplate;

	/**
	 * 添加对象
	 */
	public boolean add(final String key, final String value) {
		redisTemplate.execute(new RedisCallback<Object>() {  
			@Override  
			public Object doInRedis(RedisConnection connection)  
					throws DataAccessException {  
				connection.set(  
						redisTemplate.getStringSerializer().serialize(key),  
						redisTemplate.getStringSerializer().serialize(value));
				return true;  
			}  
		});
		return false;  
	}  
	/**
	 * 添加对象
	 */
	public boolean add(final String key, final Long expires, final String value) {
		redisTemplate.execute(new RedisCallback<Object>() {  
			@Override  
			public Object doInRedis(RedisConnection connection)  
					throws DataAccessException {  
				connection.setEx(
						redisTemplate.getStringSerializer().serialize(key), 
						expires, 
						redisTemplate.getStringSerializer().serialize(value)
						);
				return true;  
			}  
		});
		return false;  
	}  
	/**
	 * 添加Map
	 */
	public boolean add(final Map<String,String>map) {
		Assert.notEmpty(map);  
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
			public Boolean doInRedis(RedisConnection connection)  
					throws DataAccessException {  
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
				for (Map.Entry<String, String> entry : map.entrySet()) {  
					byte[] key  = serializer.serialize(entry.getKey());  
					byte[] name = serializer.serialize(entry.getValue());  
					connection.setNX(key, name);  
				}  
				return true;  
			}  
		}, false, true);  
		return result; 
	}  

	/**
	 * 删除对象 ,依赖key
	 */
	public void delete(String key) {  
		redisTemplate.delete(key);
	}  

	/**
	 * 修改对象 
	 */
	public boolean update(final String key,final String value) {
		if (get(key) == null) {  
			throw new NullPointerException("数据行不存在, key = " + key);  
		}  
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
			public Boolean doInRedis(RedisConnection connection)  
					throws DataAccessException {  
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
				connection.set(serializer.serialize(key), serializer.serialize(value));  
				return true;  
			}  
		});  
		return result;   

	}  

	/**
	 * 根据key获取对象
	 */
	public Object get(final String keyId) {
		Object result = redisTemplate.execute(new RedisCallback<Object>() {  
			public Object doInRedis(RedisConnection connection)  
					throws DataAccessException {  
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
				byte[] key = serializer.serialize(keyId);  
				byte[] value = connection.get(key);  
				if (value == null) {  
					return null;  
				}  
				return serializer.deserialize(value);
			}  
		});  
		return result;  
	} 
}
