package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */

interface Field2D {
    
    int getCurrentNumberOfEmptyCells();

    char getEmptyValue();

    void fillInCell(CellPosition position, char mark) throws CellInvalidException;
    
    int getNumberOfRows();
    
    int getNumberOfColumns();
    
    char getValue(CellPosition position);
    
    char getValue(int rowNumber, int columnNumber);
    
    CellPosition getLastFilledCell();
}
