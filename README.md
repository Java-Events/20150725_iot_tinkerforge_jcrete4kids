#JCrete4Kids

##preparations:

For this event we downloaded an IDE [IntelliJ from JETBRAINS](https://www.jetbrains.com/idea/download/) for you.

* Go to the website [hhttp://www.tinkerforge.com/en/doc/Downloads.html](http://www.tinkerforge.com/en/doc/Downloads.html)
* Download the BrickViewer and BrickDeamon for your Openration System
  * we prepared a CD for this.


## used elements
* MasterBrick :
* AmbientLight: [online Ambient_Light.html](http://www.tinkerforge.com/en/doc/Hardware/Bricklets/Ambient_Light.html)
* Touchpad:
* LCD:


## Lesson 001
Main001 : Print the values to the CMD-Line.

based on the callback example you could find [online here](http://www.tinkerforge.com/en/doc/Software/Bricklets/AmbientLight_Bricklet_Java.html#ambient-light-bricklet-java-examples)

* connect the MasterBrick and the AmbientLight
* connect the MasterBrick and your PC/Laptop
* start the BrickViewer and press the button "connect"
* check if you could see the AmbientLight
* find out the UID from the AmbientLight
* Implement a main method.
* create an IPConnection
* write the values to the command line

## Lesson 002
Main002: print "Hello World" on the LCD

based on the example you could find [online here](http://www.tinkerforge.com/en/doc/Software/Bricklets/LCD20x4_Bricklet_Java.html#lcd-20x4-bricklet-java-examples)

* connect the MasterBrick and the LCD
* connect the MasterBrick and your PC/Laptop
* start the BrickViewer and press the button "connect"
* check if you could see the LCD
* find out the UID from the LCD
* Implement a main method.
* create an IPConnection
* write "HelloWorld" to the LCD

## Lesson 003
Main003: print the values from the AmbientLight to the LCD

* connect the MasterBrick and the LCD
* connect the MasterBrick and the AmbientLight
* connect the MasterBrick and your PC/Laptop
* start the BrickViewer and press the button "connect"
* check if you could see the LCD
* find out the UID from the LCD
* check if you could see the AmbientLight
* find out the UID from the AmbientLight
* Implement a main method.
* create an IPConnection
* write the Light-Value to the LCD


