package pl.itacademy;

import org.apache.commons.cli.ParseException;

public interface Parser {
    CommandLineParams parseCmdLine(String[] args) throws ParseException;

    void printHelp();
}
