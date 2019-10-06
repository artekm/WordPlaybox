package wordPlaybox;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@SuppressWarnings("Convert2MethodRef")
public class AnalyzerImpl implements Analyzer {
    @Override
    public Map<String, Long> analyzeWords(List<String> words) {
        return words.stream()
                    .collect(Collectors.groupingBy(word -> word, () -> new TreeMap<>(), Collectors.counting()));
    }
}
