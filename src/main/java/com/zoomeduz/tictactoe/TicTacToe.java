package com.zoomeduz.tictactoe;

import java.util.*;

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

        Scanner in = new Scanner(System.in);
        System.out.println("Давайте сыграем в 'Крестики-нолики'!");
        while(true) {
            System.out.println("Каким знаком будете играть? (" + MARK_X + " или " + MARK_O + ")");
            try {
                inputEnteredByPlayer = in.nextLine();
                System.out.println("");
            } catch(Exception e) {
                System.out.println("Некорректный знак! Нужно выбрать либо '" + MARK_X + "' либо '" + MARK_O + "'.\n" + e + "\n");
                in.next();
                continue;
            }
            if (!inputEnteredByPlayer.equalsIgnoreCase(String.valueOf(MARK_X)) && !inputEnteredByPlayer.equalsIgnoreCase(String.valueOf(MARK_O))) {
                System.out.println("Нужно выбрать либо '" + MARK_X + "' либо '" + MARK_O + "'.");
                continue;
            }
            playerMark = inputEnteredByPlayer.toUpperCase().charAt(0);
            break;
        }
        if (playerMark == MARK_X) {
            System.out.println("Первым будете ходить вы.\n");
            computerMark = MARK_O;
            move = Move.PLAYER;
        } else {
            System.out.println("Первым будет ходить компьютер.\n");
            computerMark = MARK_X;
            move = Move.COMPUTER;
        }
        Field3x3 field = new Field3x3();

        System.out.println("Сетка игры выглядит следующим образом. \nПросьба указывать номер поля, куда хотите сделать свой ход.\n");
        field.displayFieldWithSubfieldNumbers();

        boolean hasWin = false;
        AI computer = new AI(FIELD_SIZE, FIELD_SIZE, 3, MARK_X, MARK_O);
        
        field.displayCurrentField();
        while(!hasWin && field.getCurrentNumberOfEmptyFields() != 0) {
            switch(move) {
                case PLAYER:
                    System.out.println("Ваш ход (1-9, 0 - показать номера полей)\n");
                    try {
                        subfieldNumber = in.nextInt();
                        System.out.println("");
                    } catch(Exception e) {
                        System.out.println("Некорректный знак! Нужно выбрать либо номер поля от 1 до 9.\n" + e + "\n");
                        in.next();
                        continue;
                    }
                    if (subfieldNumber == 0) {
                        field.displayFieldWithSubfieldNumbers();
                        continue;
                    }
                    try {
                        field.fillInSubfield(subfieldNumber, playerMark);
                    } catch(SubfieldNumberInvalidException e) {
                        System.out.println(e);
                        continue;
                    }
                    break;
                case COMPUTER:
                    System.out.println("Ход противника\n");
                    try {
                        field.fillInSubfield(computer.makeMove(field.getCurrentField()), computerMark);
                    } catch(SubfieldNumberInvalidException e) {
                        System.out.println(e);
                        break; //тут надо завершать игру, т.к. COMPUTER в неадеквате
                    }
                    break;
            }
            field.displayCurrentField();
            
            if(computer.hasWin(field.getCurrentField())) {
                hasWin = true;
                winner = move;
            } else {
                move = changeMove(move);
            }
        }
        if (winner == null) {
            System.out.println("Ничья!");
        } else {
            System.out.println("Победитель: " + winner + "!");
        }
        
        in.close();
    }
}

class SubfieldNumberInvalidException extends Exception {
	private static final long serialVersionUID = 4805057172244117284L;

	public SubfieldNumberInvalidException(String message){
        super(message);
    }
}
