package pl.itacademy;

import java.util.HashMap;
import java.util.Map;

public class Parser2 implements Parser {
    @Override
    public Map<String, String> parseCmdLine(String[] args) {
        Map<String, String> params = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            String key = args[i].replace("-", "");
            String value = args[i + 1];
            params.put(key, value);
        }
        checkRequiredParameters(params);
        System.out.println(params);
        return params;
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
        System.out.println("No help available in this implementation :-( Sorry");
    }
}

