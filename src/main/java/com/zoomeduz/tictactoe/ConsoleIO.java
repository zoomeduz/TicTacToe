package com.zoomeduz.tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author zoomeduz
 */
class ConsoleIO implements IConsoleIO {

    private final Scanner input;

    ConsoleIO() {
        input = new Scanner(System.in);
    }

    @Override
    public void outputText(String text) {
        System.out.println(text);
    }

    @Override
    public int getInt() {
        String in = getString();
        return Integer.parseInt(in);
    }

    @Override
    public String getString() {
        String result = "";
        try {
            if(input.hasNextLine()) {
                result = input.nextLine();
            }
        } catch(InputMismatchException e) {
            throw e;
        }
        return result;
    }

    @Override
    public void clear() {
        System.out.printf("\033[2J"); //ANSI ESC SEQUENCE - erase entire screen
    }

}
