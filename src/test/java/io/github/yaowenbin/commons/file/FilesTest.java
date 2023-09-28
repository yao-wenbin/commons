package io.github.yaowenbin.commons.file;

import io.github.yaowenbin.commons.string.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class FilesTest {

    @TempDir
    Path tmpDir;

    Path filePath;

    @BeforeEach
    void setUp() throws IOException {
        filePath = Files.createFile(tmpDir.resolve("common.txt"));
    }

    @Test
    void writeString() throws IOException {
        Files.writeString(filePath, "initial");
        InputStream inputStream = Files.newInputStream(filePath);

        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        String res = new String(bytes);

        assertThat(res).isEqualTo("initial");
    }


    @Test
    void watch() throws IOException, InterruptedException {
        Semaphore sm = new Semaphore(0);


        FileWatcher fileWatcher = Files.watch(filePath, (filePath) -> {
           System.out.println(Strings.format("file:{} changed", filePath));
            sm.release();
        });

        Files.writeString(filePath, "change");
        boolean result = sm.tryAcquire(1, 6000, TimeUnit.MILLISECONDS);

        assertThat(result).isTrue();
    }


    @Test
    @Disabled
    void watchStop() {
        FileWatcher fileWatcher = Files.watch(filePath, (filePath) -> {
            System.out.println(Strings.format("file:{} changed", filePath));
        });
        fileWatcher.shutdown();

        assertThat(fileWatcher.stopped()).isTrue();
    }

}
