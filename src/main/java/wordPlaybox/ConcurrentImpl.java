package wordPlaybox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
public class ConcurrentImpl implements Concurrent {

    @Autowired
    private Generator generator;

    @Override
    public Map<String, Integer> generateAndAnalyzeWords(Integer count, List<String> dictionary) throws Exception {
        Queue<String> queue = new ConcurrentLinkedQueue<>();
        ExecutorService es = Executors.newFixedThreadPool(2);
        es.execute(() -> {
            for (int i = 0; i < count; i++) {
                queue.offer(generator.nextRandomWord(dictionary));
            }
            queue.offer("KILL_ME");
        });
        Future<Map<String, Integer>> result = es.submit(() -> {
            String word;
            Map<String, Integer> wordsOccurrence = new TreeMap<>();
            do {
                while ((word = queue.poll()) == null) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        return wordsOccurrence;
                    }
                }
                if (word.equalsIgnoreCase("KILL_ME")) {
                    return wordsOccurrence;
                }
                Integer oldCount = wordsOccurrence.getOrDefault(word, 0);
                wordsOccurrence.put(word, oldCount + 1);
            } while (true);
        });
        Map<String, Integer> wordsOccurrence = result.get();
        es.shutdown();
        return wordsOccurrence;
    }
}
