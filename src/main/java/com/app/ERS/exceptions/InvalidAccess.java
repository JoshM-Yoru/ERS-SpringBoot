package com.app.ERS.exceptions;

public class InvalidAccess extends RuntimeException {

  private static final long serialVersionUid = 1L;

  public InvalidAccess() {
    super("This user does not have managerial access!");
  }

}
