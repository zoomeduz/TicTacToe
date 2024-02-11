package com.zoomeduz.tictactoe;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author zoomeduz
 */
class Mover {
    //пока играет чисто рандомно, без анализа
    static CellPosition makeMove(Field field) {
        ArrayList<CellPosition> emptyCells = new ArrayList<>();
        for (int r = 0; r < field.getNumberOfRows(); r++) {
            for (int c = 0; c < field.getNumberOfColumns(); c++) {
                if (field.getValue(r, c) == field.getEmptyValue()) {
                    emptyCells.add(new CellPosition(r, c));
                }
            }
        }
        Random rn = new Random();
        int minimum = 0;
        int maximum = emptyCells.size() - 1;
        int index = rn.nextInt(maximum - minimum + 1) + minimum;
        return emptyCells.get(index);
    }
}
