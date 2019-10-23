package wordPlaybox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
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

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, String> params = parser.parseCmdLine(args);
        Instant startTime = Instant.now();
        String action = params.getOrDefault("action", "");
        switch (action) {
            case "concurrent": {
                List<String> dictionary = Files.readAllLines(Paths.get(params.get("dictionary")));
                Integer count = Integer.valueOf(params.get("count"));
                Map<String, Integer> wordsOccurrence = concurrent.generateAndAnalyzeWords(count, dictionary);
                Files.write(Paths.get("result.txt"), wordsOccurrence.toString().getBytes());
                break;
            }
            case "generate": {
                List<String> dictionary = Files.readAllLines(Paths.get(params.get("dictionary")));
                Integer count = Integer.valueOf(params.get("count"));
                List<String> randomWords = generator.generateWords(count, dictionary);
                Files.write(Paths.get(params.get("output")), randomWords);
                break;
            }
            case "analyze": {
                List<String> words = Files.readAllLines(Paths.get(params.get("input")));
                Map<String, Long> wordsOccurrence = analyzer.analyzeWords(words);
                Iterable<String> mapAsIterable = () -> wordsOccurrence.entrySet().stream()
                                                                      .map(e -> e.getKey() + " >> " + e.getValue())
                                                                      .iterator();
                Files.write(Paths.get("result.txt"), mapAsIterable);
                break;
            }
            default: {
                parser.printHelp();
            }
        }
        Instant endTime = Instant.now();
        System.out.println("Task finished in " + Duration.between(startTime, endTime).toMillis() + " ms");
    }
}
