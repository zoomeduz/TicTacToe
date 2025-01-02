package com.zoomeduz.tictactoe;

/**
 *
 * @author zoomeduz
 */
class ConsoleObserver implements Observer {
    
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
            ).replace("?", "%s");
    
    ConsoleObserver(IO io) {
        this.io = io;
    }

    @Override
    public void onGameRoundStarted(FieldViewer fv) {
        field = fv;
    }

    @Override
    public void onGameRoundFinished(GameRoundResult grr) {
        io.clear();
        io.outputText("Игра завершена:\n");
        displayField();
        switch(grr) {
            case WIN_X:
                io.outputText("Выиграл X");
                break;
            case WIN_O:
                io.outputText("Выиграл O");
                break;
            case DRAW:
                io.outputText("Ничья");
                break;
        }
    }
    
    @Override
    public void onMoveBefore(Mark mark) {
        io.clear();
        io.outputText("Сейчас ход " + mark.toString() + ":\n");
        displayField();
    }

    @Override
    public void onMoveDone(int cellIndex, Mark mark) {
    }
    
    private void displayField() {
        io.outputText(String.format(fieldTemplate,
            getMark(0), getMark(1), getMark(2),
            getMark(3), getMark(4), getMark(5),
            getMark(6), getMark(7), getMark(8)));
        io.outputText("");
    }

    private String getMark(int cellIndex) {
        Mark m = field.get(cellIndex);
        return m == null? " " : m.toString();
    }
    
}
