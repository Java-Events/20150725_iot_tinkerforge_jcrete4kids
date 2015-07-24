package org.rapidpm.event.jcrete4kids.v003;

import com.tinkerforge.*;

import java.io.IOException;

/**
 * Created by svenruppert on 24.07.15.
 */
public class Main003 {

  private static final String HOST = "localhost";
  private static final int PORT = 4223;
  private static final String UID_AMBIENTLIGHT = "m8y"; // Change to your UID
  private static final String UID_LCD = "qoL"; // Change to your UID

  public static void main(String[] args) throws Exception {
    IPConnection ipcon = new IPConnection(); // Create IP connection
    final BrickletAmbientLight al = new BrickletAmbientLight(UID_AMBIENTLIGHT, ipcon); // Create device object
    final BrickletLCD20x4 lcd = new BrickletLCD20x4(UID_LCD, ipcon); // Create device object

    ipcon.connect(HOST, PORT); // Connect to brickd
    // Don't use device before ipcon is connected

    //write to the LCD
    // Turn backlight on
    lcd.backlightOn();



    // Set Period for illuminance callback to 1s (1000ms)
    // Note: The illuminance callback is only called every second if the
    //       illuminance has changed since the last call!
    al.setIlluminanceCallbackPeriod(1000);

    // Add and implement illuminance listener (called if illuminance changes)
    al.addIlluminanceListener(new BrickletAmbientLight.IlluminanceListener() {
      public void illuminance(int illuminance) {
        final String text = "Light: " + illuminance / 10.0 + " Lux";
        System.out.println(text);

        // Write "Hello World"
        try {
          lcd.clearDisplay();
          lcd.writeLine((short)0, (short)0, text);
        } catch (TimeoutException | NotConnectedException e) {
          e.printStackTrace();
        }


      }
    });

    System.out.println("Press key to exit");
    System.in.read();
    ipcon.disconnect();
  }
}
