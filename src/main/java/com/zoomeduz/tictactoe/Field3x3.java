package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
class Field3x3 {
    
    private static final int NUMBER_OF_ROWS = 3;
    private static final int NUMBER_OF_COLUMNS = 3;
    private final Mark[][] field = new Mark[NUMBER_OF_COLUMNS][NUMBER_OF_ROWS];
    private int numberOfFreeCells = NUMBER_OF_COLUMNS * NUMBER_OF_ROWS;

    void set(int row, int column, Mark mark) {
        field[column][row] = mark;
        numberOfFreeCells--;
    }

    void set(int cellIndex, Mark mark) {
        int row = cellIndex / NUMBER_OF_COLUMNS;
        int column = cellIndex % NUMBER_OF_COLUMNS;
        set(row, column, mark);
    }

    Mark get(int row, int column) {
        return field[column][row];
    }

    Mark get(int cellIndex) {
        int row = cellIndex / NUMBER_OF_COLUMNS;
        int column = cellIndex % NUMBER_OF_COLUMNS;
        return get(row, column);
    }

    int getNumberOfFreeCells() {
        return numberOfFreeCells;
    }

    int getNumberOfCells() {
        return NUMBER_OF_COLUMNS * NUMBER_OF_ROWS;
    }

}