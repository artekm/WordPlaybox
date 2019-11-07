package wordPlaybox;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private Application app;

    @MockBean
    private Concurrent concurrentMock;

    @MockBean
    private Generator generatorMock;

    @MockBean
    private Analyzer analyzerMock;

    @Test
    public void concurrent_call() throws Exception {
        String[] args = "--action concurrent --count 100 --dictionary english10000.txt --output words.txt".split(" ");
        app.run(args);
        verify(concurrentMock).generateAndAnalyzeWords(eq(100), anyList());
    }

    @Test
    public void generate_call() throws Exception {
        String[] args = "--action generate --count 100 --dictionary english10000.txt --output words.txt".split(" ");
        app.run(args);
        verify(generatorMock).generateWords(eq(100), anyList());
    }

    @Test
    public void analyze_call() throws Exception {
        String[] args = "--action analyze --input words.txt".split(" ");
        app.run(args);
        verify(analyzerMock).analyzeWords(anyList());
    }
}