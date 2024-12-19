package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
interface IO {
    
    void outputText(String text);
	
    int getUserInt();
    
    int getUserInt(String textDescription);
	
    String getUserString();
	
    String getUserString(String textDescription);
    
    void displayField(Field2D field);
    
    void displayFieldWithCellNumbers();
}
