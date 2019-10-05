package pl.itacademy;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@Component
public class Analyzer2 implements Analyzer {
    @Override
    public void analyzeWords(String inputFileName) throws IOException {
        Map<String, Integer> wordsOccurrence = new TreeMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String word;
            while ((word = reader.readLine()) != null) {
                Integer oldValue = wordsOccurrence.getOrDefault(word, 0);
                wordsOccurrence.put(word, oldValue + 1);
            }
        }
        System.out.println(wordsOccurrence);
    }
}
