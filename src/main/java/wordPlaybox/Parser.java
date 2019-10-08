package wordPlaybox;

import java.util.Map;

public interface Parser {
    Map<String, String> parseCmdLine(String[] args);

    void printHelp();

    default void checkRequiredParameters(Map<String, String> params) {
        String action = params.getOrDefault("action", "").toLowerCase();
        switch (action) {
            case "generate": {
                if (!params.containsKey("count"))
                    throw new IllegalArgumentException("Mandatory parameter 'count' missing");
                if (!params.containsKey("dictionary"))
                    throw new IllegalArgumentException("Mandatory parameter 'dictionary' missing");
                if (!params.containsKey("output"))
                    throw new IllegalArgumentException("Mandatory parameter 'output' missing");
                break;
            }
            case "analyze": {
                if (!params.containsKey("input"))
                    throw new IllegalArgumentException("Mandatory parameter 'input' missing");
                break;
            }
            case "concurrent": {
                if (!params.containsKey("count"))
                    throw new IllegalArgumentException("Mandatory parameter 'count' missing");
                if (!params.containsKey("dictionary"))
                    throw new IllegalArgumentException("Mandatory parameter 'dictionary' missing");
                break;
            }
        }
    }
}
