package org.jcrete.event.jcrete4kids.memory;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jwloka on 24/07/15.
 */

public class MemoryGameTest {
    @Test
    public void boardHasExpectedDimensions() throws Exception {
        String[][] actual = new MemoryGame().getBoard();

        assertEquals(4, actual.length);
        assertEquals(3, actual[0].length);
    }

    @Test
    public void boardIsNotFilledWithEmptyCards() throws Exception {
        String[][] actual = new MemoryGame().getBoard();

        for (int r = 0; r < actual.length; r++) {
            for (int c = 0; c < actual[r].length; c++) {
                assertFalse(actual[r][c].length() == 0);
            }
        }
    }

    @Test
    public void boardIsFilledWithCorrectCoordinates() throws Exception {
        String[][] actual = new MemoryGame("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11").getBoard();


        assertArrayEquals(new String[][]{
                new String[]{"9", "10", "11"},
                new String[]{"6", "7", "8"},
                new String[]{"3", "4", "5"},
                new String[]{"0", "1", "2"}}, actual);
    }

    @Test
    public void turnWithZeroReturnsFirstCard() throws Exception {
        MemoryGame testObj = new MemoryGame("%", "%", "#", "#", "@", "@", "&", "&", "*", "*", "^", "^");

        assertEquals("%", testObj.turn(0).getValue());
    }

    @Test
    public void turnWithTwoReturnsThirdCard() throws Exception {
        MemoryGame testObj = new MemoryGame("%", "%", "#", "#", "@", "@", "&", "&", "*", "*", "^", "^");

        assertEquals("#", testObj.turn(2).getValue());
    }

    @Test
    public void turnWithFourReturnsFithCard() throws Exception {
        MemoryGame testObj = new MemoryGame("%", "%", "#", "#", "@", "@", "&", "&", "*", "*", "^", "^");

        assertEquals("@", testObj.turn(4).getValue());
    }

    @Test
    public void turnWithElevenReturnsTwelfthCard() throws Exception {
        MemoryGame testObj = new MemoryGame("%", "%", "#", "#", "@", "@", "&", "&", "*", "*", "^", "^");

        assertEquals("^", testObj.turn(11).getValue());
    }

    @Test(expected = IllegalStateException.class)
    public void turnWithTwelfthThrowsException() throws Exception {
        MemoryGame testObj = new MemoryGame("%", "%", "#", "#", "@", "@", "&", "&", "*", "*", "^", "^");

        testObj.turn(12);
    }

    @Test(expected = IllegalStateException.class)
    public void turnWithThreeCardsThrowsException() throws Exception {
        MemoryGame testObj = new MemoryGame("%", "%", "#", "#", "@", "@", "&", "&", "*", "*", "^", "^");

        testObj.turn(2);
        testObj.turn(1);
        testObj.turn(3);
    }

    @Test
    public void turnWithSameCardTwiceReturnsCardValue() throws Exception {
        MemoryGame testObj = new MemoryGame("%", "%", "#", "#", "@", "@", "&", "&", "*", "*", "^", "^");

        assertEquals("%", testObj.turn(1).getValue());
        assertEquals("%", testObj.turn(1).getValue());
    }

    @Test
    public void turnWithMatchedCardReturnsCardValue() throws Exception {
        MemoryGame testObj = new MemoryGame("%", "%", "#", "#", "@", "@", "&", "&", "*", "*", "^", "^");

        testObj.turn(0);
        testObj.turn(1);
        testObj.match();

        assertEquals("%", testObj.turn(0).getValue());
    }

    @Test
    public void canMatchWithNoTurnedCardReturnsFalls() throws Exception {
        MemoryGame testObj = new MemoryGame("%", "%", "#", "#", "@", "@", "&", "&", "*", "*", "^", "^");

        assertFalse(testObj.canMatch());
    }

    @Test
    public void canMatchWithOneCardTurnedReturnsFalls() throws Exception {
        MemoryGame testObj = new MemoryGame("%", "%", "#", "#", "@", "@", "&", "&", "*", "*", "^", "^");
        testObj.turn(1);

        assertFalse(testObj.canMatch());
    }

    @Test
    public void canMatchWithTwoCardTurnedReturnsTrue() throws Exception {
        MemoryGame testObj = new MemoryGame("%", "%", "#", "#", "@", "@", "&", "&", "*", "*", "^", "^");
        testObj.turn(1);
        testObj.turn(2);

        assertTrue(testObj.canMatch());
    }

    @Test
    public void canMatchWithOneCardTurnedTwiceReturnsFalse() throws Exception {
        MemoryGame testObj = new MemoryGame("%", "%", "#", "#", "@", "@", "&", "&", "*", "*", "^", "^");
        testObj.turn(1);
        testObj.turn(1);

        assertFalse(testObj.canMatch());
    }

    @Test
    public void matchWithTurnedZeroZeroAndMatchOneZeroReturnsTrue() throws Exception {
        MemoryGame testObj = new MemoryGame("%", "%", "#", "#", "@", "@", "&", "&", "*", "*", "^", "^");

        testObj.turn(0);
        testObj.turn(1);

        assertTrue(testObj.match());
    }

    @Test
    public void matchWithOnlyOneTurnedCardReturnsFalses() throws Exception {
        MemoryGame testObj = new MemoryGame("%", "%", "#", "#", "@", "@", "&", "&", "*", "*", "^", "^");

        testObj.turn(0);

        assertFalse(testObj.match());
    }

    @Test
    public void matchWithNonTurnedCardThrowsException() throws Exception {
        MemoryGame testObj = new MemoryGame("%", "%", "#", "#", "@", "@", "&", "&", "*", "*", "^", "^");

        assertFalse(testObj.match());
    }

    @Test
    public void gameRemembersMatchingPairs() throws Exception {
        MemoryGame testObj = new MemoryGame("%", "%", "#", "#", "@", "@", "&", "&", "*", "*", "^", "^");

        testObj.turn(0);
        testObj.turn(1);
        testObj.match();
        testObj.turn(8);
        testObj.turn(9);
        testObj.match();

        assertArrayEquals(new String[][]{
                new String[]{"*", "\7", "\7"},
                new String[]{"\7", "\7", "*"},
                new String[]{"\7", "\7", "\7"},
                new String[]{"%", "%", "\7"}}, testObj.getMatches());
    }
}
