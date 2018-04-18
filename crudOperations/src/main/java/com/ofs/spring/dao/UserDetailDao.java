package com.ofs.spring.dao;

import java.util.List;

import com.ofs.spring.exception.DaoException;
import com.ofs.spring.model.UserModel;

/**
 * UserAuthenticationDao interface contains only method declarations 
 * @author devasena.s
 *
 */
public interface UserDetailDao {

	public List<UserModel> getUsers();

	public String createNewUser(UserModel user);

	public UserModel getUserById(int userId);

	public String deleteUserByUserName(String username) throws DaoException;

	public String updateUser(UserModel user) throws DaoException;

	UserModel findUserByUserName(String username);

	public UserModel findUser(String username, String password);

	public String updateAllUser(List<UserModel> user) throws DaoException;

}
