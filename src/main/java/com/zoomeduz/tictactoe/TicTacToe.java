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

    enum Move {
        PLAYER, COMPUTER
    };

//    Move changeMove(Move move) {
//        switch(move) {
//            case PLAYER: return Move.COMPUTER;
//            case COMPUTER: return Move.PLAYER;
//        }
//        return null;
//    }

    public static void main(String[] args){
        char playerMark;
        char computerMark;
        String inputEnteredByPlayer;
        int subfieldNumber = -1;
        Move move;

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
        Field field = new Field();

        System.out.println("Сетка игры выглядит следующим образом. \nПросьба указывать номер поля, куда хотите сделать свой ход.\n");
        field.displayFieldWithSubfieldNumbers();

        boolean hasWin = false;
        int currentEmptyFields = field.getNumberOfEmptyFields();

        while(!hasWin && currentEmptyFields != 0) {
            field.displayCurrentField();
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
                    move = Move.COMPUTER;
                    break;
                case COMPUTER:
                    System.out.println("Ход противника\n");
                    try {
                        field.fillInSubfield(AI.makeMove(field.getSimpleCurrentField(), computerMark), computerMark);
                    } catch(SubfieldNumberInvalidException e) {
                        System.out.println(e);
                        continue; //тут надо завершать игру, т.к. COMPUTER в неадеквате
                    }
                    move = Move.PLAYER;
                    break;
            }
            //проверка на наличие победы
            break;
        }
        in.close();
    }
}

class Field {
    
    private static final int DEFAULT_NUMBER_OF_FIELDS = 9;
    private int numberOfEmptyFields;
    private String[][] currentField;
    private char[] simpleField;
    
    Field() {
        numberOfEmptyFields = DEFAULT_NUMBER_OF_FIELDS;
        currentField = new String[9][17];
        simpleField = new char[DEFAULT_NUMBER_OF_FIELDS];
        
        for (int i = 0; i < currentField.length; i++) {
            for (int j = 0; j < currentField[i].length; j++) {
                if (j == 5 || j == 11)
                    currentField[i][j] = "|";
                else if (i == 2 || i == 5)
                    currentField[i][j] = "_";
                else
                    currentField[i][j] = " ";
            }
        }
    }
    
    int getNumberOfEmptyFields() {
        return numberOfEmptyFields;
    }
    
    char[] getSimpleCurrentField() {
        return simpleField;
    }
    
    void displayCurrentField() {
        //надо сделать отображение simpleField в currentField
        for (int i = 0; i < currentField.length; i++) {
            for (int j = 0; j < currentField[i].length; j++) {
                System.out.print(currentField[i][j]);
            }
            System.out.print("\n");
        }
        System.out.println("");
    }

    void displayFieldWithSubfieldNumbers() {
        System.out.println("     |     |     ");
        System.out.println("  1  |  2  |  3  ");
        System.out.println("_____|_____|_____");
        System.out.println("     |     |     ");
        System.out.println("  4  |  5  |  6  ");
        System.out.println("_____|_____|_____");
        System.out.println("     |     |     ");
        System.out.println("  7  |  8  |  9  ");
        System.out.println("     |     |     ");
        System.out.println("");
    }
    
    void fillInSubfield(int subfieldNumber, char mark) throws SubfieldNumberInvalidException {
        //надо корректней сделать проверку, обработать ситуацию, если subfieldNumber вылезет за пределы массива
        //надо убедиться, что пол было пустым
        if (subfieldNumber > DEFAULT_NUMBER_OF_FIELDS || subfieldNumber <= 0){
            throw new SubfieldNumberInvalidException("В это поле нельзя сделать ход!\n");
        }
        simpleField[subfieldNumber] = mark;
    }
}

class AI {
    static int makeMove(char[] field, char mark) {
        return 0; //пока так, да..
    }
}

class SubfieldNumberInvalidException extends Exception {
	private static final long serialVersionUID = 4805057172244117284L;

	public SubfieldNumberInvalidException(String message){
        super(message);
    }
}
