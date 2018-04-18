package com.ofs.spring.exception;

public class userNameAlreadyDeletedException extends Exception {

	int statusCode;
	String errormsg;
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = 404;
	}
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = "User Name Does not exsits or already deleted";
	}
	

}
