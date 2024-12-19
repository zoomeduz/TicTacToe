package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
class Field3x3 implements Field2D {
    
    private static final int NUMBER_OF_ROWS = 3;
    private static final int NUMBER_OF_COLUMNS = 3;
    private static final char EMPTY_VALUE = ' ';
    private int currentNumberOfEmptyFields;
    private final char[][] simpleField;
    private CellPosition lastFilledCell;

    Field3x3() {
        currentNumberOfEmptyFields = NUMBER_OF_COLUMNS*NUMBER_OF_ROWS;
        simpleField = new char[NUMBER_OF_COLUMNS][NUMBER_OF_ROWS];
        lastFilledCell = null;

        for (int r = 0; r < NUMBER_OF_ROWS; r++) {
            for (int c = 0; c < NUMBER_OF_COLUMNS; c++) {
                simpleField[r][c] = EMPTY_VALUE;
            }
        }
    }

    @Override
    public int getCurrentNumberOfEmptyCells() {
        return currentNumberOfEmptyFields;
    }
    
    @Override
    public char getEmptyValue() {
        return EMPTY_VALUE;
    }

    @Override
    public void fillInCell(CellPosition pos, char mark) throws CellInvalidException {
        if (simpleField[pos.row][pos.column] != EMPTY_VALUE) {
            throw new CellInvalidException("В это поле нельзя сделать ход!\n");
        }
        simpleField[pos.row][pos.column] = mark;
        currentNumberOfEmptyFields--;
        lastFilledCell = pos;
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
    public char getValue(CellPosition pos) {
        return simpleField[pos.row][pos.column];
    }
    
    @Override
    public char getValue(int rowNumber, int columnNumber) {
        return simpleField[rowNumber][columnNumber];
    }
    
    @Override
    public CellPosition getLastFilledCell() {
        return lastFilledCell;
    }
}

class CellInvalidException extends Exception {
    private static final long serialVersionUID = 4805057172244117284L;

    public CellInvalidException(String message) {
        super(message);
    }
}