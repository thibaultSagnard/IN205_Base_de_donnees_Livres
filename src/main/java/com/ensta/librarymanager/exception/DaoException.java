package com.ensta.librarymanager.exception;

public class DaoException extends Exception {
	public DaoException() {}
	public DaoException(String error) {
		super(error);
	}
}
	