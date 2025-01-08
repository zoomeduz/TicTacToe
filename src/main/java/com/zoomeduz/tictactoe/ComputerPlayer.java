package com.zoomeduz.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author zoomeduz
 */
class ComputerPlayer implements IPlayer {

    private final Mark mark;

    ComputerPlayer(Mark mark) {
        this.mark = mark;
    }

    //пока играет чисто рандомно, без анализа
    @Override
    public int getMove(IFieldViewer field) {
        Set<Integer> availCellIndexes = Core.getAvailCellIndexes(field);
        if (availCellIndexes.isEmpty()) {
            throw new RuntimeException("Нет доступных ячеек на поле для хода");
        }

        makeDelay();

        List<Integer> freeCells = new ArrayList<>(availCellIndexes);
        Random rn = new Random();
        int index = rn.nextInt(freeCells.size());
        return freeCells.get(index);
    }

    //имитация продумывания хода
    private void makeDelay() {
        int maxDelay = 2000;
        int minDelay = 800;

        Random rn = new Random();
        int delay = rn.nextInt(maxDelay - minDelay + 1) + minDelay;
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
        }
    }

    @Override
    public Mark getMark() {
        return mark;
    }

}
