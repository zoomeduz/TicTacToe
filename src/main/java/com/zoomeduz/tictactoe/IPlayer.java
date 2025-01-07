package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
interface IPlayer {

    void onGameRoundStarted(IFieldViewer fv);

    void onGameRoundFinished(GameRoundResult grr);

    int getMove();

    Mark getMark();

}
