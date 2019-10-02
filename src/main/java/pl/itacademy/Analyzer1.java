package pl.itacademy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Analyzer1 implements Analyzer {
    @Override
    public void analyzeWords(String inputFileName) throws IOException {
        Map<String, Long> occur = Files.lines(Paths.get(inputFileName))
                .collect(Collectors.groupingBy(word -> word, TreeMap::new, Collectors.counting()));
        System.out.println(occur);

//        Sort by occurrence
        List<Map.Entry<String, Long>> list = new ArrayList<>(occur.entrySet());
        list.sort((entry1, entry2) -> (int) (entry2.getValue() - entry1.getValue()));
        System.out.println(list);
    }
}
