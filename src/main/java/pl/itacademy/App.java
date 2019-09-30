package pl.itacademy;

import org.apache.commons.cli.ParseException;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws ParseException, IOException {
        Parser parser = new Parser();
        CommandLineParams params = parser.parseCmdLine(args);
        if ("generate".equalsIgnoreCase(params.getAction())) {
            Generator generator = new Generator3();
            generator.generateWords(Integer.valueOf(params.getCount()), params.getDictionary(), params.getOutput());
        } else if ("analyze".equalsIgnoreCase(params.getAction())) {
            Analyzer1 analyzer = new Analyzer1();
            analyzer.analyzeWords(params.getInput());
        } else
            parser.printHelp();
    }
}
