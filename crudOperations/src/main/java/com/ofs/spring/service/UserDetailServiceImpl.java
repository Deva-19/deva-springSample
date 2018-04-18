package com.ofs.spring.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ofs.spring.dao.UserDetailDao;
import com.ofs.spring.exception.userNameAlreadyExsists;
import com.ofs.spring.exception.CustomeException;
import com.ofs.spring.exception.DaoException;
import com.ofs.spring.exception.ErrorResponse;
import com.ofs.spring.exception.ServiceException;
import com.ofs.spring.exception.userNameAlreadyDeletedException;
import com.ofs.spring.model.UserModel;

/**
 * UserAuthenticationServiceImpl class implements all the methods of
 * UserAuthenticationService interface
 * 
 * @author devasena.s
 *
 */
public class UserDetailServiceImpl implements UserDetailService {

	@Autowired
	UserDetailDao userDetaildao;

	/**
	 * getUsers methods to get all the users from data base
	 */
	public List<UserModel> getUsers() {

		return userDetaildao.getUsers();
	}

	/**
	 * createNewUser method to create new user
	 * @throws ServiceException 
	 * @throws DaoException 
	 * 
	 * @throws userNameAlreadyExsits
	 *             exception if the created username is not unique
	 */
	public String createNewUser(UserModel user) throws ServiceException {
		UserModel exsistingUserName = userDetaildao.findUserByUserName(user.getUsername());
		if (exsistingUserName == null) {
			return userDetaildao.createNewUser(user);
		}	
		throw new ServiceException();	
	}

	/**
	 * getUserById method is to obtain the details of particular user
	 */
	public UserModel getUserById(int userId) {

		return userDetaildao.getUserById(userId);
	}

	/**
	 * deleteUserByUserName method is to delete a particular user by passing
	 * username
	 * 
	 * @throws userNameAlreadyDeletedException
	 *             if the username does not match with any one in dB
	 * @throws DaoException 
	 */
	public String deleteUserByUserName(String username) throws ServiceException, DaoException {

		UserModel exsistingUserName = userDetaildao.findUserByUserName(username);
		if (exsistingUserName == null) {
			throw new ServiceException();
		}
			return userDetaildao.deleteUserByUserName(username);
	}

	/**
	 * updateUser method to update the details of particular user
	 */
	public String updateUser(UserModel user) throws ServiceException, DaoException{

		if(user.getEmail()=="" || user.getFullname()=="" || user.getPassword()=="" || user.getUsername()=="") {
			throw new ServiceException();
		} 
		try {
			return userDetaildao.updateUser(user);
		} catch (DaoException de) {
			throw new DaoException();
			}
		}
	
	public UserDetailDao getUserAuthenticationdao() {
		return userDetaildao;
	}

	public void setUserAuthenticationdao(UserDetailDao userAuthenticationdao) {
		this.userDetaildao = userAuthenticationdao;
	}

	public UserModel findUser(String username, String password) {
		
		return userDetaildao.findUser(username,password);
	}

	public String updateAllUser(List<UserModel> user) throws ServiceException, DaoException {
		try {
			return userDetaildao.updateAllUser(user);
		} catch (DaoException de) {
			throw new DaoException();
			}
		}
	

}
