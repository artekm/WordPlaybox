package pl.itacademy;

import org.apache.commons.cli.ParseException;

import java.util.Map;

public interface Parser {
    Map<String, String> parseCmdLine(String[] args);

    void printHelp();
}
