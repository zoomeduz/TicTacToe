package com.zoomeduz.tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author zoomeduz
 */
class ConsoleIO implements IO {
    
    Scanner input;
	ConsoleIO() {
		input = new Scanner(System.in);
	}

    @Override
    public void outputText(String text) {
        System.out.println(text);
    }

    @Override
    public int getUserInt() {
        return getUserInt("");
    }

    @Override
    public int getUserInt(String textDescription) throws InputMismatchException {
        int result;
		if(!textDescription.equals("")) {
			System.out.println(textDescription);
		}
        try {
            result = input.nextInt();
            input.nextLine(); // Удаление \n из ввода
        } catch(InputMismatchException e) {
        	input.next();
            throw e;
        }
        return result;
    }

    @Override
    public String getUserString() {
        return getUserString("");
    }

    @Override
    public String getUserString(String textDescription) throws InputMismatchException {
        String result = "";
		if(!textDescription.equals("")) {
			System.out.println(textDescription);
		}
        try {
        	if(input.hasNextLine()) {
        		result = input.nextLine();
            }
        } catch(InputMismatchException e) {
        	input.next();
            throw e;
        }
        return result;
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
    @Override
    public void displayField(Field field) {
        if (field instanceof Field3x3) {
            displayField3x3((Field3x3)field);
        } else {
            System.out.println("Неподдерживаемый тип поля.");
        }
    }
    
    private void displayField3x3(Field3x3 field) {
        int k = 0;
        char[] allValuesFromField = new char[field.getNumberOfColumns()*field.getNumberOfRows()];
        
        for (int r = 0; r < field.getNumberOfRows(); r++) {
            for (int c = 0; c < field.getNumberOfColumns(); c++) {
                allValuesFromField[k] = field.getValue(r, c);
                k++;
            }
        }
        
        k = 0;
        int numberOfDisplayedRows = 9;
        int numberOfDisplayedColumns = 17;
        for (int i = 0; i < numberOfDisplayedRows; i++) {
            for (int j = 0; j < numberOfDisplayedColumns; j++) {
                if (i % 3 == 1 && j % 6 == 2) {
                    System.out.print(allValuesFromField[k++]);
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

    @Override
    public void displayFieldWithSubfieldNumbers() {
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
}
