package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
interface Field {
    
    int getCurrentNumberOfEmptySubFields();

    char[] getCurrentField();

    char getEmptyValue();

    void fillInSubfield(int subfieldNumber, char mark) throws SubfieldNumberInvalidException;
    
    int getNumberOfRows();
    
    int getNumberOfColumns();
}
