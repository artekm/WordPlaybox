package wordPlaybox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private Parser parser;

    @Autowired
    private Generator generator;

    @Autowired
    private Analyzer analyzer;

    @Autowired
    private Concurrent concurrent;

    @Autowired
    Application application;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Override
    public void run(String... args) throws Exception {
        Map<String, String> params = parser.parseCmdLine(args);
        String action = params.getOrDefault("action", "");
        logger.info("Parameters: " + params.toString());
        switch (action) {
            case "concurrent": {
                application.performConcurrent(params);
                break;
            }
            case "generate": {
                application.performGenerate(params);
                break;
            }
            case "analyze": {
                application.performAnalyze(params);
                break;
            }
            default: {
                parser.printHelp();
            }
        }
    }

    public void performAnalyze(Map<String, String> params) throws IOException {
        List<String> words = Files.readAllLines(Paths.get(params.get("input")));
        Map<String, Long> wordsOccurrence = analyzer.analyzeWords(words);
        Iterable<String> mapAsIterable = () -> wordsOccurrence.entrySet().stream()
                                                              .map(e -> e.getKey() + " >> " + e.getValue())
                                                              .iterator();
        Files.write(Paths.get("result.txt"), mapAsIterable);
    }

    public void performGenerate(Map<String, String> params) throws IOException {
        List<String> dictionary = Files.readAllLines(Paths.get(params.get("dictionary")));
        Integer count = Integer.valueOf(params.get("count"));
        List<String> randomWords = generator.generateWords(count, dictionary);
        Files.write(Paths.get(params.get("output")), randomWords);
    }

    public void performConcurrent(Map<String, String> params) throws Exception {
        List<String> dictionary = Files.readAllLines(Paths.get(params.get("dictionary")));
        Integer count = Integer.valueOf(params.get("count"));
        Map<String, Integer> wordsOccurrence = concurrent.generateAndAnalyzeWords(count, dictionary);
        Files.write(Paths.get("result.txt"), wordsOccurrence.toString().getBytes());
    }
}
