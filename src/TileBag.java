import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Long.min;

public class TileBag {
    private List<Tile> tiles;
    int bagSize;

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
        bagSize = tiles.size();
    }

    public List<Tile> replenishTiles(int n){
        List<Tile> newTiles=new ArrayList<>();
        Random random = new Random();
        Tile pulledTile;
        for(int i=0;i<min(n,bagSize);i++){
            pulledTile=tiles.get(random.nextInt(tiles.size()));
            tiles.remove(pulledTile);
            newTiles.add(pulledTile);
        }
        bagSize= (int) min(n,bagSize);
        return newTiles;
    }

    public List<Tile> swapTiles(List<Tile> toSwap){
        int n=toSwap.size();
        if(n>bagSize){
            System.out.println("Not enough tiles in the bag");
            return null;
        }
        else {
            List<Tile> newTiles = replenishTiles(n);
            for (int i = 0; i < n; i++) {
                tiles.add(toSwap.get(i));
            }
            return newTiles;
        }

    }
}
