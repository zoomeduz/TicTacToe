package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
interface IGameUI {

    void displayGameStart();

    int getConsolePlayerMove(IFieldViewer fv, Mark mark);

    Mark getMarkForPlayer();

    void displayHelp();

    void displayNonConsolePlayerMove(IFieldViewer fv, Mark mark);

    void displayRoundResult(GameRoundResult grr);

}
