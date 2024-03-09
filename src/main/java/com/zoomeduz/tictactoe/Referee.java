package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
class Referee {
    
    private static char winnerMark;
    private static int markCount;

    static boolean hasWin(Field2D field, int winningCombinationLength) {
        int numberOfRows = field.getNumberOfRows();
        int numberOfColumns = field.getNumberOfColumns();
        CellPosition refPosition = field.getLastFilledCell();
        char mark = field.getValue(refPosition);
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
                winnerMark = mark;
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
                winnerMark = mark;
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
                winnerMark = mark;
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
                winnerMark = mark;
                return true;
            }
        }
        
        //переход от проверки столбца к диагонали: \
        resetMarkCount();
        
        //проверка диагонали: \ вниз и вправо от refPosition
        for(int r = refPosition.row+1, c = refPosition.column+1; r < numberOfRows && c < numberOfColumns; r++, c++) {
            if(field.getValue(r, c) == mark) {
                markCount++;
            } else {
                resetMarkCount();
                break;
            }
            
            if (markCount == winningCombinationLength) {
                winnerMark = mark;
                return true;
            }
        }
        
        //проверка диагонали: \ вверх и влево от refPosition
        for(int r = refPosition.row-1, c = refPosition.column-1; r >= 0 && c >= 0; r--, c--) {
            if(field.getValue(r, c) == mark) {
                markCount++;
            } else {
                resetMarkCount();
                break;
            }
            
            if (markCount == winningCombinationLength) {
                winnerMark = mark;
                return true;
            }
        } 
        
        //переход от проверки диагонали от \ к /
        resetMarkCount();
        
        //проверка диагонали: / вниз и влево от refPosition
        for(int r = refPosition.row+1, c = refPosition.column-1; r < numberOfRows && c >=0; r++, c--) {
            if(field.getValue(r, c) == mark) {
                markCount++;
            } else {
                resetMarkCount();
                break;
            }
            
            if (markCount == winningCombinationLength) {
                winnerMark = mark;
                return true;
            }
        }
        
        //проверка диагонали: / вверх и вправо от refPosition
        for(int r = refPosition.row-1, c = refPosition.column+1; r >= 0 && c < numberOfColumns; r--, c++) {
            if(field.getValue(r, c) == mark) {
                markCount++;
            } else {
                resetMarkCount();
                break;
            }
            
            if (markCount == winningCombinationLength) {
                winnerMark = mark;
                return true;
            }
        }
        
        resetMarkCount();
        
        return false;
    }
    
    static char getWinnerMark() {
        return winnerMark;
    }
    
    private static void resetMarkCount() {
        markCount = 1;
    }
}