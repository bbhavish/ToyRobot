package com.bhavish.junit.robotapp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.bhavish.robotapp.game.BotGame;
import com.bhavish.robotapp.game.board.Board;
import com.bhavish.robotapp.game.board.SquareBoard;
import com.bhavish.robotapp.util.Commands;
import com.bhavish.robotapp.util.Constants;
import com.bhavish.robotapp.util.Coordinates;

public class BotGameJunit {

  Board squareBoard;
  BotGame game;
  @Before
  public void setUp() throws Exception {
    squareBoard = new SquareBoard(5, 5);
    game = new BotGame(squareBoard);
  }

  @Test
  public void validateGame() {
    //verify dummy commands.
    assertEquals(game.executeCommand(""), Constants.ERR_INVALID_COMMAND);
    assertEquals(game.executeCommand("TEST"), Constants.ERR_INVALID_COMMAND);
    
  }
  @Test
  public void validatePlaceCommand() {
    assertEquals(game.executeCommand(Commands.PLACE.toString()), Constants.ERR_INVALID_PLACE_COMMAND);
    assertEquals(game.executeCommand(Commands.PLACE.toString()+",,"), Constants.ERR_INVALID_COMMAND);
    
    assertEquals(game.executeCommand(Commands.PLACE.toString()+" ,,"), Constants.ERR_INVALID_PLACE_COMMAND);
    assertEquals(game.executeCommand(Commands.PLACE.toString()+" x,y,test"), Constants.ERR_INVALID_PLACE_COMMAND);
    assertEquals(game.executeCommand(Commands.PLACE.toString()+" 0,1,REPORT"), Constants.ERR_INVALID_PLACE_COMMAND);
    assertEquals(game.executeCommand(Commands.PLACE.toString()+" ,,"), Constants.ERR_INVALID_PLACE_COMMAND);
    
    assertEquals(game.executeCommand(Commands.MOVE.toString()), Constants.ERR_INVALID_ACTION_BEFORE_PLACECOMMAND);
    assertEquals(game.executeCommand(Commands.LEFT.toString()), Constants.ERR_INVALID_ACTION_BEFORE_PLACECOMMAND);
    assertEquals(game.executeCommand(Commands.RIGHT.toString()), Constants.ERR_INVALID_ACTION_BEFORE_PLACECOMMAND);
    assertEquals(game.executeCommand(Commands.REPORT.toString()), Constants.ERR_INVALID_ACTION_BEFORE_PLACECOMMAND);
    
    assertEquals(game.executeCommand(Commands.PLACE.toString()+" 0,0,"+Coordinates.NORTH.toString()), "Bot placed at 0,0 facing "+Coordinates.NORTH.toString());
    assertEquals(game.executeCommand(Commands.PLACE.toString()+" 0,0,"+Coordinates.SOUTH.toString()), "Bot placed at 0,0 facing "+Coordinates.SOUTH.toString());
    assertEquals(game.executeCommand(Commands.PLACE.toString()+" 0,0,"+Coordinates.EAST.toString()), "Bot placed at 0,0 facing "+Coordinates.EAST.toString());
    assertEquals(game.executeCommand(Commands.PLACE.toString()+" 0,0,"+Coordinates.WEST.toString()), "Bot placed at 0,0 facing "+Coordinates.WEST.toString());
  }

  @Test
  public void testGame() {
    assertEquals(game.executeCommand(Commands.PLACE.toString()+" 0,0,"+Coordinates.NORTH.toString()), "Bot placed at 0,0 facing "+Coordinates.NORTH.toString());
    assertEquals(game.executeCommand(Commands.MOVE.toString()), Constants.MSG_VALID_FWD_MOVE);
    assertEquals(game.executeCommand(Commands.LEFT.toString()), Constants.MSG_TURN_LEFT);
    assertEquals(game.executeCommand(Commands.LEFT.toString()), Constants.MSG_TURN_LEFT);
    assertEquals(game.executeCommand(Commands.REPORT.toString()), "0,1,SOUTH");
    assertEquals(game.executeCommand(Commands.LEFT.toString()), Constants.MSG_TURN_LEFT);
    assertEquals(game.executeCommand(Commands.RIGHT.toString()), Constants.MSG_TURN_RIGHT);
    assertEquals(game.executeCommand(Commands.REPORT.toString()), "0,1,SOUTH");
  }
}
