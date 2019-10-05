package pl.itacademy;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@SuppressWarnings("Convert2MethodRef")
public class Generator1 implements Generator {
    @Override
    public void generateWords(Integer count, String dictionaryFilename, String outputFileName) throws IOException {
        List<String> dictionary = Files.readAllLines(Paths.get(dictionaryFilename));
        Random rnd = new Random();
        List<String> words = Stream
                .generate(() -> rnd.nextInt(dictionary.size()))
                .limit(count)
                .map(index -> dictionary.get(index))
                .collect(Collectors.toList());
        Files.write(Paths.get(outputFileName), words);
    }
}
