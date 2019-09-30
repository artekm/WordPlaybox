package pl.itacademy;

public class App {
    public static void main(String[] args) throws Exception {
        Parser parser = new Parser2();
        CommandLineParams params = parser.parseCmdLine(args);
        if ("generate".equalsIgnoreCase(params.getAction())) {
            Generator generator = new Generator2();
            generator.generateWords(Integer.valueOf(params.getCount()), params.getDictionary(), params.getOutput());
        } else if ("analyze".equalsIgnoreCase(params.getAction())) {
            Analyzer analyzer = new Analyzer1();
            analyzer.analyzeWords(params.getInput());
        } else
            parser.printHelp();
    }
}
