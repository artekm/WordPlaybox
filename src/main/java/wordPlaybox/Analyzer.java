package wordPlaybox;

import java.util.List;
import java.util.Map;

public interface Analyzer {
    Map<String, Long> analyzeWords(List<String> words);
}
