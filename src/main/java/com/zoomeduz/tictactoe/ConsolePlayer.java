package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
class ConsolePlayer implements IPlayer {

    private IFieldViewer field;
    private final IGameUI ui;
    private final Mark mark;

    ConsolePlayer(IGameUI ui, Mark mark) {
        this.ui = ui;
        this.mark = mark;
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
        ui.displayCurrentMoveMark(field, mark);
        return ui.getCellIndex(field);
    }

    @Override
    public Mark getMark() {
        return mark;
    }

}
