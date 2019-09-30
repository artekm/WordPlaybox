package pl.itacademy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class Generator3 implements Generator {
    @Override
    public void generateWords(Integer count, String dictionaryFilename, String outputFileName) throws IOException {
        List<String> dictionary = Files.readAllLines(Paths.get(dictionaryFilename));
        Random rnd = new Random();
        try (PrintStream ps = new PrintStream(outputFileName)) {
            System.setOut(ps);
            for (int num = 0; num < count; num++) {
                int randomIndex = rnd.nextInt(dictionary.size());
                String randomWord = dictionary.get(randomIndex);
                System.out.println(randomWord);
            }
        }
    }
}

