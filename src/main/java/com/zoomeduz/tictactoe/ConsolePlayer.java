package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
class ConsolePlayer implements IPlayer {

    private IFieldViewer field;
    private final IGameUI ui;

    ConsolePlayer(IGameUI ui) {
        this.ui = ui;
    }

    @Override
    public void onGameRoundStarted(IFieldViewer fv) {
        field = fv;
    }

    @Override
    public void onGameRoundFinished(GameRoundResult grr) {
    }

    @Override
    public int getMove() {
        if (Core.getAvailCellIndexes(field).isEmpty()) {
            throw new RuntimeException("Нет доступных ячеек на поле для хода");
        }
        return ui.getCellIndex(field);
    }

}
