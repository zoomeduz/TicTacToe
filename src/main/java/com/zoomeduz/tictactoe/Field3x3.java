package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
class Field3x3 implements Field {
    
    private static final int NUMBER_OF_ROWS = 3;
    private static final int NUMBER_OF_COLUMNS = 3;
    private static final char EMPTY_VALUE = ' ';
    private int currentNumberOfEmptyFields;
    private char[][] simpleField;

    Field3x3() {
        currentNumberOfEmptyFields = NUMBER_OF_COLUMNS*NUMBER_OF_ROWS;
        simpleField = new char[NUMBER_OF_COLUMNS][NUMBER_OF_ROWS];
        for (int r = 0; r < NUMBER_OF_ROWS; r++) {
            for (int c = 0; c < NUMBER_OF_COLUMNS; c++) {
                simpleField[r][c] = EMPTY_VALUE;
            }
        }
    }

    @Override
    public int getCurrentNumberOfEmptySubFields() {
        return currentNumberOfEmptyFields;
    }

    @Override
    public char[][] getCurrentField() {
        return simpleField;
    }
    
    @Override
    public char getEmptyValue() {
        return EMPTY_VALUE;
    }

    @Override
    public void fillInSubfield(int rowNumber, int columnNumber, char mark) throws SubfieldNumberInvalidException {
        if (simpleField[rowNumber][columnNumber] != EMPTY_VALUE) {
            throw new SubfieldNumberInvalidException("В это поле нельзя сделать ход!\n");
        }
        simpleField[rowNumber][columnNumber] = mark;
        currentNumberOfEmptyFields--;
    }
    
    @Override
    public int getNumberOfRows() {
        return NUMBER_OF_ROWS;
    }
    
    @Override
    public int getNumberOfColumns() {
        return NUMBER_OF_COLUMNS;
    }
    
    @Override
    public char getValue(int rowNumber, int columnNumber) {
        return simpleField[rowNumber][columnNumber];
    }
}

class SubfieldNumberInvalidException extends Exception {
	private static final long serialVersionUID = 4805057172244117284L;

	public SubfieldNumberInvalidException(String message){
        super(message);
    }
}