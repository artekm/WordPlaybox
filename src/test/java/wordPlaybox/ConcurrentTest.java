package wordPlaybox;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
//@ContextConfiguration(classes = Application.class, initializers = ConfigFileApplicationContextInitializer.class)
@RunWith(SpringRunner.class)
public class ConcurrentTest {

    @Autowired
    private Concurrent concurrent;

    @MockBean
    private Generator generatorMock;

    @Test
    public void generateAndAnalyzeWords_returnsCorrectOccurrence_forGivenData() throws Exception {
        when(generatorMock.generateWords(anyInt(), any()))
                .thenAnswer(invocation -> Stream.generate(() -> "Jeden")
                                                .limit((int) invocation.getArgument(0))
                                                .collect(Collectors.toList()));
        when(generatorMock.generateSingleWord(any())).thenReturn("Jeden");
        Map<String, Integer> occurrence = concurrent.generateAndAnalyzeWords(10, null);
        assertThat(occurrence).containsExactly(entry("Jeden", 10));
    }
}