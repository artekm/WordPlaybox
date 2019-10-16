package wordPlaybox;

import java.util.List;
import java.util.Map;

public interface Concurrent {
    Map<String, Integer> generateAndAnalyzeWords(Integer count, List<String> dictionary) throws Exception;
}
