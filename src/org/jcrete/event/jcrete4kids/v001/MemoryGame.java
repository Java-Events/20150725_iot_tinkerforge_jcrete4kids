package org.jcrete.event.jcrete4kids.v001;

import java.util.Random;

public class MemoryGame {
    public static final String[] CARDS = new String[] {"%", "#", "@", "&", "*", "^"};
    public static final int ROW_COUNT = 4;
    public static final int COL_COUNT = 3;
    public static final int CARD_COUNT = 12;

    private String[][] board;

    public MemoryGame() {
        this.board = initBoard(shuffleCards());
    }

    public String[][] getBoard() {
        return board;
    }

    private String[] shuffleCards() {
        String[] result = new String[CARD_COUNT];
        for (int i = 0; i < CARDS.length; i++) {
            result[getEmptyPosition(result)] = CARDS[i];
            result[getEmptyPosition(result)] = CARDS[i]; // FIXME: Add actual shuffling
        }

        return result;
    }

    private int getEmptyPosition(String[] result) {
        Random randomGenerator = new Random();
        int i = randomGenerator.nextInt(CARD_COUNT);
        while( result[i] != null){
            i = randomGenerator.nextInt(CARD_COUNT);
        }
        return i;
    }

    private String[][] initBoard(String[] cards) {
        String[][] result = new String[ROW_COUNT][COL_COUNT];
        for (int idx = 0, r = 0; r < result.length; r++) {
            for (int c = 0; c < result[r].length; c++) {
                result[r][c] = cards[idx++];
            }
        }
        return result;
    }
}
