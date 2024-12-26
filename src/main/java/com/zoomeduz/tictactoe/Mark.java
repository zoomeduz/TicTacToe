package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
public enum Mark {

    X, O;

    @Override
    public String toString() {
        switch(this) {
            case X:     return "X";
            case O:     return "O";
            default:    return " ";
        }
    }

}
