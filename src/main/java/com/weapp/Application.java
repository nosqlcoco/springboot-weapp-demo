package com.weapp;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.weapp.entity.auth.AppKey;
import com.weapp.repository.AppKeyRepository;

/**
 *
 * @author Shanqiang Ke
 * @version 1.0.0
 * @blog http://nosqlcoco.cnblogs.com
 * @since 2016-10-15
 */
@SpringBootApplication(scanBasePackages={"com.weapp"})
public class Application implements CommandLineRunner{
	@Autowired
	private AppKeyRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Map<String,Integer>map = new HashMap<String,Integer>();
		map.put("url", 2222);
		repository.save(new AppKey("sddd", "sdsd", new Date(), new Date(), false, map));
	}
}