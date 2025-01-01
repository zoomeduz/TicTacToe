package com.zoomeduz.tictactoe;

/*
 * Tic Tac Toe game
 *
 * @author zoomeduz
 */
public class TicTacToe {

    private static IO io;

    public static void main(String[] args) {

        //режим пока один: игрок против компьютера
        io = new ConsoleIO();
        io.outputText("Давайте сыграем в 'Крестики-нолики'!");

        Mark playerMark = getMarkForPlayer();

        switch(playerMark) {
            case X:
                io.outputText("Первым будете ходить вы.\n");
                GameRound.run(new ConsolePlayer(io), new ComputerPlayer());
                break;
            case O:
                io.outputText("Первым будет ходить компьютер.\n");
                GameRound.run(new ComputerPlayer(), new ConsolePlayer(io));
                break;
            default:
                throw new RuntimeException("Unreachable");
        }

    }

    private static Mark getMarkForPlayer() {

        String inputEnteredByPlayer;

        while(true) {
            try {
                io.outputText("Каким знаком будете играть? (" + Mark.X + " или " + Mark.O + ")");
                inputEnteredByPlayer = io.getString();
                io.outputText("");
            } catch(Exception e) {
                io.outputText("Некорректный знак! Нужно выбрать либо '" + Mark.X + "' либо '" + Mark.O + "'.\n" + e + "\n");
                continue;
            }
            if (inputEnteredByPlayer.equalsIgnoreCase(Mark.X.toString())) {
                return Mark.X;
            }
            if (inputEnteredByPlayer.equalsIgnoreCase(Mark.O.toString())) {
                return Mark.O;
            }
            io.outputText("Нужно выбрать либо '" + Mark.X + "' либо '" + Mark.O + "'.");
        }

    }

}