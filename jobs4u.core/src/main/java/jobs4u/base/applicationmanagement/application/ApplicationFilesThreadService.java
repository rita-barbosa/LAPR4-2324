package jobs4u.base.applicationmanagement.application;

import jobs4u.base.applicationmanagement.domain.ApplicationFile;
import jobs4u.base.applicationmanagement.domain.FileWordCountThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ApplicationFilesThreadService {
    public Map<String, Map<String, Integer>> getTop20Words(Set<ApplicationFile> applicationFiles) {
        List<Thread> threads = new ArrayList<>();

        for (ApplicationFile applicationFile : applicationFiles) {
            threads.add(new Thread(new FileWordCountThread()));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        throw new UnsupportedOperationException("Not supported yet.");
    }
}
