package wordPlaybox;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GeneratorTest {

    @Autowired
    Generator generator;

    @Test
    public void generateWords_returnsRandomWords_forGivenDictionary() {
        List<String> dictionary = Arrays.asList("Jeden", "Dwa", "Trzy", "Cztery");
        List<String> words = generator.generateWords(10, dictionary);
        assertEquals(10, words.size());
        assertTrue(dictionary.containsAll(words));
    }

    @Test
    public void generateSingleWord_returnsRandomWord_forGivenDictionary() {
        List<String> dictionary = Arrays.asList("Jeden", "Dwa", "Trzy", "Cztery");
        for (int repeat = 0; repeat < 10; repeat++) {
            String word = generator.generateSingleWord(dictionary);
            assertTrue(dictionary.contains(word));
        }
    }

}
