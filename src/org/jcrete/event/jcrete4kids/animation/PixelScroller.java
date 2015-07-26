package org.jcrete.event.jcrete4kids.animation;

import com.tinkerforge.BrickletLCD20x4;
import com.tinkerforge.IPConnection;
import java.util.concurrent.TimeUnit;

public class PixelScroller {

    private static final String UID = "od6"; // Change to your UID

    private static final String HOST = "localhost";
    private static final int PORT = 4223;
    private static final String OUTPUT_TEXT = "JCrete4Kids 2015";

    public static void main(String args[]) throws Exception {
        IPConnection ipcon = new IPConnection();
        BrickletLCD20x4 lcd = new BrickletLCD20x4(UID, ipcon);
        ipcon.connect(HOST, PORT);

        lcd.clearDisplay();
        lcd.backlightOn();

        int line = 1;

        for (int charIndex = 0; charIndex < OUTPUT_TEXT.length(); charIndex++) {
            int position;
            for (position = 19; position > 1 + charIndex; position--) {
                char actualCharacter = OUTPUT_TEXT.charAt(charIndex);
                if (actualCharacter == ' ') {
                    continue;
                }
                short[] charLeft = new short[8];
                short[] charRight = new short[8];
                int[] character = getPixelCharacter(actualCharacter);
                lcd.writeLine((short) line, (short) position, "\u0008\u0009 ");
                for (int j = 0; j < 6; j++) {

                    for (int i = 0; i < 7; i++) {
                        charLeft[i] = (short) ((character[i] & 0x3E0) >> 5);
                        charRight[i] = (short) (character[i] & 0x001F);
                    }
                    lcd.setCustomCharacter((short) 0, charLeft);
                    lcd.setCustomCharacter((short) 1, charRight);

                    for (int i = 0; i < 7; i++) {
                        character[i] = character[i] << 1;
                    }

                    TimeUnit.MILLISECONDS.sleep(50);
                }
                lcd.writeLine((short) line, (short) position, actualCharacter + "  ");
            }
        }

//        lcd.backlightOff();
        ipcon.disconnect();
    }

    private static int[] getPixelCharacter(char c) {
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
}
