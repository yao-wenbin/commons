package io.github.yaowenbin.commons.file;

import io.github.yaowenbin.commons.string.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
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
        filePath = Files.create(tmpDir.resolve("common.txt"));
    }

    @Test
    void writeString() throws IOException {

    }

    @Test
    void write() throws IOException {
        Files.write(filePath, "initial");

        String res = testRead(filePath.toString());

        assertThat(res).isEqualTo("initial");
    }

    @Test
    void write_shouldOverrideFileContent() throws IOException {
        Files.write(filePath, "initial");
        Files.write(filePath, "second initial");

        String res = testRead(filePath.toString());

        assertThat(res).isEqualTo("second initial");
    }

    @Test
    void read() throws IOException {
        Files.write(filePath, "Hello World");
        String res = Files.read(filePath);
        assertThat(res).isEqualTo("Hello World");
    }

    @Test
    void read_multiLines() throws IOException {
        String complexString = "Hello World \n Foo Bar \t Yes";

        Files.write(filePath, complexString);
        String res = Files.read(filePath);
        assertThat(res).isEqualTo("Hello World  Foo Bar \t Yes");
    }

    @Test
    void exists_path() throws IOException {
        boolean res = Files.exists(null);
        assertThat(res).isFalse();

        Path randonPath = tmpDir.resolve("askdlfjskljfklas");
        res = Files.exists(randonPath);
        assertThat(res).isFalse();

        Path existsPath = Files.createIfNotExist(randonPath);
        res = Files.exists(existsPath);
        assertThat(res).isTrue();
    }

    @Test
    void makeSureParentExist() {
        Path multiUnExistsPath = tmpDir.resolve("notExistsDirLevel1").resolve("notExistsDirLevel2").resolve("notExistsDirLevel3").resolve("notExistsFile");

        Files.makeSureParentExist(multiUnExistsPath);
        boolean parentExists = multiUnExistsPath.getParent().toFile().exists();
        assertThat(parentExists).isTrue();
    }

    @Test
    void createIfNotExist_withPath() throws IOException {
        Path unexistPath = tmpDir.resolve("notExistsDirLevel1").resolve("notExistsDirLevel2").resolve("ksadjflksajfklsajflksajldf.txt");
        System.out.println(unexistPath);

        Path existPath = Files.createIfNotExist(unexistPath);

        Path res = Files.createIfNotExist(existPath);
        assertThat(res.toFile().exists()).isTrue();
    }

    @Test
    void createIfNotExist_withString() throws IOException {
        String unexistPath = tmpDir.resolve("ksadjflksajfklsajflksajldf.txt").toString();

        Path existPath = Files.createIfNotExist(unexistPath);
        assertThat(existPath.toFile().exists()).isTrue();

        Path res = Files.createIfNotExist(existPath);
        assertThat(res.toFile().exists()).isTrue();
        assertThat(res.toString()).isEqualTo(unexistPath);
    }

    @Test
    void createIfNotExist_withFile() throws IOException {
        File unexistsFile = tmpDir.resolve("ksadjflksajfklsajflksajldfsadjfklasjdf.txt").toFile();

        File existsFile = Files.createIfNotExist(unexistsFile);

        assertThat(existsFile.exists()).isTrue();
        assertThat(existsFile.toString()).isEqualTo(unexistsFile.toString());
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
    void watchStop() {
        FileWatcher fileWatcher = Files.watch(filePath, (filePath) -> {
            System.out.println(Strings.format("file:{} changed", filePath));
        });
        fileWatcher.shutdown();

        assertThat(fileWatcher.stopped()).isTrue();
    }

    private String testRead(String file) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(file));
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        return new String(bytes);
    }

}
