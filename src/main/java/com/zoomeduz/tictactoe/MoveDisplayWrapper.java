package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
class MoveDisplayWrapper implements IPlayer {

    private IPlayer player;
    private final IGameUI ui;
    private IFieldViewer field;
    
    MoveDisplayWrapper(IGameUI ui, IPlayer nonConsolePlayer) {
        this.ui = ui;
        this.player = nonConsolePlayer;
    }

    @Override
    public void onGameRoundStarted(IFieldViewer fv) {
        field = fv;
        player.onGameRoundStarted(fv);
    }

    @Override
    public void onGameRoundFinished(GameRoundResult grr) {
        player.onGameRoundFinished(grr);
    }

    @Override
    public int getMove() {
        ui.displayCurrentMoveMark(field, player.getMark());
        return player.getMove();
    }

    @Override
    public Mark getMark() {
        return player.getMark();
    }

}
