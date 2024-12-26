package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
//Стоит переименовать в ConsolePlayer3х3?
public class ConsolePlayer implements Player {

    FieldViewer field;
    IO io;

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
        io.outputText(grr.toString());
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
        if (mr != null && mr != MoveResult.SUCCESSFUL) {
            io.outputText(mr.toString());
            io.outputText("");
        }
    }

    private void displayField() {
        io.outputText("     |     |     ");
        io.outputText(String.format("  %s  |  %s  |  %s  ", getMark(0), getMark(1), getMark(2)));
        io.outputText("_____|_____|_____");
        io.outputText("     |     |     ");
        io.outputText(String.format("  %s  |  %s  |  %s  ", getMark(3), getMark(4), getMark(5)));
        io.outputText("_____|_____|_____");
        io.outputText("     |     |     ");
        io.outputText(String.format("  %s  |  %s  |  %s  ", getMark(6), getMark(7), getMark(8)));
        io.outputText("     |     |     ");
        io.outputText("");
    }

    private void displayFieldWithCellIndexes() {
        //оставлен такой вывод, для наглядности
        io.outputText("     |     |     ");
        io.outputText("  1  |  2  |  3  ");
        io.outputText("_____|_____|_____");
        io.outputText("     |     |     ");
        io.outputText("  4  |  5  |  6  ");
        io.outputText("_____|_____|_____");
        io.outputText("     |     |     ");
        io.outputText("  7  |  8  |  9  ");
        io.outputText("     |     |     ");
        io.outputText("");
    }

    private String getMark(int cellIndex) {
        Mark m = field.get(cellIndex);
        return m == null? " ": m.toString();
    }

}
