package com.bhavish.robotapp.util;

public interface Constants {

  public static final String ERR_INVALID_COMMAND = "Invalid command";
  public static final String ERR_INVALID_PLACE_COMMAND = "Invalid PLACE command. Format - PLACE X,Y,NORTH|SOUTH|EAST|WEST";
  public static final String ERR_INVALID_ACTION_BEFORE_PLACECOMMAND ="Invalid Action. Please PLACE before doing any operation";
  public static final String ERR_BOARD_NOT_INITIALIZED = "Board is not initialized";
  public static final String ERR_BOT_POSTION_NOT_INITIALIZED = "Bot Position is not initialized";
  public static final String ERR_DIRECTON_NOT_SET = "Direction not set!!";
  public static final String ERR_INVALID_POS_WHILE_PLACING_BOT = "Invalid position while placing the Robot. Please try again";
  
  
  public static final String MSG_TURN_LEFT = "Turned Left";
  public static final String MSG_TURN_RIGHT = "Turned Right";
  public static final String MSG_VALID_FWD_MOVE = "Valid Forward Move";
  public static final String MSG_INVALID_FWD_MOVE = "Invalid Forward Move.Turn LEFT or RIGHT.";
  
  public static final int NORTH_XCOORDINATE = 0;
  public static final int NORTH_YCOORDINATE = 1;
  public static final int SOUTH_XCOORDINATE = 0;
  public static final int SOUTH_YCOORDINATE = -1;
  public static final int EAST_XCOORDINATE = 1;
  public static final int EAST_YCOORDINATE = 0;
  public static final int WEST_XCOORDINATE = -1;
  public static final int WEST_YCOORDINATE = 0;
  
}
