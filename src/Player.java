import java.util.List;

public class Player {
    private final String name;
    private int score;
    private List<Tile> tileList;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTileList(List<Tile> tileList) {
        this.tileList = tileList;
    }

    public List<Tile> getTileList() {
        return tileList;
    }

}
