package com.gb.snakeladder.model;

import lombok.Getter;

import java.util.ArrayDeque;
import java.util.Queue;

@Getter
public class Game {
    private int numberOfSnakes;
    private int numberOfLadders;

    private Queue<Player> players;

    private Board board;
    private Dice dice;

    public Game(int numberOfLadders, int numberOfSnakes,
                int boardSize) {
        this.numberOfLadders = numberOfLadders;
        this.numberOfSnakes = numberOfSnakes;
        this.players = new ArrayDeque<>();
        board = new Board(boardSize, numberOfSnakes, numberOfLadders);
        dice = new Dice(1, 6, 2);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void playGame() {
        while (true) {
            Player player = players.poll();
            int val = dice.roll();
            int newPosition = player.getPosition() + val;
            if (newPosition > board.getEnd()) {
                player.setPosition(player.getPosition());
                players.offer(player);
            } else {
                player.setPosition(getNewPosition(newPosition));
                if (player.getPosition() == board.getEnd()) {
                    player.setWon(true);
                    System.out.println("Player " + player.getName() + " Won.");
                } else {
                    System.out.println("Setting " + player.getName() + "'s new position to " + player.getPosition());
                    players.offer(player);
                }
            }
            if (players.size() < 2) {
                break;
            }
        }
    }

    private int getNewPosition(int newPosition) {
        if(board.getSnakes().containsKey(newPosition)) {
            newPosition = board.getSnakes().get(newPosition);
        }
        if(board.getLadders().containsKey(newPosition)) {
            newPosition = board.getSnakes().get(newPosition);
        }
        return newPosition;
    }
}
