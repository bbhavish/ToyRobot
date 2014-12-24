package com.bhavish.robotapp.exception;

public class RobotException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public RobotException() {
    super();
  }

  public RobotException(String message) {
    super(message);
  }
  
  public RobotException(String message,Throwable throwable) {
    super(message,throwable);
  }
  
}
