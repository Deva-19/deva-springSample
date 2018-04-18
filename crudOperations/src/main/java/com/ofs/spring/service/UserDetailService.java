package com.ofs.spring.service;

import java.util.List;

import com.ofs.spring.exception.userNameAlreadyExsists;
import com.ofs.spring.exception.DaoException;
import com.ofs.spring.exception.ServiceException;
import com.ofs.spring.exception.userNameAlreadyDeletedException;
import com.ofs.spring.model.UserModel;

/**
 * UserAuthenticationService interface contains only method declarations
 * 
 * @author devasena.s
 *
 */
public interface UserDetailService {

	public List<UserModel> getUsers();

	public String createNewUser(UserModel user) throws ServiceException;

	public UserModel getUserById(int userId);

	public String deleteUserByUserName(String username) throws DaoException, ServiceException;

	public String updateUser(UserModel user) throws ServiceException, DaoException;

	public UserModel findUser(String username, String password);

	public String updateAllUser(List<UserModel> user) throws ServiceException, DaoException;

}
