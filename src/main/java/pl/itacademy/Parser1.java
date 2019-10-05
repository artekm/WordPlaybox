package pl.itacademy;

import org.apache.commons.cli.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Parser1 implements Parser {
    private final Options options;

    Parser1() {
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
            if (cmd.hasOption("action")) params.put("action", cmd.getOptionValue("action"));
            if (cmd.hasOption("count")) params.put("count", cmd.getOptionValue("count"));
            if (cmd.hasOption("dictionary")) params.put("dictionary", cmd.getOptionValue("dictionary"));
            if (cmd.hasOption("input")) params.put("input", cmd.getOptionValue("input"));
            if (cmd.hasOption("output")) params.put("output", cmd.getOptionValue("output"));
            checkRequiredParameters(params);
            return params;
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getCause());
        }
    }

    @Override
    public void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("WordPlaybox", options);
    }
}
