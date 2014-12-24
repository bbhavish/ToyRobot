package com.bhavish.robotapp.game;

import com.bhavish.robotapp.exception.RobotException;
import com.bhavish.robotapp.game.board.Board;
import com.bhavish.robotapp.util.Commands;
import com.bhavish.robotapp.util.Constants;
import com.bhavish.robotapp.util.Coordinates;

public class BotGame implements Constants{
  
  Board board;
  Robot bot;

  public BotGame(Board board) {
    super();
    this.board = board;
    this.bot = new Robot(board);
  }

  public String executeCommand(String inputCommand) {
    String output = null;
    try {
      String[] args = inputCommand.trim().split(" ");
      Commands command;
      try {
        command = Commands.valueOf(args[0]);
      } catch (IllegalArgumentException e) {
        throw new RobotException(ERR_INVALID_COMMAND);
      }
      if (command == Commands.PLACE && args.length < 2) {
        throw new RobotException(ERR_INVALID_PLACE_COMMAND);
      }

      // validate PLACE params
      String[] params;
      int x = 0;
      int y = 0;
      Coordinates commandDirection = null;
      if (command == Commands.PLACE) {
        params = args[1].split(",");
        try {
          x = Integer.parseInt(params[0]);
          y = Integer.parseInt(params[1]);
          commandDirection = Coordinates.valueOf(params[2]);
        } catch (Exception e) {
          throw new RobotException(ERR_INVALID_PLACE_COMMAND);
        }
      }

      switch (command) {
      case PLACE:
        output = placeBot(x, y, commandDirection);
        break;
      case REPORT:
        output = bot.report();
        break;
      case MOVE :
        output = bot.moveForward();
        break;
      case LEFT :
        output = bot.turnLeft();
        break;
      case RIGHT :
        output = bot.turnRight();
        break;
      default:
        throw new RobotException(ERR_INVALID_COMMAND);
      }
    } catch (RobotException e) {
      output = e.getMessage();
    }
    return output;
  }

  private String placeBot(int x, int y, Coordinates commandDirection) throws RobotException {
    this.bot.placeBot(x, y, commandDirection);
    return "Bot placed at "+x+","+y+" facing "+commandDirection;
  }
}
