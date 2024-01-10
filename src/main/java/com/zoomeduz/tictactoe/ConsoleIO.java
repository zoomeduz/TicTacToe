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
}
