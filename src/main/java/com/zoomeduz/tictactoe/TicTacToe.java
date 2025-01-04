package com.zoomeduz.tictactoe;

/*
 * Tic Tac Toe game
 *
 * @author zoomeduz
 */
public class TicTacToe {

    private static IGameUI ui;

    public static void main(String[] args) {

        //режим пока один: игрок против компьютера
        ui = new GameUI(new ConsoleIO());

        ui.displayGameStart();
        Mark playerMark = ui.getMarkForPlayer();

        IObserver observer = new ConsoleObserver(ui, true);

        switch(playerMark) {
            case X:
                GameRound.run(new ConsolePlayer(ui), new ComputerPlayer(), observer);
                break;
            case O:
                GameRound.run(new ComputerPlayer(), new ConsolePlayer(ui), observer);
                break;
            default:
                throw new RuntimeException("Unreachable");
        }
    }

}