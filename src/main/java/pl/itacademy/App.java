package pl.itacademy;

import java.io.IOException;
import java.util.Map;

public class App {
    public static void main(String[] args) throws IOException {
        Parser parser = new Parser2();
        Map<String, String> params = parser.parseCmdLine(args);
        switch (params.getOrDefault("action", "")) {
            case "generate": {
                Generator generator = new Generator2();
                generator.generateWords(Integer.valueOf(params.get("count")), params.get("dictionary"), params.get("output"));
                break;
            }
            case "analyze": {
                Analyzer analyzer = new Analyzer2();
                analyzer.analyzeWords(params.get("input"));
                break;
            }
            default: {
                parser.printHelp();
            }
        }
    }
}
