package com.bhavish.robotapp.game;

public class Position {
  
  public Position() {
    super();
  }
  
  public Position(Position currentPosition) {
    this.row = currentPosition.getRow();
    this.column = currentPosition.getColumn();
  }

  int row,column;

  /**
   * @return the row
   */
  public int getRow() {
    return row;
  }

  /**
   * @param row the row to set
   */
  public void setRow(int row) {
    this.row = row;
  }

  /**
   * @return the column
   */
  public int getColumn() {
    return column;
  }

  /**
   * @param column the column to set
   */
  public void setColumn(int column) {
    this.column = column;
  }
  
  /**
   * Increment the co-ordiantes of the bot position.
   * @param changedRow
   * @param changedColumn
   */
  public void changeCordinates(int changedRow, int changedColumn) {
    this.row += changedRow;
    this.column += changedColumn;
  }

}
