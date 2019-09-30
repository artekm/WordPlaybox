package pl.itacademy;

import org.apache.commons.cli.*;

import java.util.HashMap;
import java.util.Map;

public class Parser1 implements Parser {
    private Options options;

    public Parser1() {
        options = new Options();
        options.addOption("action", "action", true, "action (generate/analyze)");
        options.addOption("count", "count", true, "desired words count");
        options.addOption("dictionary", "dictionary", true, "input dictionary file name");
        options.addOption("input", "input", true, "input file name");
        options.addOption("output", "output", true, "output file name");
    }

    @Override
    public Map<String, String> parseCmdLine(String[] args) {
        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);
            Map<String, String> params = new HashMap<>();
            params.put("action", cmd.getOptionValue("action"));
            params.put("count", cmd.getOptionValue("count"));
            params.put("dictionary", cmd.getOptionValue("dictionary"));
            params.put("input", cmd.getOptionValue("input"));
            params.put("output", cmd.getOptionValue("output"));
            checkRequiredParameters(params);
            return params;
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getCause());
        }
    }

    private void checkRequiredParameters(Map<String, String> params) {
        if ("generate".equalsIgnoreCase(params.get("action"))) {
            if (!params.containsKey("count") || !params.containsKey("dictionary") || !params.containsKey("output")) {
                throw new IllegalArgumentException("Mandatory parameters missing");
            }
        }
        if ("analyze".equalsIgnoreCase(params.get("action"))) {
            if (!params.containsKey("input")) {
                throw new IllegalArgumentException("Mandatory parameters missing");
            }
        }
    }

    @Override
    public void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("WordPlaybox", options);
    }
}
