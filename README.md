#JCrete4Kids

##preparations:

For this event we downloaded an IDE [IntelliJ from JETBRAINS](https://www.jetbrains.com/idea/download/) for you.

* Go to the website [hhttp://www.tinkerforge.com/en/doc/Downloads.html](http://www.tinkerforge.com/en/doc/Downloads.html)
* Download the BrickViewer and BrickDeamon for your Openration System
  * we prepared a CD for this.


## used elements
* MasterBrick :
* AmbientLight: [online Ambient_Light.html](http://www.tinkerforge.com/en/doc/Hardware/Bricklets/Ambient_Light.html)
* Touchpad: [online Multi Touch](http://www.tinkerforge.com/en/doc/Hardware/Bricklets/Multi_Touch.html)
* LCD: [online LCD](http://www.tinkerforge.com/en/doc/Hardware/Bricklets/LCD_20x4.html)


## Lesson 001
Main001 : Print the values to the CMD-Line.

based on the callback example you could find [online here](http://www.tinkerforge.com/en/doc/Software/Bricklets/AmbientLight_Bricklet_Java.html#ambient-light-bricklet-java-examples)

* connect the MasterBrick and the AmbientLight
* connect the MasterBrick and your PC/Laptop
* start the BrickViewer and press the button "connect"
* check if you could see the AmbientLight
* find out the UID from the AmbientLight
* open the BrickViewer and press the button "disconnect"
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
* open the BrickViewer and press the button "disconnect"
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
* open the BrickViewer and press the button "disconnect"
* Implement a main method.
* create an IPConnection
* write the Light-Value to the LCD


##Lesson 004
Main004: first steps with a TouchPad

* connect the MasterBrick and the TouchPad
* connect the MasterBrick and your PC/Laptop
* start the BrickViewer and press the button "connect"
* check if you could see the TouchPad
* find out the UID from the TouchPad
* open the BrickViewer and press the button "disconnect"
* Implement the main method
* write the UID
* start the programm
* find out the right number of each square.

* press at the same time the square 3 and 8
* press at the same time the square 4 and 7 and 10
* press at the same time the square 2 and 5 and 8 and 11

##Lesson 005
Main005: increase and decrease the callbackrate

* connect the MasterBrick and the LCD
* connect the MasterBrick and the AmbientLight
* connect the MasterBrick and the TouchPad
* connect the MasterBrick and your PC/Laptop
* start the BrickViewer and press the button "connect"
* check if you could see the LCD
* find out the UID from the LCD
* check if you could see the AmbientLight
* find out the UID from the AmbientLight
* check if you could see the TouchPad
* find out the UID from the TouchPad
* open the BrickViewer and press the button "disconnect"
* Implement a main method.

