package com.bhavish.junit.robotapp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.rules.ExpectedException;

import com.bhavish.robotapp.exception.RobotException;
import com.bhavish.robotapp.game.Position;
import com.bhavish.robotapp.game.Robot;
import com.bhavish.robotapp.game.board.Board;
import com.bhavish.robotapp.game.board.SquareBoard;
import com.bhavish.robotapp.util.Constants;
import com.bhavish.robotapp.util.Coordinates;

public class RobotJunit {
  
  Board board;
  Robot bot;
  
  @Before
  public void setUp() throws Exception {
    this.board = new SquareBoard(5, 5);
    this.bot = new Robot(board);
  }
  
  @Rule public ExpectedException expectedExceptionThrown = ExpectedException.none();

  @Test
  public void testIsValidBoard() throws RobotException {
    expectedExceptionThrown.expect(RobotException.class);
    expectedExceptionThrown.expectMessage(Constants.ERR_INVALID_ACTION_BEFORE_PLACECOMMAND);
    bot.isValidBoard();
  }

  @Test
  public void testPlaceBot() throws RobotException {
    bot.placeBot(0, 0, Coordinates.EAST);
    bot.isValidBoard();
    
    //Multiple placing bot
    bot.placeBot(0, 1, Coordinates.SOUTH);
    bot.isValidBoard();
    
  }
  
  @Test
  public void testPlaceBotInvalid() throws RobotException {
    expectedExceptionThrown.expect(RobotException.class);
    expectedExceptionThrown.expectMessage(Constants.ERR_INVALID_POS_WHILE_PLACING_BOT);
    //Position out of board
    bot.placeBot(5, 5, Coordinates.SOUTH);
    //Position-ROW having negative value
    bot.placeBot(-1, 0, Coordinates.SOUTH);
    //Positiom-COLUMN having negative value
    bot.placeBot(0, -1, Coordinates.SOUTH);
  }
  
  @Test
  public void testReport() throws RobotException {
    bot.placeBot(0, 0, Coordinates.NORTH);
    assertEquals(bot.report(),"0,0,NORTH");
  }
  
  @Test 
  public void testReportInvalid() throws RobotException {
    expectedExceptionThrown.expect(RobotException.class);
    expectedExceptionThrown.expectMessage(Constants.ERR_INVALID_ACTION_BEFORE_PLACECOMMAND);
    bot.report();
  }
  
  @Test
  public void testMoveForward() throws RobotException {
    bot.placeBot(0, 0, Coordinates.NORTH);
    assertEquals(bot.moveForward(), Constants.MSG_VALID_FWD_MOVE);
        
  }
  
  @Test
  public void testMoveForwardInvalid() throws RobotException {
   expectedExceptionThrown.expect(RobotException.class);
   expectedExceptionThrown.expectMessage(Constants.ERR_INVALID_ACTION_BEFORE_PLACECOMMAND);
   bot.moveForward();
   
   //Southwest corner
   bot.placeBot(0, 0, Coordinates.SOUTH);
   assertEquals(bot.moveForward(), Constants.MSG_INVALID_FWD_MOVE);
   bot.placeBot(0, 0, Coordinates.WEST);
   assertEquals(bot.moveForward(), Constants.MSG_INVALID_FWD_MOVE);
   
   //NorthWest corner
   bot.placeBot(0, 4, Coordinates.NORTH);
   assertEquals(bot.moveForward(), Constants.MSG_INVALID_FWD_MOVE);
   bot.placeBot(0, 4, Coordinates.WEST);
   assertEquals(bot.moveForward(), Constants.MSG_INVALID_FWD_MOVE);
   
   //NorthEast corner
   bot.placeBot(4, 4, Coordinates.NORTH);
   assertEquals(bot.moveForward(), Constants.MSG_INVALID_FWD_MOVE);
   bot.placeBot(4, 4, Coordinates.EAST);
   assertEquals(bot.moveForward(), Constants.MSG_INVALID_FWD_MOVE);
   
   //SouthEast corner
   bot.placeBot(4, 0, Coordinates.SOUTH);
   assertEquals(bot.moveForward(), Constants.MSG_INVALID_FWD_MOVE);
   bot.placeBot(4, 0, Coordinates.EAST);
   assertEquals(bot.moveForward(), Constants.MSG_INVALID_FWD_MOVE);

  }
  
  @Test
  public void testTurnLeft() throws RobotException {
    bot.placeBot(0, 0, Coordinates.EAST);
    assertEquals(bot.turnLeft(), Constants.MSG_TURN_LEFT);
    assertEquals(Coordinates.NORTH.toString(), bot.report().split(",")[2]);
    assertEquals(bot.turnLeft(), Constants.MSG_TURN_LEFT);
    assertEquals(Coordinates.WEST.toString(), bot.report().split(",")[2]);
    assertEquals(bot.turnLeft(), Constants.MSG_TURN_LEFT);
    assertEquals(Coordinates.SOUTH.toString(), bot.report().split(",")[2]);
    assertEquals(bot.turnLeft(), Constants.MSG_TURN_LEFT);
    assertEquals(Coordinates.EAST.toString(), bot.report().split(",")[2]);
    
  }
  
  @Test
  public void testTurnLeftInValid() throws RobotException {
    expectedExceptionThrown.expect(RobotException.class);
    expectedExceptionThrown.expectMessage(Constants.ERR_INVALID_ACTION_BEFORE_PLACECOMMAND);
    bot.turnLeft();
  }
  
  @Test
  public void testTurnRight() throws RobotException {
    bot.placeBot(0, 0, Coordinates.EAST);
    assertEquals(bot.turnRight(), Constants.MSG_TURN_RIGHT);
    assertEquals(Coordinates.SOUTH.toString(), bot.report().split(",")[2]);
    assertEquals(bot.turnRight(), Constants.MSG_TURN_RIGHT);
    assertEquals(Coordinates.WEST.toString(), bot.report().split(",")[2]);
    assertEquals(bot.turnRight(), Constants.MSG_TURN_RIGHT);
    assertEquals(Coordinates.NORTH.toString(), bot.report().split(",")[2]);
    assertEquals(bot.turnRight(), Constants.MSG_TURN_RIGHT);
    assertEquals(Coordinates.EAST.toString(), bot.report().split(",")[2]);
    
  }
  @Test
  public void testTurnRightInValid() throws RobotException {
    expectedExceptionThrown.expect(RobotException.class);
    expectedExceptionThrown.expectMessage(Constants.ERR_INVALID_ACTION_BEFORE_PLACECOMMAND);
    bot.turnRight();
  }
}
