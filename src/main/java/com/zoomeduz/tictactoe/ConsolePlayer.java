package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
public class ConsolePlayer implements Player {

    private FieldViewer field;
    private final IO io;
    private final String fieldTemplate =
                String.join("\n",
                    "     |     |     " ,
                    "  ?  |  ?  |  ?  " ,
                    "_____|_____|_____" ,
                    "     |     |     " ,
                    "  ?  |  ?  |  ?  " ,
                    "_____|_____|_____" ,
                    "     |     |     " ,
                    "  ?  |  ?  |  ?  " ,
                    "     |     |     "
            ).replace("?", "%s");;

    ConsolePlayer(IO io) {
        this.io = io;
    }

    @Override
    public void onGameStarted(FieldViewer fv) {
        field = fv;
        io.outputText("Сетка игры выглядит следующим образом. \nПросьба указывать номер поля, куда хотите сделать свой ход.\n");
        displayFieldWithCellIndexes();
    }

    @Override
    public void onGameFinished(GameRoundResult grr) {
        displayField();
        switch(grr) {
            case WIN_X:
                io.outputText("Выиграли X");
                break;
            case WIN_O:
                io.outputText("Выиграли O");
                break;
            case DRAW:
                io.outputText("Ничья");
                break;
        }
    }

    @Override
    public int getMove() {
        displayField();
        int cellIndex;

        while(true) {
            try {
                io.outputText("Ваш ход (1-9, 0 - показать номера полей)\n\n");
                cellIndex = io.getInt();
                io.outputText("");
            } catch(Exception e) {
                io.outputText("Некорректный знак! Нужно выбрать либо номер поля от 1 до 9.\n" + e + "\n");
                continue;
            }
            if (cellIndex == 0) {
                displayFieldWithCellIndexes();
                continue;
            }
            return cellIndex - 1; //-1, т.к. ячейки в поле нумируются с 0
        }
    }

    @Override
    public void onMoveProcessed(MoveResult mr) {
        switch(mr) {
            case INDEX_OUT_OF_FIELD:
                io.outputText("Выбранной ячейки нет на поле");
                break;
            case INDEX_OF_FILLED_CELL:
                io.outputText("Выбранная ячейка уже заполнена");
                break;
        }
        io.outputText("");
    }

    private void displayField() {
        io.outputText(String.format(fieldTemplate,
            getMark(0), getMark(1), getMark(2),
            getMark(3), getMark(4), getMark(5),
            getMark(6), getMark(7), getMark(8)));
        io.outputText("");
    }

    private void displayFieldWithCellIndexes() {
        io.outputText(String.format(fieldTemplate,
            1, 2, 3,
            4, 5, 6,
            7, 8, 9));
        io.outputText("");
    }

    private String getMark(int cellIndex) {
        Mark m = field.get(cellIndex);
        return m == null? " " : m.toString();
    }

}
