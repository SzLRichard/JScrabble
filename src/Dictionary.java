import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Dictionary {
    private Set<String> dictionary;
    public Dictionary() {
        dictionary = new HashSet<String>();
        readWords();
    }
    public boolean contains(String word) {
        return dictionary.contains(word);
    }

    public void readWords() {
        try (BufferedReader reader = new BufferedReader(new FileReader("resources/wordlist.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dictionary.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

}
