package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
class MoveDisplayWrapper implements IPlayer {

    private final IGameUI ui;
    private final IPlayer player;

    MoveDisplayWrapper(IGameUI ui, IPlayer nonConsolePlayer) {
        this.ui = ui;
        this.player = nonConsolePlayer;
    }

    @Override
    public int getMove(IFieldViewer field) {
        ui.displayMove(field, player.getMark());
        return player.getMove(field);
    }

    @Override
    public Mark getMark() {
        return player.getMark();
    }

}
