package com.zoomeduz.tictactoe;

import java.util.Set;

/**
 *
 * @author zoomeduz
 */
class ConsoleUI implements IGameUI {

    private final IConsoleIO io;
    private IFieldViewer field;
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
                    "     |     |     " ,
                    ""
            ).replace("?", "%s");

    ConsoleUI(IConsoleIO io) {
        this.io = io;
    }

    @Override
    public void displayGameStart() {
        io.outputText("Давайте сыграем в 'Крестики-нолики'!");
    }

    @Override
    public int getConsolePlayerMove(IFieldViewer fv, Mark mark) {
        final int MIN_CELL_INDEX = 1;
        final int MAX_CELL_INDEX = 9;
        final int NUM_FOR_DISPLAY_HELP = 0;
        int cellIndex;

        io.clear();
        while(true) {
            displayMove(fv, mark);
            io.outputText(String.format("Выберите: %d - %d, %d - показать номера ячеек\n"
                , MIN_CELL_INDEX
                , MAX_CELL_INDEX
                , NUM_FOR_DISPLAY_HELP));
            try {
                cellIndex = io.getInt();
            } catch(Exception e) {
                io.outputText(String.format("Некорректный знак! Нужно выбрать номер ячейки от %d до %d.\n"
                    , MIN_CELL_INDEX
                    , MAX_CELL_INDEX));
                io.outputText(e + "\n");
                continue;
            }
            io.outputText("");

            if (cellIndex == NUM_FOR_DISPLAY_HELP) {
                displayHelp();
                continue;
            }
            if (cellIndex < MIN_CELL_INDEX || cellIndex > MAX_CELL_INDEX) {
                io.outputText("Выбранной ячейки нет на поле\n");
                continue;
            }
            cellIndex--; //-1, т.к. ячейки в поле нумируются с 0
            Set<Integer> availCellIndexes = Core.getAvailCellIndexes(fv);
            if (!availCellIndexes.contains(cellIndex)) {
                io.outputText("Выбранная ячейка уже заполнена\n");
                continue;
            }
            return cellIndex;
        }
    }

    @Override
    public Mark getMarkForPlayer() {
        final int NUMBER_FOR_X = 1;
        final int NUMBER_FOR_O = 2;
        int inputEnteredByPlayer;

        while(true) {
            io.outputText("Каким знаком будете играть?");
            io.outputText(String.format("%d. %s", NUMBER_FOR_X, Mark.X));
            io.outputText(String.format("%d. %s", NUMBER_FOR_O, Mark.O));
            io.outputText("Введите нужное число:");
            try {
                inputEnteredByPlayer = io.getInt();
            } catch(Exception e) {
                io.outputText(String.format("\nНекорректный знак! Нужно выбрать либо '%d' либо '%d'.\n"
                    , NUMBER_FOR_X
                    , NUMBER_FOR_O));
                io.outputText(e + "\n");
                continue;
            }
            io.outputText("");

            switch (inputEnteredByPlayer) {
                case NUMBER_FOR_X -> { return Mark.X; }
                case NUMBER_FOR_O -> { return Mark.O; }
                default -> {
                    io.outputText(String.format("Нужно выбрать либо '%d' либо '%d'.\n"
                        , NUMBER_FOR_X
                        , NUMBER_FOR_O));
                }
            }
        }
    }

    @Override
    public void displayNonConsolePlayerMove(IFieldViewer fv, Mark mark) {
        io.clear();
        displayMove(fv, mark);
    }

    @Override
    public void displayHelp() {
        io.clear();
        io.outputText("Сетка игры выглядит следующим образом:");
        io.outputText(String.format(fieldTemplate
            , 1, 2, 3
            , 4, 5, 6
            , 7, 8, 9));
        io.outputText("Просьба указывать номер ячейки, куда хотите сделать свой ход.\n");
        askForContinue();
    }

    @Override
    public void displayRoundResult(GameRoundResult grr) {
        io.clear();
        io.outputText("Игра завершена:\n");
        displayField(grr.getResultField());

        if (grr.hasWinner()) {
            io.outputText("Выиграл " + grr.getWinner());
        } else {
            io.outputText("Ничья");
        }
    }

    private void displayMove(IFieldViewer fv, Mark mark) {
        io.outputText(String.format("Сейчас ход %s:\n", mark));
        displayField(fv);
    }

    private void displayField(IFieldViewer fv) {
        field = fv;
        io.outputText(String.format(fieldTemplate
            , getMark(0), getMark(1), getMark(2)
            , getMark(3), getMark(4), getMark(5)
            , getMark(6), getMark(7), getMark(8)));
    }

    private String getMark(int cellIndex) {
        Mark m = field.get(cellIndex);
        return m == null ? " " : m.toString();
    }

    private void askForContinue() {
        io.outputText("Нажмите Enter чтобы продолжить...");
        io.getString();
    }

}
