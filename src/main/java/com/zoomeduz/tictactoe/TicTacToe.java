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

    public static void main(String[] args){
        char playerMark;
        char computerMark;
        String inputEnteredByPlayer;
        int subfieldNumber = -1;
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
        io.displayFieldWithSubfieldNumbers();

        boolean hasWin = false;
        
        io.displayField(field);
        while(!hasWin && field.getCurrentNumberOfEmptySubFields() != 0) {
            switch(move) {
                case PLAYER:
                    try {
                        subfieldNumber = io.getUserInt("Ваш ход (1-9, 0 - показать номера полей)\n");
                        io.outputText("");
                    } catch(Exception e) {
                        io.outputText("Некорректный знак! Нужно выбрать либо номер поля от 1 до 9.\n" + e + "\n");
                        continue;
                    }
                    if (subfieldNumber == 0) {
                        io.displayFieldWithSubfieldNumbers();
                        continue;
                    }
                    try {
                        fillInSubfieldInField3x3(field, subfieldNumber, playerMark);
                    } catch(SubfieldNumberInvalidException e) {
                        io.outputText(e.toString());
                        continue;
                    }
                    break;
                case COMPUTER:
                    io.outputText("Ход противника\n");
                    try {
                        CellPosition position = Mover.makeMove(field);
                        fillInSubfieldInField3x3(field, position.getCellPositionNumber(field.getNumberOfColumns()), computerMark);
                    } catch(SubfieldNumberInvalidException e) {
                        io.outputText(e.toString());
                        break; //тут надо завершать игру, т.к. COMPUTER в неадеквате
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
    
    static void fillInSubfieldInField3x3(Field3x3 field, int subfieldNumber, char playerMark) throws SubfieldNumberInvalidException {
        switch(subfieldNumber) {
            case 1:
                field.fillInSubfield(0, 0, playerMark);
                return;
            case 2:
                field.fillInSubfield(0, 1, playerMark);
                return;
            case 3:
                field.fillInSubfield(0, 2, playerMark);
                return;
            case 4:
                field.fillInSubfield(1, 0, playerMark);
                return;
            case 5:
                field.fillInSubfield(1, 1, playerMark);
                return;
            case 6:
                field.fillInSubfield(1, 2, playerMark);
                return;
            case 7:
                field.fillInSubfield(2, 0, playerMark);
                return;
            case 8:
                field.fillInSubfield(2, 1, playerMark);
                return;
            case 9:
                field.fillInSubfield(2, 2, playerMark);
                return;
            default:
                throw new SubfieldNumberInvalidException("В это поле нельзя сделать ход!\n");
        }
    }
    
}