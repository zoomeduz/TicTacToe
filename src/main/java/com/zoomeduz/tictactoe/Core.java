package com.zoomeduz.tictactoe;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author zoomeduz
 */
class Core {

    static final int NUMBER_OF_ROWS_ON_FIELD    = 3;
    static final int NUMBER_OF_COLUMNS_ON_FIELD = 3;
    static final int WINNING_COMBINATION_LENGTH = 3;

    static Set<Integer> getAvailCellIndexes(IFieldViewer field) {
        Set<Integer> availCellIndexes = new HashSet<>();
        for (int i = 0; i < field.getNumberOfCells(); i++) {
            if (field.get(i) == null) {
                availCellIndexes.add(i);
            }
        }
        return availCellIndexes;
    }

}
