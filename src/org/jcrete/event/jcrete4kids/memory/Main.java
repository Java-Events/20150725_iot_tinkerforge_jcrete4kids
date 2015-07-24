package org.jcrete.event.jcrete4kids.memory;

public class Main {

    private static final String HOST = "localhost";
    private static final int PORT = 4223;

    public static void main(String args[]) throws Exception {
        MemoryGame game = new MemoryGame();

        GameKit kit = new GameKit(game, HOST, PORT).setup();
        kit.start();
        kit.stop();
    }
}