package pl.itacademy;

import java.io.IOException;
import java.util.Map;

public class App {
    public static void main(String[] args) throws IOException {
        Parser parser = new Parser1();
        Map<String, String> params = parser.parseCmdLine(args);
        System.out.println(params);
        switch (params.getOrDefault("action", "")) {
            case "generate": {
                Generator generator = new Generator1();
                generator.generateWords(Integer.valueOf(params.get("count")), params.get("dictionary"), params.get("output"));
                break;
            }
            case "analyze": {
                Analyzer analyzer = new Analyzer1();
                analyzer.analyzeWords(params.get("input"));
                break;
            }
            default: {
                parser.printHelp();
            }
        }
    }
}
