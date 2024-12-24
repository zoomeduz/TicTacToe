package com.zoomeduz.tictactoe;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author zoomeduz
 */
class Mover {
    //пока играет чисто рандомно, без анализа
    static CellPosition makeMove(Field2D field) {
        ArrayList<CellPosition> emptyCells = new ArrayList<>();
        for (int r = 0; r < field.getNumberOfRows(); r++) {
            for (int c = 0; c < field.getNumberOfColumns(); c++) {
                CellPosition pos = new CellPosition(r, c);
                if (field.getValue(pos) == field.getEmptyValue()) {
                    emptyCells.add(pos);
                }
            }
        }
        Random rn = new Random();
        int index = rn.nextInt(emptyCells.size());
        return emptyCells.get(index);
    }
}
