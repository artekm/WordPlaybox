package wordPlaybox;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class GeneratorImpl2 implements Generator {
    private Random random = new Random();

    @Override
    public List<String> generateWords(Integer count, List<String> dictionary) {
        List<String> randomWords = new ArrayList<>();
        for (int num = 0; num < count; num++) {
            randomWords.add(dictionary.get(random.nextInt(dictionary.size())));
        }
        return randomWords;
    }

    @Override
    public String nextRandomWord(List<String> dictionary) {
        return dictionary.get(random.nextInt(dictionary.size()));
    }
}
