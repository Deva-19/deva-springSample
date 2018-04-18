/*package com.ofs.spring.test.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.when;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ofs.spring.dao.UserDetailDao;
import com.ofs.spring.exception.ServiceException;
import com.ofs.spring.model.UserModel;
import com.ofs.spring.service.UserDetailServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserAuthenticationServiceTest {

	@Mock
	private UserDetailDao userAuthenticationDao;

	@Autowired
	private UserDetailServiceImpl userAuthenticationServiceImpl;

	private String actualValue;
	private UserModel user;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		//userAuthenticationDao = Mockito.mock(UserDetailDao.class);
		when(userAuthenticationDao.createNewUser(user)).thenReturn("created");
		userAuthenticationServiceImpl.setUserAuthenticationdao(userAuthenticationDao);
	}

	@Test
	public void userAuthenticationtestClass() throws ServiceException {
		actualValue = userAuthenticationServiceImpl.createNewUser(user);
		Assert.assertEquals("created", actualValue);
	}

}
*/