package menu;

import builder.StudentBuilder;
import comparator.StudentByAverageGradeComparator;
import comparator.StudentByGroupNumberComparator;
import comparator.StudentByRecordBookNumberComparator;
import io.*;
import model.Student;
import strategy.BubbleSortStrategy;
import strategy.EvenOddSortStrategy;
import strategy.InsertionSortStrategy;
import strategy.QuickSortStrategy;
import strategy.SortStrategy;
import util.MultiThreadCounter;

import java.util.List;
import java.util.Scanner;

/**
 * Консольное меню приложения для управления коллекцией студентов.
 */
public class ConsoleMenu {

    private final CustomList<Student> students = new CustomList<>();
    private final Scanner scanner = new Scanner(System.in);

    private DataFiller<Student> currentFiller;
    private SortStrategy<Student> currentSortStrategy;
    private String currentSortFieldName;

    /**
     * Запускает меню приложения.
     */
    public void start() {
        System.out.println("========================================");
        System.out.println("  Приложение для управления студентами");
        System.out.println("========================================\n");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = readInput("Выберите действие: ");

            switch (choice) {
                case "1":
                    selectDataSource();
                    break;
                case "2":
                    setCollectionSize();
                    break;
                case "3":
                    selectSortClass();
                    break;
                case "4":
                    selectSortField();
                    break;
                case "5":
                    executeSort();
                    break;
                case "6":
                    saveToFile();
                    break;
                case "7":
                    countOccurrences();
                    break;
                case "8":
                    showCollection();
                    break;
                case "0":
                    System.out.println("Выход из программы. До свидания!");
                    running = false;
                    break;
                default:
                    System.out.println("Неверный пункт меню. Попробуйте снова.\n");
                    break;
            }

            if (running) {
                System.out.println("\n--- Нажмите Enter для продолжения ---");
                readInput("");
            }
        }
    }

    /**
     * Выводит пункты меню.
     */
    private void printMenu() {
        System.out.println("\n========================================");
        System.out.println("           ГЛАВНОЕ МЕНЮ");
        System.out.println("========================================");
        System.out.println(" 1. Выбрать источник данных (файл / рандом / вручную)");
        System.out.println(" 2. Задать длину коллекции");
        System.out.println(" 3. Выбрать алгоритм сортировки");
        System.out.println(" 4. Выбрать поле сортировки");
        System.out.println(" 5. Выполнить сортировку");
        System.out.println(" 6. Сохранить результат в файл");
        System.out.println(" 7. Подсчитать вхождения элемента");
        System.out.println(" 8. Показать текущую коллекцию");
        System.out.println(" 0. Выход");
        System.out.println("========================================");
    }

    /**
     * Выбор источника данных.
     */
    private void selectDataSource() {
        System.out.println("\n--- Выбор источника данных ---");
        System.out.println(" 1. Случайные данные (RandomStudentFiller)");
        System.out.println(" 2. Из файла (FileStudentFiller)");
        System.out.println(" 3. Вручную с консоли (ManualStudentFiller)");

        String choice = readInput("Ваш выбор: ");

        switch (choice) {
            case "1":
                currentFiller = new RandomStudentFiller();
                System.out.println("Выбран: Случайные данные");
                break;
            case "2":
                String filePath = readInput("Введите путь к файлу: ");
                currentFiller = new FileStudentFiller();
                System.out.println("Выбран: Загрузка из файла " + filePath);
                loadFromFile(filePath);
                break;
            case "3":
                currentFiller = new ManualStudentFiller();
                System.out.println("Выбран: Ручной ввод");
                break;
            default:
                System.out.println("Неверный выбор. Источник данных не изменён.");
                return;
        }

        System.out.println("Источник данных установлен: " + (currentFiller != null ? currentFiller.getClass().getSimpleName() : "не выбран"));
    }

    /**
     * Загрузка студентов из файла.
     */
    private void loadFromFile(String filePath) {
        if (currentFiller instanceof FileStudentFiller) {
            List<Student> loaded = ((FileStudentFiller) currentFiller).fillFromFile(filePath);
            clearCollection();
            for (Student student : loaded) {
                students.add(student);
            }
            System.out.println("Загружено " + loaded.size() + " студентов из файла.");
        }
    }

    /**
     * Задание размера коллекции и заполнение.
     */
    private void setCollectionSize() {
        if (currentFiller == null) {
            System.out.println("Сначала выберите источник данных (пункт 1).");
            return;
        }

        String sizeInput = readInput("Введите количество студентов: ");
        int size;
        try {
            size = Integer.parseInt(sizeInput);
            if (size <= 0) {
                System.out.println("Количество должно быть больше 0.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Некорректное число.");
            return;
        }

        System.out.println("Заполнение коллекции (" + size + " элементов)...");
        List<Student> filled = currentFiller.fill(size);

        clearCollection();
        for (Student student : filled) {
            students.add(student);
        }

        System.out.println("Коллекция заполнена. Всего студентов: " + students.size());
    }

    /**
     * Выбор алгоритма сортировки.
     */
    private void selectSortClass() {
        System.out.println("\n--- Выбор алгоритма сортировки ---");
        System.out.println(" 1. Bubble Sort (пузырьковая)");
        System.out.println(" 2. Quick Sort (быстрая)");
        System.out.println(" 3. Insertion Sort (вставками)");
        System.out.println(" 4. Even-Odd Sort (чётные/нечётные)");

        String choice = readInput("Ваш выбор: ");

        switch (choice) {
            case "1":
                currentSortStrategy = new BubbleSortStrategy<>(getDefaultComparator());
                System.out.println("Выбран: Bubble Sort");
                break;
            case "2":
                currentSortStrategy = new QuickSortStrategy<>(getDefaultComparator());
                System.out.println("Выбран: Quick Sort");
                break;
            case "3":
                currentSortStrategy = new InsertionSortStrategy<>(getDefaultComparator());
                System.out.println("Выбран: Insertion Sort");
                break;
            case "4":
                currentSortStrategy = new EvenOddSortStrategy<>(
                        getDefaultComparator(),
                        this::extractSortValue
                );
                System.out.println("Выбран: Even-Odd Sort");
                break;
            default:
                System.out.println("Неверный выбор. Алгоритм не изменён.");
                return;
        }

        System.out.println("Алгоритм сортировки установлен: " + currentSortStrategy.getClass().getSimpleName());
    }

    /**
     * Выбор поля сортировки.
     */
    private void selectSortField() {
        System.out.println("\n--- Выбор поля сортировки ---");
        System.out.println(" 1. Номер группы (groupNumber)");
        System.out.println(" 2. Средний балл (averageGrade)");
        System.out.println(" 3. Номер зачетной книжки (recordBookNumber)");

        String choice = readInput("Ваш выбор: ");

        switch (choice) {
            case "1":
                currentSortStrategy = new BubbleSortStrategy<>(new StudentByGroupNumberComparator());
                currentSortFieldName = "groupNumber";
                System.out.println("Выбрано поле: Номер группы");
                break;
            case "2":
                currentSortStrategy = new BubbleSortStrategy<>(new StudentByAverageGradeComparator());
                currentSortFieldName = "averageGrade";
                System.out.println("Выбрано поле: Средний балл");
                break;
            case "3":
                currentSortStrategy = new BubbleSortStrategy<>(new StudentByRecordBookNumberComparator());
                currentSortFieldName = "recordBookNumber";
                System.out.println("Выбрано поле: Номер зачетной книжки");
                break;
            default:
                System.out.println("Неверный выбор. Поле не изменено.");
                return;
        }

        System.out.println("Поле сортировки: " + currentSortFieldName);
    }

    /**
     * Выполнение сортировки.
     */
    private void executeSort() {
        if (students.size() == 0) {
            System.out.println("Коллекция пуста. Сначала заполните данные.");
            return;
        }

        if (currentSortStrategy == null) {
            System.out.println("Сначала выберите алгоритм сортировки (пункт 3).");
            return;
        }

        System.out.println("\n--- Выполнение сортировки ---");
        System.out.println("Алгоритм: " + currentSortStrategy.getClass().getSimpleName());
        System.out.println("Поле: " + (currentSortFieldName != null ? currentSortFieldName : "не выбрано"));
        System.out.println("Элементов: " + students.size());

        // Конвертируем CustomList в java.util.List для SortStrategy
        java.util.ArrayList<Student> listForSort = convertToArrayList();

        long startTime = System.nanoTime();
        currentSortStrategy.sort(listForSort);
        long endTime = System.nanoTime();

        // Обновляем CustomList отсортированными данными
        syncFromArrayList(listForSort);

        double durationMs = (endTime - startTime) / 1_000_000.0;
        System.out.println("Сортировка завершена за " + String.format("%.3f", durationMs) + " мс");
        System.out.println("Результат:");
        showCollection();
    }

    /**
     * Сохранение коллекции в файл.
     */
    private void saveToFile() {
        if (students.size() == 0) {
            System.out.println("Коллекция пуста. Нечего сохранять.");
            return;
        }

        String filePath = readInput("Введите путь к файлу для сохранения: ");
        System.out.println("Сохранение " + students.size() + " записей в " + filePath + "...");
        FileWriterUtil.appendToFile(filePath, convertToArrayList());
        System.out.println("Данные сохранены.");
    }

    /**
     * Подсчёт вхождений элемента (многопоточно).
     */
    private void countOccurrences() {
        if (students.size() == 0) {
            System.out.println("Коллекция пуста.");
            return;
        }

        System.out.println("\n--- Подсчёт вхождений ---");
        System.out.println("1. По номеру группы");
        System.out.println("2. По среднему баллу");
        System.out.println("3. По номеру зачетной книжки");

        String choice = readInput("Выберите поле: ");

        Student target;
        String fieldName;

        switch (choice) {
            case "1": {
                int groupNumber = readInt("Введите номер группы: ", "Некорректный номер группы");
                target = new StudentBuilder().groupNumber(groupNumber).averageGrade(0.0).recordBookNumber(1).build();
                fieldName = "groupNumber=" + groupNumber;
                break;
            }
            case "2": {
                double avgGrade = readDouble("Введите средний балл: ", "Некорректный средний балл");
                target = new StudentBuilder().groupNumber(1).averageGrade(avgGrade).recordBookNumber(1).build();
                fieldName = "averageGrade=" + avgGrade;
                break;
            }
            case "3": {
                int recordBook = readInt("Введите номер зачетной книжки: ", "Некорректный номер");
                target = new StudentBuilder().groupNumber(1).averageGrade(0.0).recordBookNumber(recordBook).build();
                fieldName = "recordBookNumber=" + recordBook;
                break;
            }
            default:
                System.out.println("Неверный выбор.");
                return;
        }

        int threadCount = readInt("Введите количество потоков: ", "Некорректное число потоков");

        MultiThreadCounter<Student> counter = new MultiThreadCounter<>();
        int count = counter.countOccurrences(
                convertToArrayList(), target, threadCount
        );

        System.out.println("Найдено " + count + " студентов с " + fieldName);
    }

    /**
     * Отображение текущей коллекции.
     */
    private void showCollection() {
        if (students.size() == 0) {
            System.out.println("Коллекция пуста.");
            return;
        }

        System.out.println("\n--- Текущая коллекция (" + students.size() + " студентов) ---");
        for (int i = 0; i < students.size(); i++) {
            System.out.printf("  [%d] %s%n", i + 1, students.get(i));
        }
        System.out.println("-----------------------------------------------");
    }

    /**
     * Очищает текущую коллекцию.
     */
    private void clearCollection() {
        while (students.size() > 0) {
            students.remove(0);
        }
    }

    /**
     * Получает компаратор по выбранному полю.
     */
    private java.util.Comparator<Student> getDefaultComparator() {
        if (currentSortFieldName == null) {
            return new StudentByGroupNumberComparator();
        }
        switch (currentSortFieldName) {
            case "groupNumber":
                return new StudentByGroupNumberComparator();
            case "averageGrade":
                return new StudentByAverageGradeComparator();
            case "recordBookNumber":
                return new StudentByRecordBookNumberComparator();
            default:
                return new StudentByGroupNumberComparator();
        }
    }

    /**
     * Извлекает числовое значение для Even-Odd сортировки.
     */
    private int extractSortValue(Student student) {
        if ("averageGrade".equals(currentSortFieldName)) {
            return (int) (student.getAverageGrade() * 10);
        }
        if ("recordBookNumber".equals(currentSortFieldName)) {
            return student.getRecordBookNumber();
        }
        return student.getGroupNumber();
    }

    /**
     * Конвертирует CustomList в java.util.ArrayList.
     */
    private java.util.ArrayList<Student> convertToArrayList() {
        java.util.ArrayList<Student> list = new java.util.ArrayList<>(students.size());
        for (int i = 0; i < students.size(); i++) {
            list.add(students.get(i));
        }
        return list;
    }

    /**
     * Синхронизирует CustomList с java.util.ArrayList.
     */
    private void syncFromArrayList(java.util.ArrayList<Student> list) {
        clearCollection();
        for (Student student : list) {
            students.add(student);
        }
    }

    /**
     * Читает одну строку ввода.
     */
    private String readInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Читает целое число с валидацией.
     */
    private int readInt(String prompt, String errorMessage) {
        while (true) {
            String input = readInput(prompt).trim();
            if (input.isEmpty()) {
                System.out.println("Поле не может быть пустым.");
                continue;
            }
            try {
                int value = Integer.parseInt(input);
                return value;
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }

    /**
     * Читает число с плавающей точкой с валидацией.
     */
    private double readDouble(String prompt, String errorMessage) {
        while (true) {
            String input = readInput(prompt).trim();
            if (input.isEmpty()) {
                System.out.println("Поле не может быть пустым.");
                continue;
            }
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }
}
