package com.ofs.spring.dao;

import java.util.List;

import javax.ws.rs.core.Response;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ofs.spring.exception.DaoException;
import com.ofs.spring.exception.ErrorResponse;
import com.ofs.spring.exception.ServiceException;
import com.ofs.spring.exception.userNameAlreadyDeletedException;
import com.ofs.spring.model.UserModel;
import com.ofs.spring.util.HibernateUtil;

/**
 * UserAuthenticationDaoImpl class enables a connection with dB through
 * hibernate query
 * 
 * @author devasena.s
 *
 */

public class UserDetailDaoImpl implements UserDetailDao {
	

	@Autowired
	HibernateUtil hibernateUtil;

	/**
	 * getUsers method returns a userData object which contains the list of
	 * users in dB
	 */
	public List<UserModel> getUsers() {
		hibernateUtil.openCurrentSession();
		List<UserModel> userData = (List<UserModel>) hibernateUtil.getCurrentSession().createQuery("from UserModel")
				.list();
		hibernateUtil.closeCurrentSession();
		return userData;
	}

	/**
	 * createNewUser method save the user object obtained from UI
	 */
	public String createNewUser(UserModel user) {
		
			hibernateUtil.openCurrentSessionwithTransaction();
			hibernateUtil.getCurrentSession().save(user);
			hibernateUtil.closeCurrentSessionwithTransaction();
			return "new user created " + user.getFullname();
		
	}

	/**
	 * getaUserById method returns details of particular user based on userId
	 */
	public UserModel getUserById(int userId) {
		hibernateUtil.openCurrentSession();
		UserModel userData = (UserModel) hibernateUtil.getCurrentSession()
				.createQuery("from UserModel where userId=" + userId).uniqueResult();
		hibernateUtil.closeCurrentSession();
		return userData;
	}

	/**
	 * deleteUserByUserName method deletes a particular user based on username
	 * @throws DaoException 
	 */
	public String deleteUserByUserName(String username) throws DaoException {

		try {
			hibernateUtil.openCurrentSessionwithTransaction();
			hibernateUtil.getCurrentSession().createQuery("delete from UserModel where username='" + username + "'")
					.executeUpdate();
			hibernateUtil.closeCurrentSessionwithTransaction();
			return "Deleted user: " + username;
		} catch (HibernateException e) {
			throw new DaoException();
		}

	}

	/**
	 * updateUser updates a particular userDetails as user object
	 */
	public String updateUser(UserModel user) throws DaoException {

		try {
			hibernateUtil.openCurrentSessionwithTransaction();
			hibernateUtil.getCurrentSession().update(user);
			hibernateUtil.closeCurrentSessionwithTransaction();
			return " New User updated Successfully!!";
		} catch (HibernateException e) {
			throw new DaoException();
		}
	}

	/**
	 * findUserByUserName method is to check whether the username exists or not
	 */
	public UserModel findUserByUserName(String username) {
		hibernateUtil.openCurrentSession();
		UserModel user = (UserModel) hibernateUtil.getCurrentSession()
				.createQuery("from UserModel where username='" + username + "'").uniqueResult();
		hibernateUtil.closeCurrentSession();
		return user;
	}

	public UserModel findUser(String username, String password) {
		hibernateUtil.openCurrentSessionwithTransaction();
		UserModel user = (UserModel) hibernateUtil.getCurrentSession()
				.createQuery("from UserModel where username='" + username + "' and password='"+password+"'").uniqueResult();
		hibernateUtil.closeCurrentSessionwithTransaction();
		return user;
	}

	public String updateAllUser(List<UserModel> user) throws DaoException {
		try {
			hibernateUtil.openCurrentSessionwithTransaction();
			 for(UserModel usersDetail : user) {
				 hibernateUtil.getCurrentSession().saveOrUpdate(usersDetail);
			    }
			hibernateUtil.closeCurrentSessionwithTransaction();
			return " User updated Successfully!!";
		} catch (HibernateException e) {
			throw new DaoException();
		}
	} 

}
