package wordPlaybox;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Primary
public class GeneratorImpl implements Generator {
    private Random random = new Random();

    @Override
    public List<String> generateWords(Integer count, List<String> dictionary) {
        return IntStream.generate(() -> random.nextInt(dictionary.size()))
                        .limit(count)
                        .mapToObj(dictionary::get)
                        .collect(Collectors.toList());
    }

    @Override
    public String generateSingleWord(List<String> dictionary) {
        return dictionary.get(random.nextInt(dictionary.size()));
    }
}

