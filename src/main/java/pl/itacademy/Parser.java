package pl.itacademy;

import org.apache.commons.cli.*;

public class Parser {
    private Options options;

    public Parser() {
        options = new Options();
        options.addOption("action","action", true, "action (generate/analyze)");
        options.addOption("count","count", true, "desired words count");
        options.addOption("dictionary","dictionary", true, "input dictionary file name");
        options.addOption("input","input", true, "input file name");
        options.addOption("output","output", true, "output file name");
    }

    public CommandLineParams parseCmdLine(String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        CommandLineParams params = new CommandLineParams();
        params.setAction(cmd.getOptionValue("action"));
        params.setCount(cmd.getOptionValue("count"));
        params.setDictionary(cmd.getOptionValue("dictionary"));
        params.setInput(cmd.getOptionValue("input"));
        params.setOutput(cmd.getOptionValue("output"));
        return params;
    }

    public void printHelp(){
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "WordPlaybox", options );
    }
}
