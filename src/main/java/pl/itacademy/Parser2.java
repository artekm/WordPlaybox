package pl.itacademy;

import java.util.HashMap;
import java.util.Map;

public class Parser2 implements Parser{
    private Map<String, String> makeParamsMap(String[] args) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            String key = args[i].replace("-", "");
            String value = args[i + 1];
            map.put(key, value);
        }
        System.out.println(map);
        return map;
    }

    @Override
    public CommandLineParams parseCmdLine(String[] args) {
        Map<String, String> paramsMap = makeParamsMap(args);
        CommandLineParams params = new CommandLineParams();
        params.setAction(paramsMap.get("action"));
        params.setCount(paramsMap.get("count"));
        params.setDictionary(paramsMap.get("dictionary"));
        params.setInput(paramsMap.get("input"));
        params.setOutput(paramsMap.get("output"));
        checkRequiredParameters(params);
        return params;
    }

    private void checkRequiredParameters(CommandLineParams params) {
        if ("generate".equalsIgnoreCase(params.getAction())) {
            if (params.getCount() == null || params.getDictionary() == null || params.getOutput() == null) {
                throw new IllegalArgumentException("Mandatory parameters missing");
            }
        }
        if ("analyze".equalsIgnoreCase(params.getAction())) {
            if (params.getInput() == null) {
                throw new IllegalArgumentException("Mandatory parameters missing");
            }
        }
    }

    @Override
    public void printHelp() {
        System.out.println("No help available in this implementation :-( Sorry");
    }
}

