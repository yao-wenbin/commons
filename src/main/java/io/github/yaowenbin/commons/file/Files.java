package io.github.yaowenbin.commons.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Files {

    public static void write(File file, String str) throws IOException {
        createIfNotExist(file);

        try (OutputStream os = java.nio.file.Files.newOutputStream(file.toPath());){
            os.write(str.getBytes(StandardCharsets.UTF_8));
        }
    }

    /**
     * overwrite file of path's content with str.
     * @param path file path.
     * @param content content need to write in file.
     */
    public static void write(Path path, String content) throws IOException {
        write(path.toFile(), content);
    }

    public static String read(Path path) throws IOException {
        try (BufferedReader reader = newBufferedReader(path)) {
            return reader.lines().collect(Collectors.joining());
        }
    }

    public static String read(File file) throws IOException {
        return read(file.toPath());
    }

    public static InputStream newInputStream(Path path, OpenOption... options) throws IOException {
        return java.nio.file.Files.newInputStream(path, options);
    }

    public static BufferedReader newBufferedReader(Path path, OpenOption... options) throws IOException {
        Reader reader = new InputStreamReader(newInputStream(path));
        return new BufferedReader(reader);
    }

    public static OutputStream newOutPutStream(Path path, OpenOption... options) throws IOException {
        return java.nio.file.Files.newOutputStream(path, options);
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
        int[] arr = new int[]{};
        Arrays.asList(arr);
        return fileWatcher;
    }

    /**
     * make sure path's parent path exist.
     * if parent path not exist, this method will create path for it.
     * @param path any path in File system.
     */
    public static void makeSureParentExist(Path path) {
        File parent = path.toFile().getParentFile();
        if (parent == null || parent.exists()) {
            return;
        }
        parent.mkdirs();
    }


    public static Path create(Path filePath) throws IOException {
        return java.nio.file.Files.createFile(filePath);
    }

    public static Path createIfNotExist(Path path) throws IOException {
        if (exists(path)) {
            return path;
        }
        makeSureParentExist(path);

        return java.nio.file.Files.createFile(path);
    }

    public static Path createIfNotExist(String strPath) throws IOException{
        Path path = Paths.get(strPath);
        return createIfNotExist(path);
    }

    public static File createIfNotExist(File file) throws IOException{
        Path path = file.toPath();
        Path existPah = createIfNotExist(path);
        return existPah.toFile();
    }

    public static boolean exists(Path path) {
        return path != null && path.toFile().exists();
    }


}
