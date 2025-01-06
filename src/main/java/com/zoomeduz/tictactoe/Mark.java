package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
enum Mark {

    X, O;

    @Override
    public String toString() {
        return switch (this) {
            case X  -> "X";
            case O  -> "O";
            default -> " ";
        };
    }

}
