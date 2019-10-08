package wordPlaybox;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class AnalyzerImpl implements Analyzer {
    @Override
    public Map<String, Long> analyzeWords(List<String> words) {
        return words.stream()
                    .collect(Collectors.groupingBy(Function.identity(), TreeMap::new, Collectors.counting()));
    }
}