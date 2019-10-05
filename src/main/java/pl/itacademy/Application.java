package pl.itacademy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@SpringBootApplication
public class Application {
    private Parser parser;
    private Generator generator;
    private Analyzer analyzer;

    @Autowired
    public Application(@Qualifier("parser1") Parser parser,
                       @Qualifier("generator1") Generator generator,
                       @Qualifier("analyzer1") Analyzer analyzer) {
        this.parser = parser;
        this.generator = generator;
        this.analyzer = analyzer;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            Map<String, String> params = parser.parseCmdLine(args);
            System.out.println(params);
            String action = params.getOrDefault("action","");
            switch (action) {
                case "generate": {
                    Integer count = Integer.valueOf(params.get("count"));
                    generator.generateWords(count, params.get("dictionary"), params.get("output"));
                    break;
                }
                case "analyze": {
                    analyzer.analyzeWords(params.get("input"));
                    break;
                }
                default: {
                    parser.printHelp();
                }
            }
        };
    }
}
