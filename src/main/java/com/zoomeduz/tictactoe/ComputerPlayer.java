package com.zoomeduz.tictactoe;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author zoomeduz
 */
class ComputerPlayer implements Player {
    
    FieldViewer field;

    @Override
    public void onGameStarted(FieldViewer fv) {
        field = fv;
    }

    @Override
    public void onGameFinished(GameRoundResult grr) {
    }

//нужна проверка на отсутсвие инициализированного field?
    //пока играет чисто рандомно, без анализа
    @Override
    public int getMove() {

    //имитация продумывания хода
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        ArrayList<Integer> freeCells = new ArrayList<>();
        for (int i = 0; i < field.getNumberOfCells(); i++) {
            if (field.get(i) == null) {
                freeCells.add(i);
            }
        }
        Random rn = new Random();
        int index = rn.nextInt(freeCells.size());
        return freeCells.get(index);
    }

    @Override
    public void onMoveProcessed(MoveResult mr) {
    }
    
}
