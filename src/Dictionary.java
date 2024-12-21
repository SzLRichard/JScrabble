import java.util.HashSet;
import java.util.Set;

public class Dictionary {
    private Set<String> dictionary;
    public Dictionary() {
        dictionary = new HashSet<String>();
    }
    public void addWord(String word) {
        dictionary.add(word);
    }
    public boolean contains(String word) {
        return dictionary.contains(word);
    }
}
