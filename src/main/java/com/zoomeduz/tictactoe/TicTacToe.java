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
        ui.displayHelp();

        IObserver observer = new ConsoleObserver(ui);

        GameRoundResult roundResult = switch(playerMark) {
            case X  -> GameRound.run(new ConsolePlayer(ui, Mark.X), new ComputerPlayer(Mark.O), observer);
            case O  -> GameRound.run(new ComputerPlayer(Mark.X), new ConsolePlayer(ui, Mark.O), observer);
            default -> throw new RuntimeException("Unreachable");
        };

        ui.displayRoundResult(roundResult);
    }

}