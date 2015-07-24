package org.jcrete.event.jcrete4kids.v001;

public class Main {

    private static final String HOST = "localhost";
    private static final int PORT = 4223;

    public static void main(String args[]) throws Exception {
        MemoryGame game = new MemoryGame();

        GameKit kit = new GameKit().init(HOST, PORT);
        kit.setup(game);
        kit.start();
        kit.stop();
    }

}