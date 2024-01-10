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
        field.displayFieldWithSubfieldNumbers(); //надо заменить на ConsoleIO метод

        boolean hasWin = false;
        AI computer = new AI(FIELD_SIZE, FIELD_SIZE, 3, MARK_X, MARK_O);
        
        field.displayCurrentField();
        while(!hasWin && field.getCurrentNumberOfEmptyFields() != 0) {
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
                        field.displayFieldWithSubfieldNumbers(); //заменить
                        continue;
                    }
                    try {
                        field.fillInSubfield(subfieldNumber, playerMark);
                    } catch(SubfieldNumberInvalidException e) {
                        io.outputText(e.toString());
                        continue;
                    }
                    break;
                case COMPUTER:
                    io.outputText("Ход противника\n");
                    try {
                        field.fillInSubfield(computer.makeMove(field.getCurrentField()), computerMark);
                    } catch(SubfieldNumberInvalidException e) {
                        io.outputText(e.toString());
                        break; //тут надо завершать игру, т.к. COMPUTER в неадеквате
                    }
                    break;
            }
            field.displayCurrentField(); //заменить
            
            if(computer.hasWin(field.getCurrentField())) {
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
}

class SubfieldNumberInvalidException extends Exception {
	private static final long serialVersionUID = 4805057172244117284L;

	public SubfieldNumberInvalidException(String message){
        super(message);
    }
}