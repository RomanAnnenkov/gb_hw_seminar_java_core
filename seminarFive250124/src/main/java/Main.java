import java.io.*;
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

        //Предположить, что числа в исходном массиве из 9 элементов имеют диапазон[0, 3],
        // и представляют собой, например, состояния ячеек поля для игры в крестикинолики,
        // где 0 – это пустое поле, 1 – это поле с крестиком, 2 – это поле с ноликом, 3 – резервное значение.
        // Такое предположение позволит хранить в одном числе типа int всё поле 3х3. Записать в файл 9 значений так,
        // чтобы они заняли три байта.
        int[] intArray = new int[]{0, 2, 0, 1, 2, 1, 0, 2, 3};
        saveArrayToFile(intArray, "array_file");

    }


    public static void saveArrayToFile(int[] arr, String fileName) {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(fileName))) {
            int counter = 0;
            byte capacitor = 0;
            for (int element : arr) {
                if (counter == 3) {
                    dataOutputStream.writeByte(capacitor);
                    capacitor = 0;
                    counter = 0;
                }
                capacitor += element << (counter * 2);
                counter++;
            }
            dataOutputStream.writeByte(capacitor);
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
