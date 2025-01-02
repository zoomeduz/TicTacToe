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
        input = new Scanner(System.in).useDelimiter("\\s");
    }

    @Override
    public void outputText(String text) {
        System.out.println(text);
    }

    @Override
    public int getInt() {
        int result;
        try {
            result = input.nextInt();
        } catch(InputMismatchException e) {
            input.next();
            throw e;
        }
        return result;
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
