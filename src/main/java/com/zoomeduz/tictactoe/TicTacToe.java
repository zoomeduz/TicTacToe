package com.zoomeduz.tictactoe;

import java.util.*;

/*
 * Tic Tac Toe game
 *
 * @author zoomeduz
 */
public class TicTacToe {

    public static final int NUMBER_OF_FIELDS = 9;

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
        String playerMark = "";
        String computerMark = "";
        int subfieldNumber = -1;
        int fieldNumber = -1;
        Move move;
        Scanner in = new Scanner(System.in);
        System.out.println("Давайте сыграем в 'Крестики-нолики'!");
        while(true) {
            System.out.println("Каким знаком будете играть? (X или O)");
            try {
                playerMark = in.nextLine();
                System.out.println("");
            } catch(Exception e) {
                System.out.println("Некорректный знак! Нужно выбрать либо 'X' либо 'O'.\n" + e + "\n");
                in.next();
                continue;
            }
            if (!playerMark.equalsIgnoreCase("X") && !playerMark.equalsIgnoreCase("O")) {
                System.out.println("Нужно выбрать либо 'X' либо 'O'.");
                continue;
            }
            break;
        }
        if (playerMark.equalsIgnoreCase("X")) {
            System.out.println("Первым будете ходить вы.\n");
            playerMark = "X";
            computerMark = "O";
            move = Move.PLAYER;
        } else {
            System.out.println("Первым будет ходить компьютер.\n");
            playerMark = "O";
            computerMark = "X";
            move = Move.COMPUTER;
        }
        Field field = new Field();

        System.out.println("Сетка игры выглядит следующим образом. \nПросьба указывать номер поля, куда хотите сделать свой ход.\n");
        field.displayFieldWithSubfieldNumbers();

        boolean hasWin = false;
        int currentEmptyFields = NUMBER_OF_FIELDS;

        while(hasWin = false && currentEmptyFields != 0) {
            field.displayField();
            switch(move) {
                case PLAYER:
                    System.out.println("Ваш ход (1-9, 0 - показать подсказку)\n");
                    //получить инфу от игрока - какой ход
                    move = Move.COMPUTER;
                    break;
                case COMPUTER:
                    System.out.println("Ход противника\n");
                    //*компуктер делает ход*
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
    void displayField() {
        System.out.println("*Текущий вид поля*\n");
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
}
