package io.github.yaowenbin.commons.file;

import com.sun.istack.internal.NotNull;
import io.github.yaowenbin.commons.concurrency.Scheduler;
import io.github.yaowenbin.commons.map.Pair;

import java.nio.file.Path;

public class FileWatcher extends Scheduler {

    private final Pair<Path /* filePath */, Long /* lastModifyTimestamp*/> pair;

    private final Listener listener;

    private final long interval;

    private Thread thread;

    public FileWatcher(@NotNull Path path, Listener listener, long interval) {
        this.pair = new Pair<>(path, path.toFile().lastModified());
        this.listener = listener;
        this.interval = interval;
    }


    public void doSomething() {
        checkFilePath();
    }


    private void checkFilePath() {
        Path path = pair.key();
        if (pair.val() != path.toFile().lastModified()) {
            listener.onChange(path);
        }
    }

    public Path path() {
        return pair.key();
    }

    public long lastModified() {
        return pair.val();
    }

    public Listener getListener() {
        return listener;
    }

    public long getInterval() {
        return interval;
    }

    public boolean stopped() {
        return false;
    }

    public interface Listener {
        void onChange(Path path);
    }
}
