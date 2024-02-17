package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
class CellPosition {
    int column;
    int row;
    
    CellPosition() {
        this.row = 0;
        this.column = 0;
    }

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
    
    boolean equals(CellPosition pos) {
        if (pos.row == this.row && pos.column == this.column) {
            return true;
        } else {
            return false;
        }
    }

}
