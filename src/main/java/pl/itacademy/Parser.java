package pl.itacademy;

import java.util.Map;

public interface Parser {
    Map<String, String> parseCmdLine(String[] args);

    void printHelp();

    default void checkRequiredParameters(Map<String, String> params) {
        if ("generate".equalsIgnoreCase(params.get("action"))) {
            if (!params.containsKey("count"))
                throw new IllegalArgumentException("Mandatory parameter 'count' missing");
            if (!params.containsKey("dictionary"))
                throw new IllegalArgumentException("Mandatory parameter 'dictionary' missing");
            if (!params.containsKey("output"))
                throw new IllegalArgumentException("Mandatory parameter 'output' missing");
        } else if ("analyze".equalsIgnoreCase(params.get("action"))) {
            if (!params.containsKey("input"))
                throw new IllegalArgumentException("Mandatory parameter 'input' missing");
        }
    }
}
