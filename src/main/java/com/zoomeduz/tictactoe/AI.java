package com.zoomeduz.tictactoe;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author zoomeduz
 */
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
            if (field[i] == ' ') {
                //надо отвязаться от этого пробела, либо надо использовать массив строк
                emptySubfields.add(i + 1); //+1 т.к. играем со сзначениями полей от 1 до 9
            }
        }
        Random rn = new Random();
        int minimum = 0;
        int maximum = emptySubfields.size() - 1;
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
        for (int i = 0; i < numberOfRows * numberOfColumns; i += numberOfRows) {
            for (int j = 0; j < numberOfColumns; j++) {
                index = i + j;
                if (field[index] == mark_X) {
                    markCountX++;
                } else if (field[index] == mark_O) {
                    markCountO++;
                }
            }
            if (markCountO >= numberOfMarksToWin || markCountX >= numberOfMarksToWin) {
                return true;
            }
            markCountO = 0;
            markCountX = 0;
        }
        //проверяем все столбцы поля
        for (int i = 0; i < numberOfColumns; i++) {
            for (int j = 0; j < numberOfRows * numberOfColumns; j += numberOfRows) {
                index = i + j;
                if (field[index] == mark_X) {
                    markCountX++;
                } else if (field[index] == mark_O) {
                    markCountO++;
                }
            }
            if (markCountO >= numberOfMarksToWin || markCountX >= numberOfMarksToWin) {
                return true;
            }
            markCountO = 0;
            markCountX = 0;
        }
        //проверка диагонали поля: /
        for (int d = 1; d <= numberOfRows; d++) {
            index = (numberOfRows - 1) * d;
            if (field[index] == mark_X) {
                markCountX++;
            } else if (field[index] == mark_O) {
                markCountO++;
            }
        }
        if (markCountO >= numberOfMarksToWin || markCountX >= numberOfMarksToWin) {
            return true;
        }
        markCountO = 0;
        markCountX = 0;
        //проверка диагонали поля: \
        for (int d = 0; d < numberOfColumns; d++) {
            index = (numberOfRows * numberOfColumns - 1) / (numberOfColumns - 1) * d;
            if (field[index] == mark_X) {
                markCountX++;
            } else if (field[index] == mark_O) {
                markCountO++;
            }
        }
        if (markCountO >= numberOfMarksToWin || markCountX >= numberOfMarksToWin) {
            return true;
        }
        return false;
    }
    
}
