package com.bhavish.robotapp.game;

import java.util.HashMap;
import java.util.Map;

import com.bhavish.robotapp.exception.RobotException;
import com.bhavish.robotapp.game.board.Board;
import com.bhavish.robotapp.util.Constants;
import com.bhavish.robotapp.util.Coordinates;

public class Robot implements Constants {

  Position position;
  Coordinates movingDirection;
  private Board board;
  private Map<String, Integer> directionToIndexMap = null;
  private Map<Integer, String> indexToDirectionMap = null;
  private Boolean validBoard = Boolean.FALSE;

  /**
   * Initialize Robot..
   * @param board
   */
  public Robot(Board board) {
    this.board = board;
    this.position = new Position();
  }

  /**
   * Verify if the board is initialized. This would be done when we hit PLACE command.
   * @throws RobotException
   */
  public void isValidBoard() throws RobotException {
    if (!validBoard)
      throw new RobotException(ERR_INVALID_ACTION_BEFORE_PLACECOMMAND);

  }

  /**
   * Place the robot on the board.
   * @param row - Index of row where the bot would be placed
   * @param column - Index of column where the bot would be placed
   * @param direction - Direction where the bot is pointing
   * @throws RobotException
   */
  public void placeBot(int row, int column, Coordinates direction) throws RobotException {
    if(board == null) {
      throw new RobotException(ERR_BOARD_NOT_INITIALIZED);
    }
    if(position == null) {
      throw new RobotException(ERR_BOT_POSTION_NOT_INITIALIZED);
    }
    //Initialize position.
    getPosition().setRow(row);
    getPosition().setColumn(column);
    //Verify if the position is inside the board.
    if(!board.verifyPosition(position)) {
      throw new RobotException(ERR_INVALID_POS_WHILE_PLACING_BOT);
    }
    //set the direction.
    movingDirection = direction;
    //initialize the maps - will be used while we turn left or right
    setMovingMap();
    validBoard = Boolean.TRUE;
  }

  /**
   * Reports the current Row, Column co-ordinates along with the direction of the BOT.
   * @return
   * @throws RobotException
   */
  public String report() throws RobotException {
    isValidBoard();
    return getPosition().getRow() + "," + getPosition().getColumn() + ","
        + getMovingDirection();
  }

  /**
   * Turn the bot Left
   * @return
   * @throws RobotException
   */
  public String turnLeft() throws RobotException {
    isValidBoard();
    validateDirection();
    turnLeftOrRight(-1);
    return MSG_TURN_LEFT;
  }

  /**
   * Turn the bot Right
   * @return
   * @throws RobotException
   */
  public String turnRight() throws RobotException {
    isValidBoard();
    validateDirection();
    turnLeftOrRight(1);
    return MSG_TURN_RIGHT;
  }

  /**
   * Moves the bot in the forward direction. Bot will be stopped when the border is reached.
   * @return
   * @throws RobotException
   */
  public String moveForward() throws RobotException {
    isValidBoard();
    validateDirection();
    String msg = MSG_VALID_FWD_MOVE;
    Position newPosition = getNextPosition();
    if (board.verifyPosition(newPosition)) {
      this.position = newPosition;
    } else {
      // invalid position.
      msg = MSG_INVALID_FWD_MOVE;
    }
    return msg;
  }

  private void validateDirection() throws RobotException {
    if (this.movingDirection == null) {
      throw new RobotException(ERR_DIRECTON_NOT_SET);
    }
  }

  /**
   * Logic to turn the bot. 
   * Index is given to each co-ordinates (North - 0, East - 1, South - 2, West - 3) and the value
   * is rotated whenever its turned left or right.
   * @param step
   */
  private void turnLeftOrRight(int step) {
    int intermediatePos = directionToIndexMap.get(this.movingDirection.toString())+step;
    int newIndex = 0;
    if(intermediatePos < 0) {
      newIndex = directionToIndexMap.size() - 1;
    } else {
      newIndex = intermediatePos % directionToIndexMap.size();
    }
   
    this.movingDirection = Coordinates.valueOf(indexToDirectionMap.get(newIndex));
  }

  /**
   * Change the co-ordinates whenever the bot moves forward.
   * If the bot is pointing to NORTH, then only Y co-ordinate is increased by 1
   * If the bot is pointing to SOUTH, then only Y co-ordinate is decremented by 1
   * If the bot is pointing to EAST, then only X co-ordinate is increased by 1
   * If the bot is pointing to SOUTH, then only X co-ordinate is decremented by 1
   * @return
   * @throws RobotException
   */
  public Position getNextPosition() throws RobotException {
    Position nextPosition = new Position(this.getPosition());
    validateDirection();
    switch (this.movingDirection) {
    case NORTH:
      nextPosition.changeCordinates(NORTH_XCOORDINATE, NORTH_YCOORDINATE);
      break;
    case EAST:
      nextPosition.changeCordinates(EAST_XCOORDINATE,EAST_YCOORDINATE);
      break;
    case SOUTH:
      nextPosition.changeCordinates(SOUTH_XCOORDINATE, SOUTH_YCOORDINATE);
      break;
    case WEST:
      nextPosition.changeCordinates(WEST_XCOORDINATE,WEST_YCOORDINATE);
      break;
    }
    return nextPosition;
  }

  /**
   * Initializes the maps required to rotate the bots.
   */
  private void setMovingMap() {
    directionToIndexMap = new HashMap<String, Integer>();
    indexToDirectionMap = new HashMap<Integer, String>();
    int i = 0;
    for (Coordinates direction : Coordinates.values()) {
      directionToIndexMap.put(direction.toString(), new Integer(i));
      indexToDirectionMap.put(new Integer(i), direction.toString());
      i++;
    }
  }

  /**
   * @return the position
   */
  public Position getPosition() {
    return position;
  }

  /**
   * @param position
   *          the position to set
   */
  public void setPosition(Position position) {
    this.position = position;
  }

  /**
   * @return the movingDirection
   */
  public Coordinates getMovingDirection() {
    return movingDirection;
  }

  /**
   * @param movingDirection
   *          the movingDirection to set
   */
  public void setMovingDirection(Coordinates movingDirection) {
    this.movingDirection = movingDirection;
  }

  /**
   * @return the board
   */
  public Board getBoard() {
    return board;
  }

  /**
   * @param board
   *          the board to set
   */
  public void setBoard(Board board) {
    this.board = board;
  }

}
