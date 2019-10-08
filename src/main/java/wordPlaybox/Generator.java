package wordPlaybox;

import java.util.List;

public interface Generator {
    List<String> generateWords(Integer count, List<String> dictionary);
    String nextRandomWord(List<String> dictionary);
}
