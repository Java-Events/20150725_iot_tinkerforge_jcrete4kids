package org.jcrete.event.jcrete4kids.animation;

import com.tinkerforge.BrickletLCD20x4;
import com.tinkerforge.IPConnection;
import java.util.concurrent.TimeUnit;

public class TextScroller {

    private static final String UID = "ocZ"; // Change to your UID

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
            for (int position = 20; position > 2 + charIndex; position--) {
                char actualCharacter = OUTPUT_TEXT.charAt(charIndex);
                if (actualCharacter == ' ') {
                    continue;
                }
                lcd.writeLine((short) line, (short) position, actualCharacter + " ");
                TimeUnit.MILLISECONDS.sleep(150);
            }
        }

//        lcd.backlightOff();
        ipcon.disconnect();
    }
}
