package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
public enum MoveResult {

    SUCCESSFUL,
    INDEX_OUT_OF_FIELD,
    INDEX_OF_FILLED_CELL;

    @Override
    public String toString() {
        switch(this) {
            case SUCCESSFUL:            return "Выбранная ячейка успешно заполнена";
            case INDEX_OUT_OF_FIELD:    return "Выбранной ячейки нет на поле";
            case INDEX_OF_FILLED_CELL:  return "Выбранная ячейка уже заполнена";
            default:                    return "";
        }
    }

}
