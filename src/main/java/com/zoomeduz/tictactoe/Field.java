package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
interface Field {
    
    int getCurrentNumberOfEmptySubFields();

    char[][] getCurrentField();

    char getEmptyValue();

    void fillInSubfield(int rowNumber, int columnNumber, char mark) throws SubfieldNumberInvalidException;
    
    int getNumberOfRows();
    
    int getNumberOfColumns();
    
    char getValue(int rowNumber, int columnNumber);
}
