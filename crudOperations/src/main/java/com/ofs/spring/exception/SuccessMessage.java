package com.ofs.spring.exception;

public class SuccessMessage extends Exception {
	
	String SuccessMessage;

	public String getSuccessMessage() {
		return SuccessMessage;
	}

	public void setSuccessMessage(String successMessage) {
		SuccessMessage = "New User Added";
	}
}
