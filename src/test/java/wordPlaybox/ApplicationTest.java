package wordPlaybox;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    Application app;

    @Test
    public void generatesWords() throws Exception {
        String cmdLine = "--action generate --count 100000 --dictionary english10000.txt --output words.txt";
        app.setGenerator(new GeneratorImpl());
        app.run(cmdLine.split(" "));

    }
}