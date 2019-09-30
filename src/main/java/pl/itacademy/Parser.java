package pl.itacademy;

import org.apache.commons.cli.ParseException;

public interface Parser {
    public CommandLineParams parseCmdLine(String[] args) throws ParseException;

    public void printHelp();
}
