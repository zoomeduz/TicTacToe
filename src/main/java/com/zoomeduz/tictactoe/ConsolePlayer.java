package com.zoomeduz.tictactoe;

import java.util.HashSet;
import java.util.Set;

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
    public void onGameStarted(IFieldViewer fv) {
        field = fv;
    }

    @Override
    public void onGameFinished(GameRoundResult grr) {
    }

    @Override
    public int getMove() {
        Set<Integer> availCellIndexes = new HashSet<>();
        for (int i = 0; i < field.getNumberOfCells(); i++) {
            if (field.get(i) == null) {
                availCellIndexes.add(i);
            }
        }
        return ui.getCellIndex(field, availCellIndexes);
    }

    @Override
    public void onMoveProcessed() {
    }

}
