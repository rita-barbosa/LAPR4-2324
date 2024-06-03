package jobs4u.base.applicationmanagement.application;

import jobs4u.base.applicationmanagement.domain.ApplicationFile;
import jobs4u.base.applicationmanagement.domain.FileWordCountThread;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

//TODO CHANGE PRINT STACKTRACE
public class ApplicationFilesThreadService {

    public Map<String, Pair<Integer, List<String>>> getTop20Words(Set<ApplicationFile> applicationFiles) {
        Map<String, Map<String, Integer>> top20Words = new HashMap<>();
        List<Thread> threads = new ArrayList<>();

        for (ApplicationFile applicationFile : applicationFiles) {
            threads.add(new Thread(new FileWordCountThread(applicationFile)));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return sortDescendingTop20Words(top20Words);
    }

    private Map<String, Pair<Integer, List<String>>> sortDescendingTop20Words(Map<String, Map<String, Integer>> top20Words) {
        Map<String, Pair<Integer, List<String>>> processedWords = processResult(top20Words);

        List<Map.Entry<String, Pair<Integer, List<String>>>> entryList = new ArrayList<>(processedWords.entrySet());
        entryList.sort((e1, e2) -> e2.getValue().getLeft().compareTo(e1.getValue().getLeft()));

        Map<String, Pair<Integer, List<String>>> sortedTop20Words = new LinkedHashMap<>();

        //if there's less than 20 entries that it only shows that
        int limit = Math.min(entryList.size(), 20);
        for (int i = 0; i < limit; i++) {
            Map.Entry<String, Pair<Integer, List<String>>> entry = entryList.get(i);
            sortedTop20Words.put(entry.getKey(), entry.getValue());
        }

        return sortedTop20Words;
    }

    private Map<String, Pair<Integer, List<String>>> processResult(Map<String, Map<String, Integer>> top20Words) {
        Map<String, Pair<Integer, List<String>>> processedWords = new HashMap<>();
        Map<String, Integer> entryMap;
        List<String> filenames = new ArrayList<>();
        int wordCount;

        for (Map.Entry<String, Map<String, Integer>> entry : top20Words.entrySet()) {
            wordCount = 0;
            entryMap = entry.getValue();
            for (Map.Entry<String, Integer> word : entryMap.entrySet()) {
                wordCount += word.getValue();
                filenames.add(entry.getKey());
            }
            processedWords.put(entry.getKey(), Pair.of(wordCount, filenames));

            filenames.clear();
        }
        return processedWords;
    }
}
