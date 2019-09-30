package pl.itacademy;

import java.io.IOException;

public interface Generator {
    void generateWords(Integer count, String dictionaryFilename, String outputFileName) throws IOException;
}
