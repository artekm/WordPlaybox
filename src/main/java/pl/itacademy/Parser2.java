package pl.itacademy;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
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
        return params;
    }

    @Override
    public void printHelp() {
        System.out.println("No help available in this implementation :-( Sorry");
    }
}

