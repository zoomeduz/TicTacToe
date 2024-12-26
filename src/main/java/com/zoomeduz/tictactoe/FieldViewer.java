package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
public interface FieldViewer {

    Mark get(int row, int column);

    Mark get(int cellIndex);

    int getNumberOfCells();

}
