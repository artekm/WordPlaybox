package pl.itacademy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Analyzer2 implements Analyzer {
    @Override
    public void analyzeWords(String inputFileName) throws IOException {
        Map<String, Integer> occur = new TreeMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String word;
            while ((word = reader.readLine()) != null) {
                occur.put(word, occur.getOrDefault(word, 1) + 1);
            }
        }
        System.out.println(occur);
    }
}
