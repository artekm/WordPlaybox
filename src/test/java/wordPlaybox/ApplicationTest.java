package wordPlaybox;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private Application app;

    @MockBean
    private Generator generatorMock;

    @Test
    public void generatesWords() throws Exception {
        String[] args = "--action concurrent --count 100 --dictionary english10000.txt --output words.txt".split(" ");
        when(generatorMock.nextRandomWord(anyList())).thenReturn("abcdef");
        app.run(args);
        assertEquals("abcdef",generatorMock.nextRandomWord(Collections.emptyList()));
    }
}