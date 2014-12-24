package com.bhavish.robotapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.bhavish.robotapp.game.BotGame;
import com.bhavish.robotapp.game.board.Board;
import com.bhavish.robotapp.game.board.SquareBoard;

public class Simulator {

  public static void main(String[] args) {
    Board squareBoard = new SquareBoard(5, 5);
    BotGame game = new BotGame(squareBoard);
   
    System.out.println("Simulator");
    System.out.println("Enter a command, Valid commands are:");
    System.out.println("PLACE X,Y,NORTH|SOUTH|EAST|WEST, MOVE, LEFT, RIGHT, REPORT or EXIT");
    try {
      while(true) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command = reader.readLine();
        if ("EXIT".equals(command)) {
            break;
        } else {
          String value = game.executeCommand(command);
          System.out.println(value);
        } 
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("BYE!!");
  }
}
