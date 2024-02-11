package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
class Referee {
    
    private static char winnerMark;
    private static int markCount;

    static boolean hasWin(Field field, int winningCombinationLength) {
        int numberOfRows = field.getNumberOfRows();
        int numberOfColumns = field.getNumberOfColumns();
        CellPosition refPosition = field.getLastFilledSubfield();
        char mark = field.getValue(refPosition.row, refPosition.column);
        char winnerMark = ' ';
        resetMarkCount(); //типа init
        
        //проверка строки вправо от refPosition
        for(int c = refPosition.column+1; c < numberOfColumns; c++) {
            if(field.getValue(refPosition.row, c) == mark) {
                markCount++;
            } else {
                resetMarkCount();
                break;
            }
            
            if (markCount == winningCombinationLength) {
                return true;
            }
        }
        
        //проверка строки влево от refPosition
        for(int c = refPosition.column-1; c >= 0; c--) {
            if(field.getValue(refPosition.row, c) == mark) {
                markCount++;
            } else {
                resetMarkCount();
                break;
            }
            
            if (markCount == winningCombinationLength) {
                return true;
            }
        }
        
        //переход от проверки строки к столбцу
        resetMarkCount();
        
        //проверка столбца вниз от refPosition
        for(int r = refPosition.row+1; r < numberOfRows; r++) {
            if(field.getValue(r, refPosition.column) == mark) {
                markCount++;
            } else {
                resetMarkCount();
                break;
            }
            
            if (markCount == winningCombinationLength) {
                return true;
            }
        }
        
        //проверка столбца вверх от refPosition
        for(int r = refPosition.row-1; r >= 0; r--) {
            if(field.getValue(r, refPosition.column) == mark) {
                markCount++;
            } else {
                resetMarkCount();
                break;
            }
            
            if (markCount == winningCombinationLength) {
                return true;
            }
        }
        
        //переход от проверки столбца к диагонали: \
        resetMarkCount();
        
        //проверка диагонали: \ вниз и вправо от refPosition
        checkDiagonalDR:
        for(int r = refPosition.row+1; r < numberOfRows; r++) {
            for (int c = refPosition.column+1; c < numberOfColumns; c++) {
                //System.out.println("Проверка (" + r + ", " + c + "): " + field.getValue(r, c) + " счет: " + markCount);
                if(field.getValue(r, c) == mark) {
                    markCount++;
                } else {
                    resetMarkCount();
                    break checkDiagonalDR;
                }
            
                if (markCount == winningCombinationLength) {
                    return true;
                }
            }
        }
        
        //проверка диагонали: \ вверх и влево от refPosition
        checkDiagonalUL:
        for(int r = refPosition.row-1; r >= 0; r--) {
            for (int c = refPosition.column-1; c >= 0; c--) {
                //System.out.println("Проверка (" + r + ", " + c + "): " + field.getValue(r, c) + " счет: " + markCount);
                if(field.getValue(r, c) == mark) {
                    markCount++;
                } else {
                    resetMarkCount();
                    break checkDiagonalUL;
                }
            
                if (markCount == winningCombinationLength) {
                    return true;
                }
            }
        } 
        
        //переход от проверки диагонали от \ к /
        resetMarkCount();
        
        //проверка диагонали: / вниз и влево от refPosition
        checkDiagonalDL:
        for(int r = refPosition.row+1; r < numberOfRows; r++) {
            for (int c = refPosition.column-1; c >=0 ; c--) {
                if(field.getValue(r, c) == mark) {
                    markCount++;
                } else {
                    resetMarkCount();
                    break checkDiagonalDL;
                }
            
                if (markCount == winningCombinationLength) {
                    return true;
                }
            }
        }
        
        //проверка диагонали: / вверх и вправо от refPosition
        checkDiagonalUR:
        for(int r = refPosition.row-1; r >= 0; r--) {
            for (int c = refPosition.column+1; c < numberOfColumns; c++) {
                if(field.getValue(r, c) == mark) {
                    markCount++;
                } else {
                    resetMarkCount();
                    break checkDiagonalUR;
                }
            
                if (markCount == winningCombinationLength) {
                    return true;
                }
            }
        }
        
        resetMarkCount();
        
        return false;
    }
    
    //может и не надо
    static char getWinnerMark() {
        return winnerMark;
    }
    
    private static void resetMarkCount() {
        markCount = 1;
    }
//    
//    private static boolean checkRowForWin() {
//        return false;
//    }
    
//    //пока Diagonal1 = \
//    private static boolean checkDiagonal1ForWin() {
//        return false;
//    }
//    
//    //пока Diagonal2 = /
//    private static boolean checkDiagonal2ForWin() {
//        return false;
//    }
}