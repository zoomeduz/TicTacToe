package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
public class GameRound {

    private final static int NUMBER_OF_CELLS = 9;
    private final static int NUMBER_OF_PLAYERS = 2;
    private final static Field3x3 field = new Field3x3();
    private final static FieldViewer viewer = makeViewer(field);

    static void run(Player playerX, Player playerO) {
        Player[] players = new Player[]{playerX, playerO};
        GameRoundResult result;

        for(Player p: players) {
            p.onGameStarted(viewer);
        }

        result = play(players);

        for(Player p: players) {
            p.onGameFinished(result);
        }
    }

    private static FieldViewer makeViewer(Field3x3 field) {
        return new FieldViewer() {

            @Override
            public Mark get(int row, int column) {
                return field.get(row, column);
            }

            @Override
            public Mark get(int cellIndex) {
                return field.get(cellIndex);
            }

            @Override
            public int getNumberOfCells() {
                return field.getNumberOfCells();
            }

        };
    }

    private static GameRoundResult play(Player[] players) {
        int playerIndex = 0;
        int cellIndex;
        Player currentPlayer;

        while(field.getNumberOfFreeCells() > 0) {
            currentPlayer = players[playerIndex];
            cellIndex = currentPlayer.getMove();

            if (cellIndex < 0 || cellIndex > NUMBER_OF_CELLS - 1) {
                currentPlayer.onMoveProcessed(MoveResult.INDEX_OUT_OF_FIELD);
                continue;
            }
            if (field.get(cellIndex) != null) {
                currentPlayer.onMoveProcessed(MoveResult.INDEX_OF_FILLED_CELL);
                continue;
            }

            field.set(cellIndex, playerIndex == 0? Mark.X : Mark.O);
            currentPlayer.onMoveProcessed(MoveResult.SUCCESSFUL);

            if (Referee3x3.hasWin(viewer, cellIndex)) {
                return playerIndex == 0? GameRoundResult.WIN_X : GameRoundResult.WIN_O;
            }

            playerIndex = (playerIndex + 1) % NUMBER_OF_PLAYERS;
        }

        return GameRoundResult.DRAW;
    }

}
