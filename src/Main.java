import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Главный класс — чтение students.txt и обработка стримом.
 *
 * Формат students.txt (описание):
 *   Каждая запись начинается со строки: STUDENT|Имя
 *   Затем идут строки книг: BOOK|title|author|pages|year
 *   Пустая строка — разделитель между студентами.
 *   Пустые строки и строки, начинающиеся с #, игнорируются.
 *
 * Пример:
 *   STUDENT|Алексей Смирнов
 *   BOOK|Java для начинающих|Иванов И.И.|320|2023
 *   BOOK|Python за 7 дней|Петров П.П.|250|2022
 *
 *   STUDENT|Мария Козлова
 *   BOOK|Базы данных|Сидоров С.С.|410|2021
 *   BOOK|ООП|Иванов И.И.|280|2023
 */
public class Main {

    /**
     * Загружает студентов из текстового файла.
     *
     * @param path путь к файлу students.txt
     * @return список студентов
     * @throws IOException если файл не найден или не читается
     */
    public static List<Student> loadStudents(String path) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(path), StandardCharsets.UTF_8);
        List<Student> students = new ArrayList<>();

        Student currentStudent = null;

        // убираем BOM, если есть
        if (!lines.isEmpty() && lines.get(0).startsWith("\uFEFF")) {
            lines.set(0, lines.get(0).substring(1));
        }

        for (String line : lines) {
            String trimmed = line.trim();

            // пропускаем пустые строки и комментарии
            if (trimmed.isEmpty() || trimmed.startsWith("#")) {
                continue;
            }

            if (trimmed.startsWith("STUDENT|")) {
                // новая запись студента
                String name = trimmed.substring("STUDENT|".length()).trim();
                currentStudent = new Student(name);
                students.add(currentStudent);
            } else if (trimmed.startsWith("BOOK|") && currentStudent != null) {
                // парсим книгу: BOOK|title|author|pages|year
                // split("\\|", 5) — ограничиваем до 5 частей, чтобы корректно разбить
                String[] parts = trimmed.split("\\|", 5);
                if (parts.length == 5) {
                    String title = parts[1].trim();  // parts[0] = "BOOK"
                    String author = parts[2].trim();
                    int pages = Integer.parseInt(parts[3].trim());
                    int year = Integer.parseInt(parts[4].trim());

                    Book book = new Book(title, author, pages, year);
                    currentStudent.addBook(book);
                }
            }
        }

        return students;
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
    public static java.util.Optional<Integer> process(List<Student> students) {
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
            // Загружаем студентов из файла (путь относительно корня проекта)
            List<Student> students = loadStudents("students.txt");

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
