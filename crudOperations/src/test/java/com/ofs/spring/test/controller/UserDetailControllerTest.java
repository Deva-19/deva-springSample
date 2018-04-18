//package com.ofs.spring.test.controller;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultMatcher;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import static org.mockito.Mockito.when;
//
//import java.awt.PageAttributes.MediaType;
//import java.util.List;
//
//import com.ofs.spring.controller.UserDetailController;
//import com.ofs.spring.exception.ServiceException;
//import com.ofs.spring.model.UserModel;
//import com.ofs.spring.service.UserDetailService;
//
//import junit.framework.Assert;
//
////@RunWith(SpringJUnit4ClassRunner.class)
////@ContextConfiguration("classpath:applicationContext.xml")
//public class UserDetailControllerTest {
//
//	@Mock
//	UserDetailService userDetailService;
//
//	//@Autowired
//	@InjectMocks
//	UserDetailController userDetailController;
//
//	private MockMvc mockMvc;
//
//	private UserModel user;
//
//	private String value = "created";
//
//	//private List value;
//	
//	@Before
//	public void setUp() throws ServiceException {
//		MockitoAnnotations.initMocks(this);
//		when(userDetailService.createNewUser(user)).thenReturn(value);
//		mockMvc = MockMvcBuilders.standaloneSetup(userDetailController).build();
//        
//	}
//	
//	@Test
//	public void userDetailControllerTest() throws Exception {
//		//MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/user/createNewUser");
//		//Assert.assertEquals(value, builder);
//		//MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/user/getUsers");
//		//mockMvc.perform(builder).andExpect((ResultMatcher) MockMvcResultMatchers.status().isOk());
//		mockMvc.perform(get("/user/getUsers")).andReturn().equals("created");
//	}
//}
