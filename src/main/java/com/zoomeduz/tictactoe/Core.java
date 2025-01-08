package com.zoomeduz.tictactoe;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zoomeduz
 */
class Core {

    static final int NUMBER_OF_ROWS_ON_FIELD    = 3;
    static final int NUMBER_OF_COLUMNS_ON_FIELD = 3;
    static final int WINNING_COMBINATION_LENGTH = 3;

    static List<Integer> getAvailCellIndexes(IFieldViewer field) {
        List<Integer> availCellIndexes = new ArrayList<>();
        for (int i = 0; i < field.getNumberOfCells(); i++) {
            if (field.get(i) == null) {
                availCellIndexes.add(i);
            }
        }
        return availCellIndexes;
    }

}
