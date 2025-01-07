package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
interface IPlayer {

    int getMove(IFieldViewer fv);

    Mark getMark();

}
