package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
class Field3x3 implements Field {
    
    private static final int NUMBER_OF_FIELDS = 9;
    private static final int NUMBER_OF_ROWS = 3;
    private static final int NUMBER_OF_COLUMNS = 3;
    private static final char EMPTY_VALUE = ' ';
    private int currentNumberOfEmptyFields;
    private char[] simpleField;

    Field3x3() {
        currentNumberOfEmptyFields = NUMBER_OF_FIELDS;
        simpleField = new char[NUMBER_OF_FIELDS];
        for (int i = 0; i < simpleField.length; i++) {
            simpleField[i] = EMPTY_VALUE;
        }
    }

    @Override
    public int getCurrentNumberOfEmptySubFields() {
        return currentNumberOfEmptyFields;
    }

    @Override
    public char[] getCurrentField() {
        return simpleField;
    }
    
    @Override
    public char getEmptyValue() {
        return EMPTY_VALUE;
    }

    @Override
    public void fillInSubfield(int subfieldNumber, char mark) throws SubfieldNumberInvalidException {
        subfieldNumber--; //-1 т.к. в массиве от 0 индексы
        if (subfieldNumber < 0 || subfieldNumber >= NUMBER_OF_FIELDS) {
            throw new SubfieldNumberInvalidException("В это поле нельзя сделать ход!\n");
        }
        if (simpleField[subfieldNumber] != EMPTY_VALUE) {
            throw new SubfieldNumberInvalidException("В это поле нельзя сделать ход!\n");
        }
        simpleField[subfieldNumber] = mark;
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
}

class SubfieldNumberInvalidException extends Exception {
	private static final long serialVersionUID = 4805057172244117284L;

	public SubfieldNumberInvalidException(String message){
        super(message);
    }
}