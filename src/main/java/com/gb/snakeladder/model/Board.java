package com.gb.snakeladder.model;

import lombok.Data;
import org.apache.commons.lang3.RandomUtils;

import java.util.Map;

@Data
public class Board {
    private int size;
    private int start;
    private int end;
    private Map<Integer, Integer> snakes;
    private Map<Integer, Integer> ladders;

    public Board(int size, int numberOfSnakes, int numberOfLadders) {
        this.start = 1;
        this.end = start + size - 1;
        this.size = size;
        initialiseSnakesAndLadders(numberOfSnakes, numberOfLadders);
    }

    private void initialiseSnakesAndLadders(int numberOfSnakes, int numberOfLadders) {
        for (int i = 0; i < numberOfSnakes; i++) {
            while (true) {
                int snakeStart = RandomUtils.nextInt(start, end);
                int snakeEnd = RandomUtils.nextInt(start, end);
                if (snakeEnd >= snakeStart)
                    continue;
                if (!snakes.containsKey(snakeStart)) {
                    snakes.put(snakeStart, snakeEnd);
                    break;
                }
            }
        }
        for (int i = 0; i < numberOfLadders; i++) {
            while (true) {
                int ladderStart = RandomUtils.nextInt(start, end);
                int ladderEnd = RandomUtils.nextInt(start, end);
                if (ladderEnd <= ladderStart)
                    continue;
                if (!ladders.containsKey(ladderStart)) {
                    ladders.put(ladderStart, ladderEnd);
                    break;
                }
            }
        }
    }
}
