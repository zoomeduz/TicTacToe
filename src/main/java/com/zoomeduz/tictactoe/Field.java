package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
class Field {
    
    private static final int NUMBER_OF_ROWS = 3;
    private static final int NUMBER_OF_COLUMNS = 3;
    private static final int SIZE = NUMBER_OF_COLUMNS * NUMBER_OF_ROWS;
    private final Mark[] field = new Mark[SIZE];
    private int numberOfFreeCells = SIZE;

    void set(int row, int column, Mark mark) {
        int cellIndex = getCellIndexByRowAndColumn(row, column);
        set(cellIndex, mark);
    }

    void set(int cellIndex, Mark mark) {
        if (!isOnField(cellIndex)) {
            throw new IndexOutOfBoundsException();
        }
        field[cellIndex] = mark;
        numberOfFreeCells--;
    }

    Mark get(int row, int column) {
        int cellIndex = getCellIndexByRowAndColumn(row, column);
        return get(cellIndex);
    }

    Mark get(int cellIndex) {
        if (!isOnField(cellIndex)) {
            throw new IndexOutOfBoundsException();
        }
        return field[cellIndex];
    }

    int getNumberOfFreeCells() {
        return numberOfFreeCells;
    }

    int getNumberOfCells() {
        return SIZE;
    }

    int getCellIndexByRowAndColumn(int row, int column) {
        return row * NUMBER_OF_COLUMNS + column;
    }

    boolean isOnField(int cellIndex) {
        return cellIndex >= 0 && cellIndex < SIZE;
    }

}