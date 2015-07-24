package org.jcrete.event.jcrete4kids.v001;

import com.tinkerforge.*;

import java.io.IOException;

public class GameKit {


    private IPConnection connection;
    private BrickletLCD20x4 display;
    private BrickletMultiTouch multiTouch;

    public GameKit() {
        this.connection = new IPConnection();
    }

    public GameKit init(String host, int port) {
        try {
            connection.connect(host, port);
            this.display = new BrickletLCD20x4("odj", connection); // insert your display UID here
            this.multiTouch= new BrickletMultiTouch("jSW", connection); // insert your display UID here

            multiTouchBoard(this.multiTouch);
            lcdDisplay(this.display);
//            ambientLight(new BrickletAmbientLight("m9J", connection));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public void start() throws IOException {
        System.out.println("Press key to exit");
        System.in.read();
    }

    public void stop() throws NotConnectedException {
        connection.disconnect();
    }


    public void setup(MemoryGame game) throws TimeoutException, NotConnectedException {
        this.display.clearDisplay();
        this.display.backlightOn();

        String[][] board = game.getBoard();
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {

            }
        }
        this.display.writeLine((short)0,(short)0, "");
    }

    private void ambientLight(BrickletAmbientLight al) throws IOException, AlreadyConnectedException, TimeoutException, NotConnectedException {
        // Set Period for illuminance callback to 1s (1000ms)
        // Note: The illuminance callback is only called every second if the
        //       illuminance has changed since the last call!
        al.setIlluminanceCallbackPeriod(1000);

        // Add and implement illuminance listener (called if illuminance changes)
        al.addIlluminanceListener(new BrickletAmbientLight.IlluminanceListener() {
            public void illuminance(int illuminance) {
                System.out.println("Illuminance: " + illuminance / 10.0 + " Lux");
            }
        });
    }

    private void multiTouchBoard(BrickletMultiTouch mt) throws AlreadyConnectedException, IOException {
        // Add and implement touch state listener (called if touch state changes)
        mt.addTouchStateListener(new BrickletMultiTouch.TouchStateListener() {
            public void touchState(int touchState) {
                String str = "";

                if ((touchState & (1 << 12)) == (1 << 12)) {
                    str += "In proximity, ";
                }

                if ((touchState & 0xfff) == 0) {
                    str += "No electrodes touched" + System.getProperty("line.separator");
                } else {
                    str += "Electrodes ";
                    for (int i = 0; i < 12; i++) {
                        if ((touchState & (1 << i)) == (1 << i)) {
                            str += i + " ";
                        }
                    }
                    str += "touched" + System.getProperty("line.separator");
                }

                System.out.println(str);
            }
        });
    }

    private void lcdDisplay(BrickletLCD20x4 lcd) throws AlreadyConnectedException, IOException, TimeoutException, NotConnectedException {
        // Add and implement listener for pressed and released events
        lcd.addButtonPressedListener(new BrickletLCD20x4.ButtonPressedListener() {
            public void buttonPressed(short button) {
                System.out.println("Pressed: " + button);
            }
        });
        lcd.addButtonReleasedListener(new BrickletLCD20x4.ButtonReleasedListener() {
            public void buttonReleased(short button) {
                System.out.println("Released: " + button);
            }
        });
        lcd.clearDisplay();
        lcd.backlightOn();
        lcd.writeLine((short)0, (short)0, "Hello Display!");
    }
}
