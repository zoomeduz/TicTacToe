package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
class Referee {

    private static final int NUMBER_OF_ROWS = 3;
    private static final int NUMBER_OF_COLUMNS = 3;
    private static final int WINNING_COMBINATION_LENGTH = 3;

    private static final Direction LEFT          = new Direction(+0, -1);
    private static final Direction RIGHT         = new Direction(+0, +1);
    private static final Direction TOP           = new Direction(-1, +0);
    private static final Direction BOTTOM        = new Direction(+1, +0);
    private static final Direction TOP_LEFT      = new Direction(-1, -1);
    private static final Direction BOTTOM_RIGHT  = new Direction(+1, +1);
    private static final Direction TOP_RIGHT     = new Direction(-1, +1);
    private static final Direction BOTTOM_LEFT   = new Direction(+1, -1);

    static boolean hasWin(IFieldViewer field, int cellIndex) {
        int target = WINNING_COMBINATION_LENGTH - 1; //-1, т.к. countMark() не учитывает ячейку cellIndex
        
        if (countMark(field, LEFT, cellIndex) + countMark(field, RIGHT, cellIndex) >= target) {
            return true;
        }

        if (countMark(field, TOP, cellIndex) + countMark(field, BOTTOM, cellIndex) >= target) {
            return true;
        }

        if (countMark(field, TOP_LEFT, cellIndex) + countMark(field, BOTTOM_RIGHT, cellIndex) >= target) {
            return true;
        }

        if (countMark(field, TOP_RIGHT, cellIndex) + countMark(field, BOTTOM_LEFT, cellIndex) >= target) {
            return true;
        }
        
        return false;
    }

    private static int countMark(IFieldViewer field, Direction dir, int cellIndex) {
        Mark mark = field.get(cellIndex);
        int r = cellIndex / NUMBER_OF_COLUMNS + dir.rowInc;
        int c = cellIndex % NUMBER_OF_COLUMNS + dir.columnInc;
        int count = 0;

        while(r < NUMBER_OF_ROWS && r >= 0 && c < NUMBER_OF_COLUMNS && c >= 0 && field.get(r, c) == mark) {
            r = r + dir.rowInc;
            c = c + dir.columnInc;
            count++;
        }
        
        return count;
    }

}

class Direction {

    int rowInc;
    int columnInc;

    Direction(int rowInc, int columnInc) {
        this.rowInc = rowInc;
        this.columnInc = columnInc;
    }

}