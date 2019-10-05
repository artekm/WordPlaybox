package pl.itacademy;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Component
@SuppressWarnings("Convert2MethodRef")
public class Analyzer1 implements Analyzer {
    @Override
    public void analyzeWords(String inputFileName) throws IOException {
        Map<String, Long> wordsOccurrence = Files.lines(Paths.get(inputFileName))
                .collect(Collectors.groupingBy(word -> word, () -> new TreeMap<>(), Collectors.counting()));
        System.out.println(wordsOccurrence);

//        Sort by occurrence
        List<Map.Entry<String, Long>> list = new ArrayList<>(wordsOccurrence.entrySet());

        Comparator<Map.Entry<String, Long>> descendingOccurrenceComparator = (entry1, entry2) -> (int) (entry2.getValue() - entry1.getValue());
        list.sort(descendingOccurrenceComparator);

        System.out.println(list);
    }
}
