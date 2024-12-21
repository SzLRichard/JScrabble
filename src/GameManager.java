import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private PlayingField playingField;
    private List<Player> playerList;
    private TileBag tileBag;
    public GameManager(PlayingField playingField) {
        this.playingField = playingField;
        playerList = new ArrayList<Player>();
        tileBag = new TileBag();
        for(Player player: playerList) {
            player.setTileList(tileBag.replenishTiles(7));
        }
    }
}
