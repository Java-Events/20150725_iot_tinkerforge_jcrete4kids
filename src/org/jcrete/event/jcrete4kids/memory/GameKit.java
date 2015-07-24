package org.jcrete.event.jcrete4kids.memory;

import com.tinkerforge.*;
import com.tinkerforge.BrickletAmbientLight.IlluminanceListener;
import com.tinkerforge.BrickletLCD20x4.ButtonPressedListener;
import com.tinkerforge.BrickletMultiTouch.TouchStateListener;
import org.jcrete.event.jcrete4kids.memory.MemoryGame.Card;

import java.io.IOException;

public class GameKit {
    public static final String DISPLAY_ID = "odj";
    public static final String KEYPAD_ID = "jSW";
    public static final String LIGHT_ID = "m9J";

    private IPConnection connection;
    private BrickletLCD20x4 display;
    private BrickletMultiTouch keypad;
    private BrickletAmbientLight ambientLight;
    private MemoryGame game;

    public GameKit(MemoryGame game, String host, int port) {
        this.game = game;
        this.connection = new IPConnection();
        try {
            connection.connect(host, port);
            this.display = new BrickletLCD20x4(DISPLAY_ID, connection);
            this.keypad = initKeypad(new BrickletMultiTouch(KEYPAD_ID, connection));
// TODO: Add ambient light support as swtich to start over
//            this.ambientLight = ambientLight(new BrickletAmbientLight(LIGHT_ID, connection));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GameKit setup() throws TimeoutException, NotConnectedException {
        this.display.backlightOn();

        showBoard();

        return this;
    }

    public void start() throws IOException {
        System.out.println("Press key to exit");
        System.in.read();
    }

    public void stop() throws NotConnectedException, TimeoutException {
        this.display.clearDisplay();
        this.display.backlightOff();
        connection.disconnect();
    }

    private void showBoard() throws TimeoutException, NotConnectedException {
        this.display.clearDisplay();
        renderCards(game.getBoard());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignored) {
        }
        renderCards(game.getMatches());
    }

    private void renderCards(String[][] cards) throws TimeoutException, NotConnectedException {
        for (int r = 0; r < cards.length; r++) {
            for (int c = 0; c < cards[r].length; c++) {
                this.display.writeLine((short)r,(short)c, cards[r][c]);
            }
        }
    }

    private void showCard(Card card) throws TimeoutException, NotConnectedException {
        hideMessage();
        this.display.writeLine((short) card.getRow(), (short) card.getCol(), card.getValue());
    }

    private BrickletMultiTouch initKeypad(BrickletMultiTouch keypad) throws AlreadyConnectedException, IOException {
        keypad.addTouchStateListener(new TouchStateListener() {
            public void touchState(int touchState) {
                if ((touchState & 0xfff) != 0) {
                    for (int i = 0; i < 12; i++) {
                        if ((touchState & (1 << i)) == (1 << i)) {
                            try {
                                showCard(game.turn(i));
                                if (game.canMatch()) {
                                    if (game.match()) {
                                        showMessage(">> MATCH! <<");
                                    } else {
                                        showMessage("TRY AGAIN");
                                        renderCards(game.getMatches());
                                    }
                                }
                                if (game.isGameWon()) {
                                    display.clearDisplay();
                                    showMessage("!!!YOU WON!!!");
                                    game = new MemoryGame();
                                    showBoard();
                                }
                            } catch (IllegalStateException | TimeoutException | NotConnectedException | InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }

            }
        });
        return keypad;
    }

    private void showMessage(String text) throws TimeoutException, NotConnectedException, InterruptedException {
        this.display.writeLine((short) 1, (short) 6, text);
        Thread.sleep(1000);
    }

    private void hideMessage() throws TimeoutException, NotConnectedException {
        this.display.writeLine((short) 1, (short) 6, "                 ");
    }

    private BrickletAmbientLight ambientLight(BrickletAmbientLight sensor) throws IOException, AlreadyConnectedException, TimeoutException, NotConnectedException {
        // Set Period for illuminance callback to 1s (1000ms)
        // Note: The illuminance callback is only called every second if the illuminance has changed since the last call!
        sensor.setIlluminanceCallbackPeriod(1000);

        // Add and implement illuminance listener (called if illuminance changes)
        sensor.addIlluminanceListener(new IlluminanceListener() {
            public void illuminance(int illuminance) {
                System.out.println("Illuminance: " + illuminance / 10.0 + " Lux");
            }
        });

        return sensor;
    }
}
