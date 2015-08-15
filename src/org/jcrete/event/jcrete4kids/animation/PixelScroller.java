package org.jcrete.event.jcrete4kids.animation;

import com.tinkerforge.BrickletLCD20x4;
import com.tinkerforge.IPConnection;
import java.util.concurrent.TimeUnit;

/**
 * A demo program to show a pixel-wise text scroller on the LCD 20x4 bricklet.
 */
public class PixelScroller {

    private static final String UID = "od6"; // Change to your UID

    private static final String HOST = "localhost";
    private static final int PORT = 4223;
    private static final String OUTPUT_TEXT = "JCrete4Kids 2015";

    private static final char CUSTOM_CHAR_ZERO = '\u0008';
    private static final short CUSTOM_CHAR_ZERO_ID = 0;

    private static final char CUSTOM_CHAR_ONE = '\u0009';
    private static final short CUSTOM_CHAR_ONE_ID = 1;

    public static void main(String args[]) throws Exception {
        IPConnection ipcon = new IPConnection();
        BrickletLCD20x4 lcd = new BrickletLCD20x4(UID, ipcon);
        ipcon.connect(HOST, PORT);

        lcd.clearDisplay();
        lcd.backlightOn();

        short outputLineNUmber = 1;

        short[] charLeftPixels = new short[8];
        short[] charRightPixels = new short[8];
        lcd.setCustomCharacter(CUSTOM_CHAR_ZERO_ID, charLeftPixels);
        lcd.setCustomCharacter(CUSTOM_CHAR_ONE_ID, charRightPixels);

        String customCharacters = "" + CUSTOM_CHAR_ZERO + CUSTOM_CHAR_ONE;

        for (int charIndex = 0; charIndex < OUTPUT_TEXT.length(); charIndex++) {
            for (int position = 18; position >= 2 + charIndex; position--) {
                char actualCharacter = getActualCharacter(charIndex);
                if (actualCharacter == ' ') {
                    continue;
                }
                int[] twoCharacterPixels = getTwoCharacterPixels(actualCharacter);

                splitTwoCharacter(twoCharacterPixels, charLeftPixels, charRightPixels);

                lcd.setCustomCharacter(CUSTOM_CHAR_ZERO_ID, charLeftPixels);
                lcd.setCustomCharacter(CUSTOM_CHAR_ONE_ID, charRightPixels);

                lcd.writeLine(outputLineNUmber, (short) position, customCharacters);

                for (int j = 0; j < 5; j++) {
                    moveTwoCharacterPixels(twoCharacterPixels);
                    TimeUnit.MILLISECONDS.sleep(50);
                    splitTwoCharacter(twoCharacterPixels, charLeftPixels, charRightPixels);
                    lcd.setCustomCharacter(CUSTOM_CHAR_ZERO_ID, charLeftPixels);
                    lcd.setCustomCharacter(CUSTOM_CHAR_ONE_ID, charRightPixels);
                }
                lcd.writeLine(outputLineNUmber, (short) (position), actualCharacter + " ");
            }
        }

//        lcd.backlightOff();
        ipcon.disconnect();
    }

