package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

/**
 * Утилита для записи данных в файл в режиме добавления.
 */
public class FileWriterUtil {

    /**
     * Добавляет представление элементов коллекции в файл.
     * Каждый элемент записывается с новой строки.
     *
     * @param path   путь к файлу
     * @param data   коллекция данных для записи
     * @throws RuntimeException если произошла ошибка записи
     */
    public static void appendToFile(String path, Collection<?> data) {
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("Путь к файлу не может быть null или пустым");
        }

        if (data == null) {
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            for (Object item : data) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи в файл: " + path, e);
        }
    }
}
