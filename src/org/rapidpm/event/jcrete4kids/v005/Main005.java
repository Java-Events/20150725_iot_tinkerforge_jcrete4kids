package org.rapidpm.event.jcrete4kids.v005;

import com.tinkerforge.*;

/**
 * Created by svenruppert on 24.07.15.
 */
public class Main005 {

  private static final String HOST = "localhost";
  private static final int PORT = 4223;
  private static final String UID_AMBIENTLIGHT = "m8y"; // Change to your UID
  private static final String UID_LCD = "qoL"; // Change to your UID
  private static final String UID_TOUCHPAD = "o4r"; // Change to your UID

  public static void main(String[] args) throws Exception {
    IPConnection ipcon = new IPConnection(); // Create IP connection
    final BrickletAmbientLight al = new BrickletAmbientLight(UID_AMBIENTLIGHT, ipcon); // Create device object
    final BrickletLCD20x4 lcd = new BrickletLCD20x4(UID_LCD, ipcon); // Create device object
    final BrickletMultiTouch mt = new BrickletMultiTouch(UID_TOUCHPAD, ipcon); // Create device object

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
          lcd.writeLine((short) 0, (short) 0, text);
        } catch (TimeoutException | NotConnectedException e) {
          e.printStackTrace();
        }
      }
    });


    // Add and implement touch state listener (called if touch state changes)
    mt.addTouchStateListener(new BrickletMultiTouch.TouchStateListener() {
      public void touchState(int touchState) {
        String str = "";

        if((touchState & 0xfff) == 0) {
          str += "No electrodes touched" + System.getProperty("line.separator");
        } else {
          str += "Electrodes ";

          for(int i = 0; i < 12; i++) {
            if((touchState & (1 << i)) == (1 << i)) {
              str += i + " _ ";
              if (i == 3){setCallbackRate(  100);}
              if (i == 5){setCallbackRate( -100);}
            }
          }
          str += "touched" + System.getProperty("line.separator");
        }

        System.out.println(str);
      }

      private void setCallbackRate(int delta) {
        try {
          final long illuminanceCallbackPeriod = al.getIlluminanceCallbackPeriod();
          al.setIlluminanceCallbackPeriod( illuminanceCallbackPeriod + delta);

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
