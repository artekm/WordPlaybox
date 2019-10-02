package pl.itacademy;

import java.util.Map;

public interface Parser {
    Map<String, String> parseCmdLine(String[] args);

    void printHelp();

    default void checkRequiredParameters(Map<String, String> params) {
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

}