    /**
     * Returns the pixel definition of the left and right character. The pixel
     * definition of the left and right character is concatenated per row.
     */
    private static int[] getTwoCharacterPixels(char c) {
        int[] character = new int[8];

        if (c == 'J') {
            character[0] = 0b00000111;
            character[1] = 0b00000010;
            character[2] = 0b00000010;
            character[3] = 0b00000010;
            character[4] = 0b00000010;
            character[5] = 0b00010010;
            character[6] = 0b00001100;
            character[7] = 0b00000000;
        } else if (c == 'C') {
            character[0] = 0b00001110;
            character[1] = 0b00010001;
            character[2] = 0b00010000;
            character[3] = 0b00010000;
            character[4] = 0b00010000;
            character[5] = 0b00010001;
            character[6] = 0b00001110;
            character[7] = 0b00000000;
        } else if (c == 'r') {
            character[0] = 0b00000000;
            character[1] = 0b00000000;
            character[2] = 0b00010110;
            character[3] = 0b00011001;
            character[4] = 0b00010000;
            character[5] = 0b00010000;
            character[6] = 0b00010000;
            character[7] = 0b00000000;
        } else if (c == 'e') {
            character[0] = 0b00000000;
            character[1] = 0b00000000;
            character[2] = 0b00001110;
            character[3] = 0b00010001;
            character[4] = 0b00011111;
            character[5] = 0b00010000;
            character[6] = 0b00001110;
            character[7] = 0b00000000;
        } else if (c == 't') {
            character[0] = 0b00001000;
            character[1] = 0b00001000;
            character[2] = 0b00011100;
            character[3] = 0b00001000;
            character[4] = 0b00001000;
            character[5] = 0b00001001;
            character[6] = 0b00000110;
            character[7] = 0b00000000;
        } else if (c == '4') {
            character[0] = 0b00000010;
            character[1] = 0b00000110;
            character[2] = 0b00001010;
            character[3] = 0b00010010;
            character[4] = 0b00011111;
            character[5] = 0b00000010;
            character[6] = 0b00000010;
            character[7] = 0b00000000;
        } else if (c == 'K') {
            character[0] = 0b00010001;
            character[1] = 0b00010010;
            character[2] = 0b00010100;
            character[3] = 0b00011000;
            character[4] = 0b00010100;
            character[5] = 0b00010010;
            character[6] = 0b00010001;
            character[7] = 0b00000000;
        } else if (c == 'i') {
            character[0] = 0b00000100;
            character[1] = 0b00000000;
            character[2] = 0b00001100;
            character[3] = 0b00000100;
            character[4] = 0b00000100;
            character[5] = 0b00000100;
            character[6] = 0b00001110;
            character[7] = 0b00000000;
        } else if (c == 'd') {
            character[0] = 0b00000001;
            character[1] = 0b00000001;
            character[2] = 0b00001101;
            character[3] = 0b00010011;
            character[4] = 0b00010001;
            character[5] = 0b00010001;
            character[6] = 0b00001111;
            character[7] = 0b00000000;
        } else if (c == 's') {
            character[0] = 0b00000000;
            character[1] = 0b00000000;
            character[2] = 0b00001110;
            character[3] = 0b00010000;
            character[4] = 0b00001110;
            character[5] = 0b00000001;
            character[6] = 0b00011110;
            character[7] = 0b00000000;
        } else if (c == '2') {
            character[0] = 0b00001110;
            character[1] = 0b00010001;
            character[2] = 0b00000001;
            character[3] = 0b00000010;
            character[4] = 0b00000100;
            character[5] = 0b00001000;
            character[6] = 0b00011111;
            character[7] = 0b00000000;
        } else if (c == '0') {
            character[0] = 0b00001110;
            character[1] = 0b00010001;
            character[2] = 0b00010011;
            character[3] = 0b00010101;
            character[4] = 0b00011001;
            character[5] = 0b00010001;
            character[6] = 0b00001110;
            character[7] = 0b00000000;
        } else if (c == '1') {
            character[0] = 0b00000100;
            character[1] = 0b00001100;
            character[2] = 0b00000100;
            character[3] = 0b00000100;
            character[4] = 0b00000100;
            character[5] = 0b00000100;
            character[6] = 0b00001110;
            character[7] = 0b00000000;
        } else if (c == '5') {
            character[0] = 0b00011111;
            character[1] = 0b00010000;
            character[2] = 0b00011110;
            character[3] = 0b00000001;
            character[4] = 0b00000001;
            character[5] = 0b00010001;
            character[6] = 0b00001110;
            character[7] = 0b00000000;
        } else {
            character[0] = 0;
            character[1] = 0;
            character[2] = 0;
            character[3] = 0;
            character[4] = 0;
            character[5] = 0;
            character[6] = 0;
            character[7] = 0;
        }
        return character;
    }

    /**
     * Returns the character at the {@code charIndex} postion of the output
     * text.
     *
     * @param charIndex character index in the output text
     * @return the character at the given position
     */
    private static char getActualCharacter(int charIndex) {
        return OUTPUT_TEXT.charAt(charIndex);
    }

    /**
     * Split the two-character pixel array into the pixel definition of the left
     * and right character.
     *
     * @param twoCharacterPixels the pixel array containing the definition of
     * the left and right character
     * @param charLeftPixels the array to store the pixel definition of the left
     * character
     * @param charRightPixels the array to store the pixel definition of the
     * right character
     */
    private static void splitTwoCharacter(int[] twoCharacterPixels, short[] charLeftPixels, short[] charRightPixels) {
        for (int i = 0; i < 7; i++) {
            charLeftPixels[i] = (short) ((twoCharacterPixels[i] & 0x3E0) >> 5);
            charRightPixels[i] = (short) (twoCharacterPixels[i] & 0x001F);
        }
    }

    /**
     * Move the pixels of the two-character to left by one position.
     *
     * @param twoCharacterPixels the pixel array containing the definition of
     * the left and right character
     */
    private static void moveTwoCharacterPixels(int[] twoCharacterPixels) {
        for (int pixelRow = 0; pixelRow < 7; pixelRow++) {
            twoCharacterPixels[pixelRow] = twoCharacterPixels[pixelRow] << 1;
        }
    }
}
