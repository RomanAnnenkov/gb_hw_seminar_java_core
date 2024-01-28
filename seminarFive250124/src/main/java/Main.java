import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        System.out.println("hw from seminar five");
        // 1. Написать функцию, создающую резервную копию всех файлов
        // в директории(без поддиректорий) во вновь созданную папку ./backup
        try {
            backupAllFilesFromDirectory(".");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void backupAllFilesFromDirectory(String pathToDirectory) throws IOException {
        Path bkpDir = Files.createDirectories(Path.of(pathToDirectory + File.separator + "backup"));
        for (File f : (Objects.requireNonNull(bkpDir.getParent().toFile().listFiles()))) {
            if (f.isFile()) {
                Files.copy(f.toPath(),
                        Path.of(bkpDir.toAbsolutePath() + File.separator + f.getName()),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }
}
