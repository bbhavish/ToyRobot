package com.bhavish.robotapp.game.board;

import com.bhavish.robotapp.game.Position;


public interface Board {

  /**
   * Verifies if the position of the bot is inside the board. 
   * @param position
   * @return TRUE : When the x and y co-ordinates is inside the board.
   *         FALSE: When the x and y co-ordinates is outside the board.
   */
  public Boolean verifyPosition(Position position);
}
