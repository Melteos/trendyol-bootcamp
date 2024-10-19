import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class UniqueWordCounter {
    public int countUniqueWords(String sentence) {
        if(sentence.equals(""))
            return 0;
        String[] words = sentence.split("[^\\w+'?\\w*]");
        for (int i=0; i<words.length; i++) {
            words[i] = words[i].toLowerCase();
        }
        Set<String> uniqueWords = new HashSet<String>(Arrays.asList(words));
        return uniqueWords.size();
    }
}
