package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
interface IFieldViewer {

    Mark get(int row, int column);

    Mark get(int cellIndex);

    int getNumberOfCells();

}
