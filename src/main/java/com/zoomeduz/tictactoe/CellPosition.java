package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
class CellPosition {
    int column;
    int row;

    CellPosition(int rowNumber, int columnNumber) {
        this.row = rowNumber;
        this.column = columnNumber;
    }
    
    //cell number: 1..N
    int getCellPositionNumber(int numberOfColumns) {
        int r = row + 1;
        int c = column + 1;
        return (r - 1) * numberOfColumns + c;
    } 
}