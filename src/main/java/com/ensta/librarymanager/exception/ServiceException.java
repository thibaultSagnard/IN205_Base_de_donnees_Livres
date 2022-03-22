package com.ensta.librarymanager.exception;

public class ServiceException extends Exception{
	public ServiceException() {}
	public ServiceException(String error) {
		super(error);
	}
}
