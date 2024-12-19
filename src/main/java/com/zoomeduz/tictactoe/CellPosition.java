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
    
    boolean equals(CellPosition pos) {
        return (pos.row == this.row && pos.column == this.column);
    }
}
