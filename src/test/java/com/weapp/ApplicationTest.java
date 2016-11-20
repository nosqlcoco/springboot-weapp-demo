package com.weapp;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.weapp.controller.AppUserController;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MockServletContext.class)
@WebAppConfiguration
@TestPropertySource("classpath:application-test.properties")
public class ApplicationTest {
	private MockMvc mvc;
	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(new AppUserController()).build();
		MockitoAnnotations.initMocks(this);
	}
	//测试获取数据接口
	@Test
	public void test() throws Exception{
		RequestBuilder request = null;
		request = MockMvcRequestBuilders.get("/api/v1/user/123123")
				.param("appId", "test123").param("apiName", "GET_USER");
				
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("{\"id\":\"123123\"}")));
	}
}
