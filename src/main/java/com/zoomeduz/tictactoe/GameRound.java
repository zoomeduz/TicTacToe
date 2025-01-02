package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
public class GameRound {

    private final static Field field = new Field();
    private final static FieldViewer viewer = makeViewer(field);

    static void run(Player playerX, Player playerO) {
        Player[] players = new Player[]{playerX, playerO};

        for(Player p: players) {
            p.onGameStarted(viewer);
        }

        GameRoundResult result = play(players);

        for(Player p: players) {
            p.onGameFinished(result);
        }
    }

    private static FieldViewer makeViewer(Field field) {
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

        while(field.getNumberOfFreeCells() > 0) {
            Player currentPlayer = players[playerIndex];
            int cellIndex = currentPlayer.getMove();

            if (!field.isOnField(cellIndex)) {
                currentPlayer.onMoveProcessed(MoveResult.INDEX_OUT_OF_FIELD);
                continue;
            }
            if (field.get(cellIndex) != null) {
                currentPlayer.onMoveProcessed(MoveResult.INDEX_OF_FILLED_CELL);
                continue;
            }

            field.set(cellIndex, playerIndex == 0? Mark.X : Mark.O);
            currentPlayer.onMoveProcessed(MoveResult.SUCCESSFUL);

            if (Referee.hasWin(viewer, cellIndex)) {
                return playerIndex == 0? GameRoundResult.WIN_X : GameRoundResult.WIN_O;
            }

            playerIndex = (playerIndex + 1) % players.length;
        }

        return GameRoundResult.DRAW;
    }

}
