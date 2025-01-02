package com.zoomeduz.tictactoe;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author zoomeduz
 */
class ComputerPlayer implements IPlayer {
    
    IFieldViewer field;

    @Override
    public void onGameStarted(IFieldViewer fv) {
        field = fv;
    }

    @Override
    public void onGameFinished(GameRoundResult grr) {
    }

    //пока играет чисто рандомно, без анализа
    @Override
    public int getMove() {
        makeDelay();

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

    //имитация продумывания хода
    private void makeDelay() {
        int maxDelay = 1200;
        int minDelay = 600;

        Random rn = new Random();
        int delay = rn.nextInt(maxDelay - minDelay + 1) + minDelay;
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
        }
    }
    
}
