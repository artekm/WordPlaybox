package wordPlaybox;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ParserTest {

    @Autowired
    Parser parser;

    @Test
    public void parseCmdLine_returnsArguments_forConcurrent() {
        String[] args = "--action concurrent --count 100 --dictionary english10000.txt".split(" ");
        Map<String, String> params = parser.parseCmdLine(args);
        assertThat(params).contains(entry("action", "concurrent"), entry("count", "100"),
                entry("dictionary", "english10000.txt"));
    }

    @Test
    public void parseCmdLine_returnsArguments_forGenerate() {
        String[] args = "--action generate --count 100 --dictionary english10000.txt --output words.txt".split(" ");
        Map<String, String> params = parser.parseCmdLine(args);
        assertThat(params).contains(entry("action", "generate"), entry("count", "100"),
                entry("dictionary", "english10000.txt"), entry("output", "words.txt"));
    }

    @Test
    public void parseCmdLine_returnsArguments_forAnalyze() {
        String[] args = "--action analyze --input words.txt".split(" ");
        Map<String, String> params = parser.parseCmdLine(args);
        assertThat(params).contains(entry("action", "analyze"), entry("input", "words.txt"));
    }
}