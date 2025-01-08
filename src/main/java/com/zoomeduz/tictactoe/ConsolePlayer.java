package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
class ConsolePlayer implements IPlayer {

    private final IGameUI ui;
    private final Mark mark;

    ConsolePlayer(IGameUI ui, Mark mark) {
        this.ui = ui;
        this.mark = mark;
    }

    @Override
    public int getMove(IFieldViewer field) {
        if (Core.getAvailCellIndexes(field).isEmpty()) {
            throw new RuntimeException("Нет доступных ячеек на поле для хода");
        }
        return ui.getConsolePlayerMove(field, mark);
    }

    @Override
    public Mark getMark() {
        return mark;
    }

}
