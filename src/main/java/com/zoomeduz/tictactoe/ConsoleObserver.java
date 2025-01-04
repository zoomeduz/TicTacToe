package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
class ConsoleObserver implements IObserver {
    
    private IFieldViewer field;
    private final IGameUI ui;
    private boolean displayHelpOnGameStart = false;

    ConsoleObserver(IGameUI ui, boolean displayHelpOnGameStart) {
        this.ui = ui;
        this.displayHelpOnGameStart = displayHelpOnGameStart;
    }

    @Override
    public void onGameRoundStarted(IFieldViewer fv) {
        field = fv;
        if (displayHelpOnGameStart) {
            ui.displayFieldWithHelp(field);
        }
    }

    @Override
    public void onGameRoundFinished(GameRoundResult grr) {
        ui.displayRoundResult(field, grr);
    }
    
    @Override
    public void onMoveBefore(Mark mark) {
        ui.displayCurrentMoveMark(field, mark);
    }

    @Override
    public void onMoveDone(int cellIndex, Mark mark) {
    }
    
}
