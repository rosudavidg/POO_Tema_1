package main;

import fileio.implementations.FileReader;
import fileio.implementations.FileWriter;
import game.Game;

public final class Main {

    private Main() {
    }

    public static void main(final String[] args) throws Exception {
        FileReader fileReader;
        FileWriter fileWriter;

//        Deschiderea fisierelor
        try {
            fileReader = new FileReader(args[0]);
        } catch (Exception e) {
            return;
        }

        try {
            fileWriter = new FileWriter(args[1]);
        } catch (Exception e) {
            return;
        }

//        Creare joc
        Game game = new Game(fileReader);

//        Rularea jocului
        game.play();

//        Scrierea la fisierul de iesire
        game.printOut(fileWriter);

//        Inchiderea fisierelor
        fileReader.close();
        fileWriter.close();
    }
}
