package com.gmail.ivanjermakov1.projecttracker.core.exception;

public class ApiException extends Exception {

	public ApiException() {
	}

	public ApiException(String s) {
		super(s);
	}

	public ApiException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public ApiException(Throwable throwable) {
		super(throwable);
	}

	public ApiException(String s, Throwable throwable, boolean b, boolean b1) {
		super(s, throwable, b, b1);
	}

}
