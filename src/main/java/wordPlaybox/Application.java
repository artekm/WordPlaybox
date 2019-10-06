package wordPlaybox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private Parser parser;
    private Generator generator;
    private Analyzer analyzer;

    @Autowired
    public void setParser(Parser parser) {
        this.parser = parser;
    }

    @Autowired
    public void setGenerator(Generator generator) {
        this.generator = generator;
    }

    @Autowired
    public void setAnalyzer(Analyzer analyzer) {
        this.analyzer = analyzer;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, String> params = parser.parseCmdLine(args);
        System.out.println(params);
        String action = params.getOrDefault("action", "");
        Instant startTime = Instant.now();
        switch (action) {
            case "generate": {
                List<String> dictionary = Files.readAllLines(Paths.get(params.get("dictionary")));
                List<String> randomWords = generator.generateWords(Integer.valueOf(params.get("count")), dictionary);
                Files.write(Paths.get(params.get("output")), randomWords);
                break;
            }
            case "analyze": {
                List<String> words = Files.readAllLines(Paths.get(params.get("input")));
                Map<String, Long> wordsOccurrence = analyzer.analyzeWords(words);
                Iterable<String> mapIterable = () -> wordsOccurrence.entrySet().stream()
                                                                    .map(e -> e.getKey() + " >> " + e.getValue())
                                                                    .iterator();
                Files.write(Paths.get("result.txt"), mapIterable);
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
