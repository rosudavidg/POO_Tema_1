package game.map;

import fileio.implementations.FileReader;

/**
 * Concret, o matrice de valori dintr-un "enum".
 */
public class Map {
    private Zone[][] zone;

    /**
     * Constructor pentru citirea din fisier a unei harti.
     * @param fileReader Bufferul catre fisierul de intrare
     * @throws Exception Eroare pentru citirea din fisier
     */
    public Map(final FileReader fileReader) throws Exception {
        int noRows    = fileReader.nextInt();
        int noColumns = fileReader.nextInt();

        zone = new Zone[noRows][noColumns];

        for (int rowCount = 0; rowCount < noRows; rowCount++) {
            String newZones = fileReader.nextWord();

            for (int columnsCount = 0; columnsCount < noColumns; columnsCount++) {
                char newZone = newZones.charAt(columnsCount);

                switch (newZone) {
                    case('V'):
                        zone[rowCount][columnsCount] = Zone.VOLCANIC;
                        break;
                    case('D'):
                        zone[rowCount][columnsCount] = Zone.DESERT;
                        break;
                    case('W'):
                        zone[rowCount][columnsCount] = Zone.WOODS;
                        break;
                    case('L'):
                        zone[rowCount][columnsCount] = Zone.LAND;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * Intorc zona de la pozitia primita ca argument.
     * @param position O pozitie
     * @return Zona
     */
    public final Zone getZone(final Position position) {
        return zone[position.getX()][position.getY()];
    }
}
