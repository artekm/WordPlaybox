package pl.itacademy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Generator {
    public void generateWords(Integer count, String dictionaryFilename,String outputFileName) throws IOException {
        List<String> dictionary = Files.readAllLines(Paths.get(dictionaryFilename));
        Random rnd = new Random();
        List<String> words = Stream
                .generate(() -> rnd.nextInt(dictionary.size()))
                .limit(count)
                .map(index -> dictionary.get(index))
                .collect(Collectors.toList());
        Files.write(Paths.get(outputFileName),words);
    }
}