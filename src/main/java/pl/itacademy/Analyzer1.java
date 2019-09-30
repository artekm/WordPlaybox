package pl.itacademy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Analyzer1 implements Analyzer{
    @Override
    public void analyzeWords(String inputFileName) throws IOException {
        List<String> input = Files.readAllLines(Paths.get(inputFileName));
        Map<String, Long> occur = input.stream().collect(Collectors.groupingBy(word -> word, TreeMap::new, Collectors.counting()));
        System.out.println(occur);
    }
}
