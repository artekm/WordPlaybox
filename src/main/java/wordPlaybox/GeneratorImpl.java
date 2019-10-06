package wordPlaybox;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@SuppressWarnings("Convert2MethodRef")
public class GeneratorImpl implements Generator {
    @Override
    public List<String> generateWords(Integer count, List<String> dictionary) {
        Random rnd = new Random();
        return IntStream.generate(() -> rnd.nextInt(dictionary.size()))
                        .limit(count)
                        .mapToObj(index -> dictionary.get(index))
                        .collect(Collectors.toList());
    }
}

