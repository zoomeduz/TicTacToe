package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
class GameRound {

    private final static Field        field = new Field();
    private final static IFieldViewer viewer = makeViewer(field);

    static GameRoundResult run(IPlayer playerX, IPlayer playerO, IObserver  observer) {
        IPlayer[] players = new IPlayer[]{playerX, playerO};

        for(IPlayer p: players) {
            p.onGameRoundStarted(viewer);
        }
        observer.onGameRoundStarted(viewer);

        GameRoundResult result = play(players, observer);

        for(IPlayer p: players) {
            p.onGameRoundFinished(result);
        }
        observer.onGameRoundFinished(result);
        return result;
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
            Integer cellIndex = currentPlayer.getMove();

            if (field.get(cellIndex) != null || !field.isOnField(cellIndex)) {
                throw new InvalidMoveException("Невалидный ход");
            }

            field.set(cellIndex, currentMark);
            observer.onMoveDone(cellIndex, currentMark);

            if (Referee.hasWin(viewer, cellIndex)) {
                return new GameRoundResult(viewer, currentMark);
            }

            playerIndex = (playerIndex + 1) % players.length;
        }

        return new GameRoundResult(viewer);
    }

}

class InvalidMoveException extends RuntimeException {

    InvalidMoveException(String message) {
        super(message);
    }

}
