import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private PlayingField playingField;
    private List<Player> playerList;
    private TileBag tileBag;
    public GameManager() {
        playerList = new ArrayList<Player>();
        tileBag = new TileBag();
        playingField=new PlayingField(playerList);

    }
}
