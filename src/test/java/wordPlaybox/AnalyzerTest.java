package wordPlaybox;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AnalyzerTest {

    @Autowired
    Analyzer analyzer;

    @Test
    public void analyzeWords_returnsCorrectMap_forSampleData() {
        List<String> words = Arrays.asList("Jeden", "Dwa", "Dwa", "Jeden", "Trzy");
        Map<String, Long> occurrence = analyzer.analyzeWords(words);
        assertThat(occurrence).containsExactly(entry("Dwa", 2L), entry("Jeden", 2L), entry("Trzy", 1L));
    }

    @Test
    public void analyzeWords_returnsEmptyMap_forEmptyData() {
        List<String> words = Collections.emptyList();
        Map<String, Long> occurrence = analyzer.analyzeWords(words);
        assertThat(occurrence).isEmpty();
    }

}