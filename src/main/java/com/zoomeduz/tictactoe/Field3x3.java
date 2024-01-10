package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
class Field3x3 {
    
    private static final int NUMBER_OF_FIELDS = 9;
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

    int getCurrentNumberOfEmptyFields() {
        return currentNumberOfEmptyFields;
    }

    char[] getCurrentField() {
        return simpleField;
    }

    void fillInSubfield(int subfieldNumber, char mark) throws SubfieldNumberInvalidException {
        subfieldNumber--; //-1 т.к. в массиве от 0 индексы
        try {
            if (simpleField[subfieldNumber] != EMPTY_VALUE) {
                throw new SubfieldNumberInvalidException("В это поле нельзя сделать ход!\n");
            }
            simpleField[subfieldNumber] = mark;
        } catch (Exception e) {
            throw new SubfieldNumberInvalidException("В это поле нельзя сделать ход!\n");
        }
        currentNumberOfEmptyFields--;
    }   
}

class SubfieldNumberInvalidException extends Exception {
	private static final long serialVersionUID = 4805057172244117284L;

	public SubfieldNumberInvalidException(String message){
        super(message);
    }
}