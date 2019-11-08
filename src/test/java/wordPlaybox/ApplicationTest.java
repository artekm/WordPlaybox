package wordPlaybox;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

    @MockBean
    private Parser parserMock;

    @Test
    public void concurrent_call() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("action", "concurrent");
        params.put("count", "100");
        params.put("dictionary", "singleword.txt");
        when(parserMock.parseCmdLine(any())).thenReturn(params);

        app.run("");
        verify(concurrentMock).generateAndAnalyzeWords(100, Collections.singletonList("jeden"));
        verifyNoMoreInteractions(concurrentMock);
    }

    @Test
    public void generate_call() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("action", "generate");
        params.put("count", "100");
        params.put("dictionary", "singleword.txt");
        params.put("output", "words.txt");
        when(parserMock.parseCmdLine(any())).thenReturn(params);

        app.run("");
        verify(generatorMock).generateWords(100, Collections.singletonList("jeden"));
        verifyNoMoreInteractions(generatorMock);
    }

    @Test
    public void analyze_call() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("action", "analyze");
        params.put("input", "singleword.txt");
        when(parserMock.parseCmdLine(any())).thenReturn(params);

        app.run("");
        verify(analyzerMock).analyzeWords(Collections.singletonList("jeden"));
        verifyNoMoreInteractions(analyzerMock);
    }
}