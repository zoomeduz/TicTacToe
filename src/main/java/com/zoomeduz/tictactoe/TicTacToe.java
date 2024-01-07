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

class Field3x3 {
    
    private static final int NUMBER_OF_FIELDS = 9;
    private static final char EMPTY_VALUE = ' ';
    private int currentNumberOfEmptyFields;
    private char[] simpleField;
    
    Field3x3() {
        currentNumberOfEmptyFields = NUMBER_OF_FIELDS;
        simpleField = new char[NUMBER_OF_FIELDS];

        for (int i = 0; i < simpleField.length; i++) {
            simpleField[i] = EMPTY_VALUE;
        }
    }
    
    int getCurrentNumberOfEmptyFields() {
        return currentNumberOfEmptyFields;
    }
    
    char[] getCurrentField() {
        return simpleField;
    }
    
    // Фукнция, которая в консоль выводит отображение текущего вида поля.
    // Формат вывода -- как в функции displayFieldWithSubfieldNumbers():
    //      |     |     
    //   1  |  2  |  3  
    // _____|_____|_____
    //      |     |     
    //   4  |  5  |  6  
    // _____|_____|_____
    //      |     |     
    //   7  |  8  |  9  
    //      |     |     
    //
    // где вместо цифр будет отображено либо заполненное значение, либо пусто
    void displayCurrentField() {
        int k = 0;
        int numberOfDisplayedRows = 9;
        int numberOfDisplayedColumns = 17;

        for (int i = 0; i < numberOfDisplayedRows; i++) {
            for (int j = 0; j < numberOfDisplayedColumns; j++) {
                if (i % 3 == 1 && j % 6 == 2)
                    System.out.print(simpleField[k++]);
                else if (j == 5 || j == 11)
                    System.out.print("|");
                else if (i == 2 || i == 5)
                    System.out.print("_");
                else
                    System.out.print(" ");
            }
            System.out.print("\n");
        }
        System.out.println(" ");
    }

    void displayFieldWithSubfieldNumbers() {
        //оставлен такой вывод, для наглядности
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
        subfieldNumber--; //-1 т.к. в массиве от 0 индексы
        try {
            if (simpleField[subfieldNumber] != EMPTY_VALUE){
                throw new SubfieldNumberInvalidException("В это поле нельзя сделать ход!\n");
            }
            simpleField[subfieldNumber] = mark;
        } catch(Exception e) {
            throw new SubfieldNumberInvalidException("В это поле нельзя сделать ход!\n");
        }
        currentNumberOfEmptyFields--;
    }
}

class AI {
    private int numberOfMarksToWin;
    private char mark_X;
    private char mark_O;
    private int numberOfRows;
    private int numberOfColumns;
    private char winnerMark;
    
    AI(int numberOfRows, int numberOfColumns, int numberOfMarksToWin, char mark_X, char mark_O) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.numberOfMarksToWin = numberOfMarksToWin;
        this.mark_X = mark_X;
        this.mark_O = mark_O;
        winnerMark = ' ';
    }
    
    //пока "AI" играет чисто рандомно, без анализа
    int makeMove(char[] field) {
        ArrayList<Integer> emptySubfields = new ArrayList<>();
        for (int i = 0; i < field.length; i++) {
            if (field[i] == ' ') { //надо отвязаться от этого пробела, либо надо использовать массив строк
                emptySubfields.add(i+1); //+1 т.к. играем со сзначениями полей от 1 до 9
            }
        }
        Random rn = new Random();
        int minimum = 0;
        int maximum = emptySubfields.size()-1;
        int index = rn.nextInt(maximum - minimum + 1) + minimum;
        return emptySubfields.get(index);
    }

    boolean hasWin(char[] field) {
        int markCountO = 0;
        int markCountX = 0;
        int index = 0;
        
        //по хорошему, надо искать именно "слипшихся" одинаковых меток, а не это всё, но для полей, 
        //где numberOfMarksToWin = numberOfRows = numberOfColumns - сойдет
        //а как диагонали искать в случаях, когда размер поля больше - хз
        //проверяем все строки поля
        for(int i = 0; i < numberOfRows*numberOfColumns; i += numberOfRows) {
            for (int j = 0; j < numberOfColumns; j++) {
                index = i+j;
                if (field[index] == mark_X)
                    markCountX++;
                else if (field[index] == mark_O)
                    markCountO++;
            }
            
            if (markCountO >= numberOfMarksToWin || markCountX >= numberOfMarksToWin) {
                return true;
            }
            markCountO = 0;
            markCountX = 0;
        }
        
        //проверяем все столбцы поля
        for(int i = 0; i < numberOfColumns; i++) {
            for (int j = 0; j < numberOfRows*numberOfColumns; j += numberOfRows) {
                index = i+j;
                if (field[index] == mark_X)
                    markCountX++;
                else if (field[index] == mark_O)
                    markCountO++;
            }

            if (markCountO >= numberOfMarksToWin || markCountX >= numberOfMarksToWin) {
                return true;
            }
            markCountO = 0;
            markCountX = 0;
        }
        
        //проверка диагонали поля: /
        for (int d = 1; d <= numberOfRows; d++) {
            index = (numberOfRows-1)*d;
            if (field[index] == mark_X)
                markCountX++;
            else if (field[index] == mark_O)
                markCountO++;
        }
        if (markCountO >= numberOfMarksToWin || markCountX >= numberOfMarksToWin) {
            return true;
        }
        markCountO = 0;
        markCountX = 0;
        
        //проверка диагонали поля: \
        for (int d = 0; d < numberOfColumns; d++) {
            index = (numberOfRows*numberOfColumns-1)/(numberOfColumns-1)*d;
            if (field[index] == mark_X)
                markCountX++;
            else if (field[index] == mark_O)
                markCountO++;
        }
        if (markCountO >= numberOfMarksToWin || markCountX >= numberOfMarksToWin) {
            return true;
        }
        return false;
    }
}

class SubfieldNumberInvalidException extends Exception {
	private static final long serialVersionUID = 4805057172244117284L;

	public SubfieldNumberInvalidException(String message){
        super(message);
    }
}
