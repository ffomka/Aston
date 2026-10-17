package ru.aston.hometask;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Главный класс — чтение students.json и обработка стримом.
 *
 * Формат students.json:
 *   Корневой массив объектов, каждый объект — студент:
 *   {
 *     "name": "Имя студента",
 *     "books": [
 *       {"title": "Название", "author": "Автор", "pages": 320, "year": 2023},
 *       ...
 *     ]
 *   }
 */
public class Main {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Загружает студентов из JSON-файла.
     *
     * @param path путь к файлу students.json
     * @return список студентов
     * @throws IOException если файл не найден или не читается
     */
    public static List<Student> loadStudents(String path) throws IOException {
        return MAPPER.readValue(
                Main.class.getClassLoader().getResourceAsStream(path),
                MAPPER.getTypeFactory().constructCollectionType(List.class, Student.class)
        );
    }

    /**
     * Обрабатывает список студентов одним стримом:
     *   1. peek — вывод студентов
     *   2. flatMap — собрать все книги всех студентов в один поток
     *   3. sorted — сортировка по количеству страниц (по возрастанию)
     *   4. distinct — убрать дубликаты (по equals: title+author+pages+year)
     *   5. filter — только книги после 2000 года
     *   6. limit(3) — первые 3
     *   7. map(Book::getYear) — извлечь год
     *   8. findFirst() — взять первый элемент
     *
     * @param students список студентов
     * @return Optional<Integer> — год первой книги, прошедшей все стадии, или пустой Optional
     */
    public static Optional<Integer> process(List<Student> students) {
        return students.stream()
                .peek(s -> System.out.println(s))
                .flatMap(s -> s.getBooks().stream())
                .sorted(java.util.Comparator.comparingInt(Book::getPages))
                .distinct()
                .filter(b -> b.getYear() > 2000)
                .limit(3)
                .map(Book::getYear)
                .findFirst();
    }

    public static void main(String[] args) {
        try {
            // Загружаем студентов из ресурсов (путь относительно src/main/resources)
            List<Student> students = loadStudents("students.json");

            System.out.println("\n--- Обработка стримом ---");

            // Вызываем process и выводим результат через map().orElse()
            String result = process(students)
                    .map(year -> "Год: " + year)
                    .orElse("Такая книга отсутствует");

            System.out.println("\n" + result);

        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }
}
