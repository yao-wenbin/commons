package io.github.yaowenbin.commons.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.OpenOption;
import java.nio.file.Path;

public class Files {

    public static void string2File(String str, File file) throws IOException {
        string2File(str, file.getAbsolutePath());
    }

    public static void string2File(String str, String filename) throws IOException {
        File file = new File(filename);
        File fileParent = file.getParentFile();
        if (fileParent != null) {
            fileParent.mkdirs();
        }

        OutputStream os = null;
        try {
            os = java.nio.file.Files.newOutputStream(file.toPath());
            os.write(str.getBytes(StandardCharsets.UTF_8));
        } finally {
            if (null != os) {
                os.close();
            }
        }
    }

    public static InputStream newInputStream(Path path, OpenOption... options) throws IOException {
        return java.nio.file.Files.newInputStream(path, options);
    }

    public static OutputStream newOutPutStream(Path path, OpenOption... options) throws IOException {
        return java.nio.file.Files.newOutputStream(path, options);
    }

    public static Path createFile(Path filePath) throws IOException {
        return java.nio.file.Files.createFile(filePath);
    }

    public static Path writeString(Path path, String s) throws IOException {
        return java.nio.file.Files.write(path, s.getBytes(StandardCharsets.UTF_8));
    }

    public static FileWatcher watch(Path filePath, FileWatcher.Listener listener) {
        return watch(filePath, listener, 500 /* 0.5s */);
    }

    public static FileWatcher watch(Path filePath, FileWatcher.Listener listener , long interval) {
        FileWatcher fileWatcher = new FileWatcher(filePath, listener, interval);
        fileWatcher.start();
        return fileWatcher;
    }
}
