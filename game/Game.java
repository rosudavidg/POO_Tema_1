package game;

import fileio.implementations.FileReader;
import fileio.implementations.FileWriter;
import game.characters.Character;
import game.characters.knight.Knight;
import game.characters.pyromancer.Pyromancer;
import game.characters.rogue.Rogue;
import game.characters.wizard.Wizard;
import game.map.Direction;
import game.map.Map;
import game.map.Position;

public class Game {
    private final Map map;       // harta
    private final int noPlayers; // numarul de jucatori
    private int noRounds;        // numarul de runde
    private Character[] players; // jucatorii
    private Direction[][] moves; // mutarile

    /**
     * Constructor in care citesc datele de intrare.
     * @param fileReader Bufferul catre fisierul de intrare.
     * @throws Exception Eroare pentru citirea din fisier.
     */
    public Game(final FileReader fileReader) throws Exception {
//        Creare harta
        map       = new Map(fileReader);
        noPlayers = fileReader.nextInt();
        players   = new Character[noPlayers];

//        Adaugare jucator
        for (int playerCount = 0; playerCount < noPlayers; playerCount++) {
            final char newRace = fileReader.nextWord().charAt(0);
            final int  newX    = fileReader.nextInt();
            final int  newY    = fileReader.nextInt();

            Position newPosition = new Position(newX, newY);

            switch (newRace) {
                case('W'):
                    players[playerCount] = new Wizard(newPosition);
                    break;
                case('R'):
                    players[playerCount] = new Rogue(newPosition);
                    break;
                case('K'):
                    players[playerCount] = new Knight(newPosition);
                    break;
                case('P'):
                    players[playerCount] = new Pyromancer(newPosition);
                    break;
                default:
                    break;
            }
        }

//      Adaugare mutare
        this.noRounds = fileReader.nextInt();
        this.moves    = new Direction[noRounds][noPlayers];

        for (int i = 0; i < noRounds; i++) {
            String newRoundMoves = fileReader.nextWord();

            for (int j = 0; j < noPlayers; j++) {
                switch (newRoundMoves.charAt(j)) {
                    case('U'):
                        moves[i][j] = Direction.UP;
                        break;
                    case('D'):
                        moves[i][j] = Direction.DOWN;
                        break;
                    case('L'):
                        moves[i][j] = Direction.LEFT;
                        break;
                    case('R'):
                        moves[i][j] = Direction.RIGHT;
                        break;
                    default:
                        moves[i][j] = Direction.NODIRECTION;
                        break;
                }
            }
        }
    }

    /**
     * Pentru fiecare runda de joc:
     * ~aplic overtime (daca acesta nu exista, nimic nu se modifica);
     * ~mut jucatorii  (daca se pot muta);
     * ~realizez bataliile.
     */
    public final void play() {
        for (int roundIndex = 0; roundIndex < noRounds; roundIndex++) {

//            Aplic overtime
            for (int playerCount = 0; playerCount < noPlayers; playerCount++) {
                if (players[playerCount].isAlive()) {
                    players[playerCount].applyOvertime();
                }
            }

//            Mut jucatorii
            for (int playerCount = 0; playerCount < noPlayers; playerCount++) {
                if (players[playerCount].canMove()) {
                    players[playerCount].movePosition(moves[roundIndex][playerCount]);
                }
            }

//            Batalii
            for (int pCount1 = 0; pCount1 < noPlayers - 1; pCount1++) {
                for (int pCount2 = pCount1 + 1; pCount2 < noPlayers; pCount2++) {
                    if (players[pCount1].canFightWith(players[pCount2])) {
                        fight(players[pCount1], players[pCount2]);
                    }
                }
            }
        }
    }

    /**
     * Lupta dintre cei doi jucatori:
     * ~actualizarea abilitatilor;
     * ~utilizarea lor;
     * ~verificarea castigatorului (daca exista).
     * @param player1 Primul jucator
     * @param player2 Al doilea jucator
     */
    private void fight(final Character player1, final Character player2) {
        updateSkills(player1, player2);
        useSkills(player1, player2);
        checkIfWin(player1, player2);
    }

    /**
     * Actualizarea abilitatilor unui jucator.
     * @param player1 Jucatorul
     * @param player2 Adversarul sau
     */
    private void updateSkills(final Character player1, final Character player2) {
        player1.updateSkills(player2, map);
        player2.updateSkills(player1, map);
    }

    /**
     * Utilizarea abilitatilor unui jucator.
     * @param player1 Jucatorul
     * @param player2 Adversarul sau
     */
    private void useSkills(final Character player1, final Character player2) {
        player1.useSkills(player2);
        player2.useSkills(player1);
    }

    /**
     * Verificarea castigatorului (daca exista).
     * @param player1 Jucatorul 1
     * @param player2 Jucatorul 2
     */
    private void checkIfWin(final Character player1, final Character player2) {
        player1.checkIfWin(player2);
        player2.checkIfWin(player1);
    }

    /**
     * La sfarsitul jocului, pentru fiecare jucator afisez, dupa caz,
     * parametrii acestuia. (clasa, viata, experienta etc).
     * @param fileWriter Bufferul catre fisierul de scriere.
     * @throws Exception Evitare eroare pentru scrierea in fisier.
     */
    public final void printOut(final FileWriter fileWriter) throws Exception {
        for (int i = 0; i < noPlayers; i++) {
            String writeOutput = "" + players[i].getRaceChar();

            if (players[i].isAlive()) {
                writeOutput += " " + players[i].getLevel()
                        + " " + players[i].getXp()
                        + " " + players[i].getHp()
                        + " " + players[i].getPosition().getX()
                        + " " + players[i].getPosition().getY();
            } else {
                writeOutput += " " + "dead";
            }

            fileWriter.writeWord(writeOutput);
            fileWriter.writeNewLine();
        }
    }
}
