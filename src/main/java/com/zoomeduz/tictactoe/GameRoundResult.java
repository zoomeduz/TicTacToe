package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
class GameRoundResult {

    private final IFieldViewer resultField;
    private final Mark winner;

    GameRoundResult(IFieldViewer resultField) {
        this.resultField = resultField;
        this.winner = null;
    }

    GameRoundResult(IFieldViewer resultField, Mark winner) {
        this.resultField = resultField;
        this.winner = winner;
    }

    IFieldViewer getResultField() {
        return resultField;
    }

    Mark getWinner() {
        return winner;
    }

    boolean hasWinner() {
        return winner != null;
    }

}
