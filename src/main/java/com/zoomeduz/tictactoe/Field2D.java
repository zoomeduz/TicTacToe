package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */

interface Field2D {
    
    int getCurrentNumberOfEmptySubFields();

    char getEmptyValue();

    void fillInSubfield(CellPosition position, char mark) throws SubfieldNumberInvalidException;
    
    int getNumberOfRows();
    
    int getNumberOfColumns();
    
    char getValue(CellPosition position);
    
    char getValue(int rowNumber, int columnNumber);
    
    CellPosition getLastFilledSubfield();
}
