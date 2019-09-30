package pl.itacademy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Generator2 implements Generator{
    @Override
    public void generateWords(Integer count, String dictionaryFilename, String outputFileName) throws IOException {
        List<String> dictionary = Files.readAllLines(Paths.get(dictionaryFilename));
        Random rnd = new Random();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName))) {
            for (int num = 0; num < count; num++) {
                int randomIndex = rnd.nextInt(dictionary.size());
                String randomWord = dictionary.get(randomIndex);
                bw.write(randomWord);
                bw.newLine();
            }
        }
    }
}

