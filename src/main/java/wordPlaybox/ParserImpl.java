package wordPlaybox;

import org.apache.commons.cli.*;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ParserImpl implements Parser {
    private final Options options;

    ParserImpl() {
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

            Map<String, String> params = options.getOptions().stream()
                                                .map(option -> option.getLongOpt().toLowerCase())
                                                .filter(cmd::hasOption)
                                                .collect(Collectors.toMap(Function.identity(),
                                                                        opt -> cmd.getOptionValue(opt).toLowerCase()));
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
