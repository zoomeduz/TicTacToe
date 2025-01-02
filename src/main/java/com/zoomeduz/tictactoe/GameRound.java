package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
public class GameRound {

    private final static Field        field = new Field();
    private final static IFieldViewer viewer = makeViewer(field);

    static void run(IPlayer playerX, IPlayer playerO, IObserver  observer) {
        IPlayer[] players = new IPlayer[]{playerX, playerO};

        for(IPlayer p: players) {
            p.onGameStarted(viewer);
        }
        observer.onGameRoundStarted(viewer);

        GameRoundResult result = play(players, observer);

        for(IPlayer p: players) {
            p.onGameFinished(result);
        }
        observer.onGameRoundFinished(result);
    }

    private static IFieldViewer makeViewer(Field field) {
        return new IFieldViewer() {

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

    private static GameRoundResult play(IPlayer[] players, IObserver  observer) {
        int playerIndex = 0;

        while(field.getNumberOfFreeCells() > 0) {
            IPlayer currentPlayer = players[playerIndex];
            Mark currentMark = playerIndex == 0? Mark.X : Mark.O;

            observer.onMoveBefore(currentMark);
            Integer validCellIndex = null;
            
            while(validCellIndex == null) {
                int cellIndex = currentPlayer.getMove();

                if (!field.isOnField(cellIndex)) {
                    currentPlayer.onMoveProcessed(MoveResult.INDEX_OUT_OF_FIELD);
                    continue;
                }

                if (field.get(cellIndex) != null) {
                    currentPlayer.onMoveProcessed(MoveResult.INDEX_OF_FILLED_CELL);
                    continue;
                }

                validCellIndex = cellIndex;
            }

            field.set(validCellIndex, currentMark);
            currentPlayer.onMoveProcessed(MoveResult.SUCCESSFUL);
            observer.onMoveDone(validCellIndex, currentMark);

            if (Referee.hasWin(viewer, validCellIndex)) {
                return playerIndex == 0? GameRoundResult.WIN_X : GameRoundResult.WIN_O;
            }

            playerIndex = (playerIndex + 1) % players.length;
        }

        return GameRoundResult.DRAW;
    }

}
