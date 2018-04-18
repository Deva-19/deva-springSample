package com.ofs.spring.controller;

import java.util.List;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ofs.spring.exception.CustomeException;
import com.ofs.spring.exception.DaoException;
import com.ofs.spring.exception.ErrorResponse;
import com.ofs.spring.exception.ServiceException;
import com.ofs.spring.exception.userNameAlreadyDeletedException;
import com.ofs.spring.exception.userNameAlreadyExsists;
import com.ofs.spring.model.UserModel;
import com.ofs.spring.service.UserDetailService;

@CrossOrigin
/**
 * RestController is the combination of @Controller and @ResponseBody
 */
@RestController
@RequestMapping("/user")

/**
 * userAuthenticationController class contains rest api's to perform CRUD
 * operation
 * 
 * @author devasena.s
 *
 */
public class UserDetailController {

	/**
	 * UserAuthenticationService is autowired so that we can access the methods
	 * in that class
	 */
	@Autowired
	UserDetailService userDetailService;

	@Autowired
	ServiceException serviceException;

	@Autowired
	DaoException daoException;

	/**
	 * getUsers call to get all the users list
	 * 
	 * @return userData object
	 */

	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public ResponseEntity getUsers() {
		List<UserModel> users;
		try {
			users = userDetailService.getUsers();
			Object[] userData = users.toArray();
			return ResponseEntity.ok(userData);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}

	/**
	 * getUserById gives information about a particular user based on ID(primary
	 * key)
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/getUserById/{userId}", method = RequestMethod.GET)
	public ResponseEntity<UserModel> getUserById(@PathVariable("userId") int userId) {
		UserModel particularUser;
		try {
			particularUser = userDetailService.getUserById(userId);
			return ResponseEntity.ok(particularUser);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}

	/**
	 * createNewUser POST call creates new user with unique username
	 * 
	 * @param user
	 * @return
	 * @throws JSONException 
	 * @throws DaoException 
	 * @throws ServiceException 
	 * @throws userNameAlreadyExsists
	 *             exception if the created user name is not unique
	 */
	@RequestMapping(value = "/createNewUser", method = RequestMethod.POST)
	public ResponseEntity<ServiceException> createNewUser(@RequestBody UserModel user)throws ServiceException {
		String updateUser;
		
		try {	
			updateUser = userDetailService.createNewUser(user);
			return ResponseEntity.status(200).build();
		
		} catch (ServiceException e) {
			serviceException.setStatusCode(409);
			return ResponseEntity.status(409).body(serviceException);
		}
	}
	
/*	@RequestMapping(value = "/createNewUser", method = RequestMethod.POST)
	public String createNewUser(@RequestBody UserModel user) throws JSONException, ServiceException {
		String updateUser;
		String response = "";
		JSONObject jsonObject = new JSONObject();
		try {
			updateUser = userDetailService.createNewUser(user);
			jsonObject.put("Status", 200);
			response = jsonObject.toString();
	} catch(JSONException e) {
		jsonObject.put("Status", 400);
		response = jsonObject.toString();
		}
		return response;
	}*/

	/**
	 * deleteUserByUserName deletes a particular user based on username
	 * 
	 * @param username
	 * @return
	 * @throws DaoException
	 * @throws ServiceException 
	 * @throws userNameAlreadyDeletedException
	 *             if username does not exists or already deleted
	 * @throws JSONException
	 */

	@RequestMapping(value = "/deleteUserByUserName/{username}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUserByUserName(@PathVariable("username") String username) throws DaoException, ServiceException {
		String deletUser;
		
		try {
			deletUser = userDetailService.deleteUserByUserName(username);
			return ResponseEntity.status(200).build();
		} catch (ServiceException e) {
			serviceException.setStatusCode(409);
			serviceException.setErrormsg("User Name Does not exsits");
			return ResponseEntity.status(409).body(serviceException);
		} catch (DaoException de) {
			daoException.setErrormsg("connection failed");
			return ResponseEntity.status(500).body(daoException);
		}
	}

	/**
	 * updateUser updates the details of particular user
	 * 
	 * @param user
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@RequestBody UserModel user) throws ServiceException, DaoException {
		String updateUser;
		try {
			updateUser = userDetailService.updateUser(user);
			return ResponseEntity.status(200).build();
		} catch (ServiceException ex) {
			serviceException.setStatusCode(422);
			serviceException.setErrormsg("Provide Proper Credentials");
			return ResponseEntity.unprocessableEntity().body(serviceException);
		} catch (DaoException e) {
			daoException.setErrormsg("connection failed");
			return ResponseEntity.status(500).body(daoException);
		}
	}

	/**
	 * to find the exsisting user
	 * 
	 * @param user
	 * @return
	 * @throws JSONException
	 */

	@RequestMapping(value = "/findUser", method = RequestMethod.POST)
	public String findUser(@RequestBody UserModel user) throws JSONException {
		String username = user.getUsername();
		String password = user.getPassword();
		UserModel exsistingUser = userDetailService.findUser(username, password);
		String response = "";
		JSONObject jsonObject = new JSONObject();
		if (username.equals(exsistingUser.getUsername()) && password.equals(exsistingUser.getPassword())) {
			jsonObject.put("Status", "success");
			jsonObject.put("fullname", exsistingUser.getFullname());
			jsonObject.put("email", exsistingUser.getEmail());
			jsonObject.put("username", exsistingUser.getUsername());
			jsonObject.put("userId", exsistingUser.getUserId());
			response = jsonObject.toString();

		} else {

			jsonObject.put("status", "fail");
			response = jsonObject.toString();
		}
		return response;
	}
	
	@RequestMapping(value = "/updateAllUser", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@RequestBody List<UserModel> user) throws ServiceException, DaoException {
		String updateUser;
		try {
			updateUser = userDetailService.updateAllUser(user);
			return ResponseEntity.status(200).build();
		} catch (ServiceException ex) {
			serviceException.setStatusCode(422);
			serviceException.setErrormsg("Provide Proper Credentials");
			return ResponseEntity.unprocessableEntity().body(serviceException);
		} catch (DaoException e) {
			daoException.setErrormsg("connection failed");
			return ResponseEntity.status(500).body(daoException);
		}
	}

}
