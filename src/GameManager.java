import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private PlayingField playingField;
    private List<Player> playerList;
    private TileBag tileBag;
    public GameManager() {
        playerList = new ArrayList<Player>();
        tileBag = new TileBag();
        for(Player player: playerList) {
            player.setTileList(tileBag.replenishTiles(7));
        }
        playingField=new PlayingField(playerList);

    }
}
