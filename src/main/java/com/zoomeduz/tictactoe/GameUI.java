package com.zoomeduz.tictactoe;

import java.util.Set;

/**
 *
 * @author zoomeduz
 */
class GameUI implements IGameUI {
    
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
    
    GameUI(IConsoleIO io) {
        this.io = io;
    }
    
    @Override
    public void displayGameStart() {
        io.outputText("Давайте сыграем в 'Крестики-нолики'!");
    }

    @Override
    public int getCellIndex(IFieldViewer fv, Set<Integer> availCellIndexes) {
        int cellIndex;
        //вынести 1, 9, 0 в константы?
        
        while(true) {
            try {
                io.outputText("Выберите: 1 - 9, 0 - показать номера полей\n");
                cellIndex = io.getInt();
                io.outputText("");
            } catch(Exception e) {
                io.outputText("Некорректный знак! Нужно выбрать либо номер поля от 1 до 9.\n" + e + "\n");
                continue;
            }
            if (cellIndex == 0) {        
                displayFieldWithHelp(fv);
                io.clear();
                displayField(fv);
                continue;
            }
            if (cellIndex < 1 || cellIndex > 9) {
                io.outputText("Выбранной ячейки нет на поле\n");
                continue;
            }
            cellIndex--; //-1, т.к. ячейки в поле нумируются с 0
            if (!availCellIndexes.contains(cellIndex)) { //вынести в отдельный класс Core
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
                io.outputText("Каким знаком будете играть? (" + Mark.X + " или " + Mark.O + ")");
                inputEnteredByPlayer = io.getString();
                io.outputText("");
            } catch(Exception e) {
                io.outputText("Некорректный знак! Нужно выбрать либо '" + Mark.X + "' либо '" + Mark.O + "'.\n" + e + "\n");
                continue;
            }
            if (inputEnteredByPlayer.equalsIgnoreCase(Mark.X.toString())) {
                return Mark.X;
            }
            if (inputEnteredByPlayer.equalsIgnoreCase(Mark.O.toString())) {
                return Mark.O;
            }
            io.outputText("Нужно выбрать либо '" + Mark.X + "' либо '" + Mark.O + "'.");
        }
    }

    @Override
    public void displayField(IFieldViewer fv) {
        field = fv;
        io.outputText(String.format(fieldTemplate,
            getMark(0), getMark(1), getMark(2),
            getMark(3), getMark(4), getMark(5),
            getMark(6), getMark(7), getMark(8)));
        io.outputText("");
    }

    @Override
    public void displayFieldWithHelp(IFieldViewer fv) {
        io.clear();
        io.outputText("Сетка игры выглядит следующим образом:");
        io.outputText(String.format(fieldTemplate,
            1, 2, 3,
            4, 5, 6,
            7, 8, 9));
        io.outputText("");
        io.outputText("Просьба указывать номер поля, куда хотите сделать свой ход.\n");
        askForContinue();
    }
    
    @Override
    public void displayCurrentMoveMark(IFieldViewer fv, Mark mark) {
        io.clear();
        io.outputText("Сейчас ход " + mark.toString() + ":\n");
        displayField(fv);
    }

    @Override
    public void displayRoundResult(IFieldViewer fv, GameRoundResult grr) {
        io.clear();
        io.outputText("Игра завершена:\n");
        displayField(fv);
        switch(grr) {
            case WIN_X -> io.outputText("Выиграл X");
            case WIN_O -> io.outputText("Выиграл O");
            case DRAW  -> io.outputText("Ничья");
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
