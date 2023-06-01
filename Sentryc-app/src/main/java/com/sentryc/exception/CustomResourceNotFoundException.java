package com.sentryc.exception;

public class CustomResourceNotFoundException extends RuntimeException {

	  private static final long serialVersionUID = 1L;

	  public CustomResourceNotFoundException(String msg) {
	    super(msg);
	  }
	}