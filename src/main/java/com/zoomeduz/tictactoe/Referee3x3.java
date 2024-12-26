package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
class Referee3x3 {

    private static Mark winnerMark;
    private static int markCount;
    private static final int NUMBER_OF_ROWS = 3;
    private static final int NUMBER_OF_COLUMNS = 3;
    private static final int WINNING_COMBINATION_LENGTH = 3;

    static boolean hasWin(FieldViewer field, int lastCellIndex) {
        int refRow = lastCellIndex / NUMBER_OF_COLUMNS;
        int refColumn = lastCellIndex % NUMBER_OF_COLUMNS;
        Mark mark = field.get(lastCellIndex);
        resetMarkCount(); //типа init
        
        //проверка строки вправо от lastCellIndex
        for(int c = refColumn + 1; c < NUMBER_OF_COLUMNS; c++) {
            if(field.get(refRow, c) == mark) {
                markCount++;
            } else {
                resetMarkCount();
                break;
            }
            
            if (markCount == WINNING_COMBINATION_LENGTH) {
                winnerMark = mark;
                return true;
            }
        }
        
        //проверка строки влево от lastCellIndex
        for(int c = refColumn - 1; c >= 0; c--) {
            if(field.get(refRow, c) == mark) {
                markCount++;
            } else {
                resetMarkCount();
                break;
            }
            
            if (markCount == WINNING_COMBINATION_LENGTH) {
                winnerMark = mark;
                return true;
            }
        }
        
        //переход от проверки строки к столбцу
        resetMarkCount();
        
        //проверка столбца вниз от lastCellIndex
        for(int r = refRow + 1; r < NUMBER_OF_ROWS; r++) {
            if(field.get(r, refColumn) == mark) {
                markCount++;
            } else {
                resetMarkCount();
                break;
            }
            
            if (markCount == WINNING_COMBINATION_LENGTH) {
                winnerMark = mark;
                return true;
            }
        }
        
        //проверка столбца вверх от lastCellIndex
        for(int r = refRow - 1; r >= 0; r--) {
            if(field.get(r, refColumn) == mark) {
                markCount++;
            } else {
                resetMarkCount();
                break;
            }
            
            if (markCount == WINNING_COMBINATION_LENGTH) {
                winnerMark = mark;
                return true;
            }
        }
        
        //переход от проверки столбца к диагонали: \
        resetMarkCount();
        
        //проверка диагонали: \ вниз и вправо от lastCellIndex
        for(int r = refRow + 1, c = refColumn + 1; r < NUMBER_OF_ROWS && c < NUMBER_OF_COLUMNS; r++, c++) {
            if(field.get(r, c) == mark) {
                markCount++;
            } else {
                resetMarkCount();
                break;
            }
            
            if (markCount == WINNING_COMBINATION_LENGTH) {
                winnerMark = mark;
                return true;
            }
        }
        
        //проверка диагонали: \ вверх и влево от lastCellIndex
        for(int r = refRow - 1, c = refColumn - 1; r >= 0 && c >= 0; r--, c--) {
            if(field.get(r, c) == mark) {
                markCount++;
            } else {
                resetMarkCount();
                break;
            }
            
            if (markCount == WINNING_COMBINATION_LENGTH) {
                winnerMark = mark;
                return true;
            }
        } 
        
        //переход от проверки диагонали от \ к /
        resetMarkCount();
        
        //проверка диагонали: / вниз и влево от lastCellIndex
        for(int r = refRow + 1, c = refColumn - 1; r < NUMBER_OF_ROWS && c >=0; r++, c--) {
            if(field.get(r, c) == mark) {
                markCount++;
            } else {
                resetMarkCount();
                break;
            }
            
            if (markCount == WINNING_COMBINATION_LENGTH) {
                winnerMark = mark;
                return true;
            }
        }
        
        //проверка диагонали: / вверх и вправо от lastCellIndex
        for(int r = refRow - 1, c = refColumn + 1; r >= 0 && c < NUMBER_OF_COLUMNS; r--, c++) {
            if(field.get(r, c) == mark) {
                markCount++;
            } else {
                resetMarkCount();
                break;
            }
            
            if (markCount == WINNING_COMBINATION_LENGTH) {
                winnerMark = mark;
                return true;
            }
        }
        
        resetMarkCount();
        
        return false;
    }
    
    static Mark getWinnerMark() {
        return winnerMark;
    }
    
    private static void resetMarkCount() {
        markCount = 1;
    }

}