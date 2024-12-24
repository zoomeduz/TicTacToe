package com.zoomeduz.tictactoe;

/*
 * Tic Tac Toe game
 *
 * @author zoomeduz
 */
public class TicTacToe {

    public static final char MARK_X = 'X';
    public static final char MARK_O = 'O';
    public static final int FIELD_SIZE = 3;
    public static final int WINNING_COMBINATION_LENGTH = 3;

    static enum Move {
        PLAYER, COMPUTER;
        
        @Override
        public String toString() {
            switch(this) {
                case PLAYER:    return "Вы";
                case COMPUTER:  return "Компьютер";
                default:        return "Не определено";
            }
        }
    };

    static Move changeMove(Move move) {
        switch(move) {
            case PLAYER:    return Move.COMPUTER;
            case COMPUTER:  return Move.PLAYER;
            default:        return null;
        }
    }

    public static void main(String[] args) {
        char playerMark;
        char computerMark;
        String inputEnteredByPlayer;
        int CellNumber = -1;
        Move move;
        Move winner = null;

        IO io = new ConsoleIO();
        io.outputText("Давайте сыграем в 'Крестики-нолики'!");
        while(true) {
            try {
                inputEnteredByPlayer = io.getUserString("Каким знаком будете играть? (" + MARK_X + " или " + MARK_O + ")");
                io.outputText("");
            } catch(Exception e) {
                io.outputText("Некорректный знак! Нужно выбрать либо '" + MARK_X + "' либо '" + MARK_O + "'.\n" + e + "\n");
                continue;
            }
            if (!inputEnteredByPlayer.equalsIgnoreCase(String.valueOf(MARK_X)) && !inputEnteredByPlayer.equalsIgnoreCase(String.valueOf(MARK_O))) {
                io.outputText("Нужно выбрать либо '" + MARK_X + "' либо '" + MARK_O + "'.");
                continue;
            }
            playerMark = inputEnteredByPlayer.toUpperCase().charAt(0);
            break;
        }
        if (playerMark == MARK_X) {
            io.outputText("Первым будете ходить вы.\n");
            computerMark = MARK_O;
            move = Move.PLAYER;
        } else {
            io.outputText("Первым будет ходить компьютер.\n");
            computerMark = MARK_X;
            move = Move.COMPUTER;
        }
        Field3x3 field = new Field3x3();

        io.outputText("Сетка игры выглядит следующим образом. \nПросьба указывать номер поля, куда хотите сделать свой ход.\n");
        io.displayFieldWithCellNumbers();

        boolean hasWin = false;
        
        io.displayField(field);
        while(!hasWin && field.getCurrentNumberOfEmptyCells() != 0) {
            switch(move) {
                case PLAYER:
                    try {
                        CellNumber = io.getUserInt("Ваш ход (1-9, 0 - показать номера полей)\n\n");
                    } catch(Exception e) {
                        io.outputText("Некорректный знак! Нужно выбрать либо номер поля от 1 до 9.\n" + e + "\n");
                        continue;
                    }
                    if (CellNumber == 0) {
                        io.displayFieldWithCellNumbers();
                        continue;
                    }
                    try {
                        field.fillInCell(getCellPositionByNumber(CellNumber, field.getNumberOfRows(), field.getNumberOfColumns())
                                           , playerMark);
                    } catch(CellInvalidException | CellNumberInvalidException e) {
                        io.outputText(e.toString());
                        continue;
                    }
                    break;
                case COMPUTER:
                    io.outputText("Ход противника\n");
                    try {
                        CellPosition position = Mover.makeMove(field);
                        field.fillInCell(position, computerMark);
                    } catch(CellInvalidException e) {
                        io.outputText(e.toString());
                        System.exit(1);
                    }
                    break;
            }
            io.displayField(field);
            
            if(Referee.hasWin(field, WINNING_COMBINATION_LENGTH)) {
                hasWin = true;
                winner = move;
            } else {
                move = changeMove(move);
            }
        }
        if (winner == null) {
            io.outputText("Ничья!");
        } else {
            io.outputText("Победитель: " + winner + "!");
        }
    }
    
    //cell number: 1..N
    static CellPosition getCellPositionByNumber(int cellNumber, int numberOfRows, int numberOfColumns) throws CellNumberInvalidException {
        if (numberOfRows < 1 || numberOfColumns < 1) {
            throw new CellNumberInvalidException("Кол-во столбцов и кол-во строк может быть только натуральное число от 1 до N");
        }
        if (cellNumber < 1) {
            throw new CellNumberInvalidException("Номером ячейки может быть только натуральное число от 1 до N");
        }
        if (cellNumber > numberOfRows * numberOfColumns) {
            throw new CellNumberInvalidException("Для заданных numberOfRows и numberOfColumns не может быть такого cellNumber");
        }

        cellNumber -= 1;
        int row = cellNumber / numberOfColumns;
        int column = cellNumber % numberOfColumns;

        return new CellPosition(row, column);
    }
}

class CellNumberInvalidException extends Exception {

    public CellNumberInvalidException(String message) {
        super(message);
    }
}