package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
public enum GameRoundResult {

    WIN_X,
    WIN_O,
    DRAW;

    @Override
    public String toString() {
        switch(this) {
            case WIN_X:     return "Выиграли X";
            case WIN_O:     return "Выиграли O";
            case DRAW:      return "Ничья";
            default:        return "";
        }
    }

}
