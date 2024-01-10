package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
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
                if (i % 3 == 1 && j % 6 == 2) {
                    System.out.print(simpleField[k++]);
                } else if (j == 5 || j == 11) {
                    System.out.print("|");
                } else if (i == 2 || i == 5) {
                    System.out.print("_");
                } else {
                    System.out.print(" ");
                }
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
            if (simpleField[subfieldNumber] != EMPTY_VALUE) {
                throw new SubfieldNumberInvalidException("В это поле нельзя сделать ход!\n");
            }
            simpleField[subfieldNumber] = mark;
        } catch (Exception e) {
            throw new SubfieldNumberInvalidException("В это поле нельзя сделать ход!\n");
        }
        currentNumberOfEmptyFields--;
    }
    
}
