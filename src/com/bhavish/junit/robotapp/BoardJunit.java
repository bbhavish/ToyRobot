package com.bhavish.junit.robotapp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.bhavish.robotapp.game.Position;
import com.bhavish.robotapp.game.board.Board;
import com.bhavish.robotapp.game.board.SquareBoard;

public class BoardJunit {

  Board sqBoard = null;
  @Before
  public void setUp() throws Exception {
    sqBoard = new SquareBoard(5, 5);
  }

  @Test
  public void verifyBoard() {
    assertNotNull(sqBoard);
    //NORTH EAST corner
    Position position = new Position();
    position.setRow(4);
    position.setColumn(4);
    assertTrue(sqBoard.verifyPosition(position));
    //SOUTH WEST corner
    position.setRow(0);
    position.setColumn(0);
    assertTrue(sqBoard.verifyPosition(position));
    //NORTH WEST corner
    position.setRow(0);
    position.setColumn(4);
    assertTrue(sqBoard.verifyPosition(position));
    //SOUTH EAST corner
    position.setRow(0);
    position.setColumn(0);
    assertTrue(sqBoard.verifyPosition(position));
    //Negative value
    position.setRow(-1);
    position.setColumn(-1);
    assertFalse(sqBoard.verifyPosition(position));
    //more than rows and columns
    position.setRow(5);
    position.setColumn(5);
    assertFalse(sqBoard.verifyPosition(position));
    
    
    
  }

}
