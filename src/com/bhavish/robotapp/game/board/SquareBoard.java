package com.bhavish.robotapp.game.board;

import com.bhavish.robotapp.game.Position;

public class SquareBoard implements Board {

  int sqrow;
  int sqcolumn;
  
  /**
   * Initializes the square board..
   * @param rows
   * @param columns
   */
  public SquareBoard(int rows, int columns) {
      this.sqrow = rows;
      this.sqcolumn = columns;
  }
  
  @Override
  public Boolean verifyPosition(Position position) {
    if(position.getRow() <0 || position.getColumn() < 0 || position.getRow() >= this.sqrow || position.getColumn() >= this.sqcolumn) {
      return Boolean.FALSE;
    } else {
      return Boolean.TRUE;
    }
  }

}
