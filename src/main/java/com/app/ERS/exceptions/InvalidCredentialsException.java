package com.app.ERS.exceptions;

public class InvalidCredentialsException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public InvalidCredentialsException() {
    super("The username or password was incorrect!");
  }

}
