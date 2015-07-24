package org.jcrete.event.jcrete4kids.memory;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MemoryGame {
    public static final String[] CARDS = new String[]{"%", "#", "@", "&", "*", "^"};
    public static final int ROW_COUNT = 4;
    public static final int COL_COUNT = 3;
    public static final int CARD_COUNT = 12;

    private String[][] board;
    private List<Card> matches;
    private Card card1;
    private Card card2;
    private boolean isGameOver;

    public MemoryGame() {
        this.matches = new LinkedList<>();
        this.isGameOver = false;
        this.board = initBoard(shuffleCards());
    }

    public MemoryGame(String... cards) {
        this.matches = new LinkedList<>();
        this.board = layoutBoard(cards);
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
        while (result[i] != null) {
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

    public Card turn(int num) throws IllegalStateException {
        if (card1 != null && card2 != null) {
            throw new IllegalStateException();
        }
        int[] coords = calcCoords(num);
        Card result;
        if (card1 == null) {
            this.card1 = new Card(coords[0], coords[1], board);
            result = card1;
            if (matches.contains(card1)) {
                this.card1 = null;
            }
        } else {
            this.card2 = new Card(coords[0], coords[1], board);
            result = card2;
            if (card1.getRow() == card2.getRow() && card1.getCol() == card2.getCol() || matches.contains(card2)) {
                this.card2 = null;
            }
        }
        return result;
    }

    public boolean canMatch() {
        return card1 != null && card2 != null;
    }

    public boolean match() {
        if (card1 == null || card2 == null) {
            return false;
        }
        boolean result = card1.getValue().equals(card2.getValue());

        if (result) {
            matches.add(card1);
            matches.add(card2);
            isGameOver = false;
        } else {
            isGameOver = true;
        }

        this.card1 = null;
        this.card2 = null;

        return result;
    }

    public String[][] getMatches() {
        String[][] result = new String[ROW_COUNT][COL_COUNT];
        for (int idx = 0, r = 0; r < result.length; r++) {
            for (int c = 0; c < result[r].length; c++) {
                result[r][c] = "\7";
            }
        }

        for (Card card : matches) {
            result[card.getRow()][card.getCol()] = card.getValue();
        }
        return result;
    }

    public boolean isGameWon() {
        return matches.size() == CARD_COUNT;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    private int[] calcCoords(int num) throws IllegalStateException {
        if (num >= CARD_COUNT) {
            throw new IllegalStateException();
        }

        int r = ROW_COUNT-1;
        int c = 0;
        for (int i = 0; r >= 0 && i < num; i++) {
            if (c > COL_COUNT - 1) {
                c = 0;
                r -= 1;
            }
            c += 1;
        }
        if (c > COL_COUNT - 1) {
            c = 0;
            r -= 1;
        }

        return new int[] {r, c};
    }

    private String[][] layoutBoard(String... cards) {
        String[][] result = new String[ROW_COUNT][COL_COUNT];
        for (int r = ROW_COUNT-1, c = 0, i = 0; r >=0 && i < cards.length; i++) {
            if (c > COL_COUNT - 1) {
                c = 0;
                r -= 1;
            }
            result[r][c] = cards[i];
            c += 1;

        }
        return result;
    }


    public static class Card {
        private final int row;
        private final int col;
        private final String value;

        private Card(int row, int col, String[][] board) {
            this.row = row;
            this.col = col;
            this.value = board[row][col];
        }

        public String getValue() {
            return value;
        }

        public int getCol() {
            return col;
        }

        public int getRow() {
            return row;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Card)) return false;

            Card card = (Card) o;

            if (row != card.row) return false;
            if (col != card.col) return false;
            return !(value != null ? !value.equals(card.value) : card.value != null);

        }

        @Override
        public int hashCode() {
            int result = row;
            result = 31 * result + col;
            result = 31 * result + (value != null ? value.hashCode() : 0);
            return result;
        }
    }
}
