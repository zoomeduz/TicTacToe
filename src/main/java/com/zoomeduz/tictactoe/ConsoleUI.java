package com.zoomeduz.tictactoe;

import java.util.List;

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
                    "     |     |     "
            ).replace("?", "%s");

    ConsoleUI(IConsoleIO io) {
        this.io = io;
    }

    @Override
    public void displayGameStart() {
        io.outputText("Давайте сыграем в 'Крестики-нолики'!");
    }

    @Override
    public int getCellIndex(IFieldViewer fv) {//getConsolePlayerMove?
        final int MIN_CELL_INDEX = 1;
        final int MAX_CELL_INDEX = 9;
        final int DISPLAY_HELP   = 0;
        int cellIndex;

        while(true) {
            try {
                io.outputText(String.format("Выберите: %d - %d, %d - показать номера ячеек\n"
                    , MIN_CELL_INDEX
                    , MAX_CELL_INDEX
                    , DISPLAY_HELP));
                cellIndex = io.getInt();
                io.outputText("");
            } catch(Exception e) {
                io.outputText(String.format("Некорректный знак! Нужно выбрать номер ячейки от %d до %d.\n"
                    , MIN_CELL_INDEX
                    , MAX_CELL_INDEX));
                io.outputText(e + "\n");
                continue;
            }
            if (cellIndex == DISPLAY_HELP) {
                displayHelp();
                io.clear();
                displayField(fv);
                continue;
            }
            if (cellIndex < MIN_CELL_INDEX || cellIndex > MAX_CELL_INDEX) {
                io.outputText("Выбранной ячейки нет на поле\n");
                continue;
            }
            cellIndex--; //-1, т.к. ячейки в поле нумируются с 0
            List<Integer> availCellIndexes = Core.getAvailCellIndexes(fv);
            if (!availCellIndexes.contains(cellIndex)) {
                io.outputText("Выбранная ячейка уже заполнена\n");
                continue;
            }
            return cellIndex;
        }
    }

    @Override
    public Mark getMarkForPlayer() {
        String inputEnteredByPlayer;

        while(true) {
            try {
                io.outputText(String.format("Каким знаком будете играть? (%s или %s)"
                    , Mark.X
                    , Mark.O));
                inputEnteredByPlayer = io.getString();
                io.outputText("");
            } catch(Exception e) {
                io.outputText(String.format("Некорректный знак! Нужно выбрать либо '%s' либо '%s'.\n"
                    , Mark.X
                    , Mark.O));
                io.outputText(e + "\n");
                continue;
            }
            if (inputEnteredByPlayer.equalsIgnoreCase(Mark.X.toString())) {
                return Mark.X;
            }
            if (inputEnteredByPlayer.equalsIgnoreCase(Mark.O.toString())) {
                return Mark.O;
            }
            io.outputText(String.format("Нужно выбрать либо '%s' либо '%s'.\n"
                , Mark.X
                , Mark.O));
        }
    }

    @Override
    public void displayField(IFieldViewer fv) {
        field = fv;
        io.outputText(String.format(fieldTemplate
            , getMark(0), getMark(1), getMark(2)
            , getMark(3), getMark(4), getMark(5)
            , getMark(6), getMark(7), getMark(8)));
        io.outputText("");
    }

    @Override
    public void displayHelp() {
        io.clear();
        io.outputText("Сетка игры выглядит следующим образом:");
        io.outputText(String.format(fieldTemplate
            , 1, 2, 3
            , 4, 5, 6
            , 7, 8, 9));
        io.outputText("");
        io.outputText("Просьба указывать номер ячейки, куда хотите сделать свой ход.\n");
        askForContinue();
    }

    @Override
    public void displayMove(IFieldViewer fv, Mark mark) {
        io.clear();
        io.outputText(String.format("Сейчас ход %s:\n", mark));
        displayField(fv);
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

    private String getMark(int cellIndex) {
        Mark m = field.get(cellIndex);
        return m == null ? " " : m.toString();
    }

    private void askForContinue() {
        io.outputText("Нажмите Enter чтобы продолжить...");
        io.getString();
    }

}
