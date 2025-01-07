package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
class ConsoleObserver implements IObserver {
    
    private IFieldViewer field;
    private final IGameUI ui;

    ConsoleObserver(IGameUI ui) {
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
    public void onMoveBefore(Mark mark) {
        ui.displayCurrentMoveMark(field, mark);
    }

    @Override
    public void onMoveDone(int cellIndex, Mark mark) {
    }
    
}
