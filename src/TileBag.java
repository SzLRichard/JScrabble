import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TileBag {
    private List<Tile> tiles;

    private void readTiles() {
        try (BufferedReader reader = new BufferedReader(new FileReader("resources/tileSet.txt"))) {
            String line;
            char letter;
            int score;
            int count;
            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(" ");
                letter = splitLine[0].charAt(0);
                score = Integer.parseInt(splitLine[1]);
                count = Integer.parseInt(splitLine[2]);
                for (int i = 0; i < count; i++) {
                    tiles.add(new Tile(letter, score));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    public TileBag() {
        tiles = new ArrayList<Tile>();
        readTiles();
        tiles.forEach(tile -> System.out.println(tile.getLetter() + " - " + tile.getPoints()));
    }
}
