package wordPlaybox;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Primary
@Component
public class AnalyzerImpl2 implements Analyzer {
    @Override
    public Map<String, Long> analyzeWords(List<String> words) {
        Map<String, Long> wordsOccurrence = new TreeMap<>();
        words.forEach(word -> {
            Long oldValue = wordsOccurrence.getOrDefault(word, 0L);
            wordsOccurrence.put(word, oldValue + 1);
        });
        return wordsOccurrence;
    }
}
