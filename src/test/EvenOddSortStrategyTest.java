package test;

import builder.StudentBuilder;
import comparator.StudentByGroupNumberComparator;
import model.Student;
import strategy.EvenOddSortStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Тесты для EvenOddSortStrategy.
 */
public class EvenOddSortStrategyTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        System.out.println("========== EvenOddSortStrategyTest ==========");

        testBasicEvenOddSort();
        testAllEven();
        testAllOdd();
        testNoEven();
        testSingleElement();
        testEmptyList();
        testEvenOddSortByRecordBookNumber();

        printSummary();
    }

    /**
     * Базовый тест: чётные groupNumber сортируются, нечётные остаются на месте.
     */
    private static void testBasicEvenOddSort() {
        System.out.println("\n[Тест] Базовая сортировка чётных/нечётных");
        List<Student> list = new ArrayList<>();
        // groupNumber: 301(нечёт), 200(чёт), 100(чёт), 401(нечёт), 500(чёт)
        list.add(new StudentBuilder().groupNumber(301).averageGrade(3.0).recordBookNumber(1).build());
        list.add(new StudentBuilder().groupNumber(200).averageGrade(3.0).recordBookNumber(2).build());
        list.add(new StudentBuilder().groupNumber(100).averageGrade(3.0).recordBookNumber(3).build());
        list.add(new StudentBuilder().groupNumber(401).averageGrade(3.0).recordBookNumber(4).build());
        list.add(new StudentBuilder().groupNumber(500).averageGrade(3.0).recordBookNumber(5).build());

        // Индексы чётных: 1 (200), 2 (100), 4 (500)
        // После сортировки чётных: 100, 200, 500
        // Результат: 301, 100, 200, 401, 500

        EvenOddSortStrategy<Student> strategy = new EvenOddSortStrategy<>(
                new StudentByGroupNumberComparator(),
                Student::getGroupNumber
        );
        strategy.sort(list);

        // Нечётные на своих местах
        assertEquals(301, list.get(0).getGroupNumber(), "индекс 0 (нечётный, остался)");
        assertEquals(401, list.get(3).getGroupNumber(), "индекс 3 (нечётный, остался)");

        // Чётные отсортированы
        assertEquals(100, list.get(1).getGroupNumber(), "индекс 1 (чётный, отсортирован)");
        assertEquals(200, list.get(2).getGroupNumber(), "индекс 2 (чётный, отсортирован)");
        assertEquals(500, list.get(4).getGroupNumber(), "индекс 4 (чётный, отсортирован)");

        pass("Базовая сортировка чётных/нечётных прошла");
    }

    /**
     * Все элементы с чётным значением.
     */
    private static void testAllEven() {
        System.out.println("\n[Тест] Все элементы чётные");
        List<Student> list = new ArrayList<>();
        list.add(new StudentBuilder().groupNumber(400).averageGrade(3.0).recordBookNumber(1).build());
        list.add(new StudentBuilder().groupNumber(100).averageGrade(3.0).recordBookNumber(2).build());
        list.add(new StudentBuilder().groupNumber(300).averageGrade(3.0).recordBookNumber(3).build());

        EvenOddSortStrategy<Student> strategy = new EvenOddSortStrategy<>(
                new StudentByGroupNumberComparator(),
                Student::getGroupNumber
        );
        strategy.sort(list);

        assertEquals(100, list.get(0).getGroupNumber(), "индекс 0");
        assertEquals(300, list.get(1).getGroupNumber(), "индекс 1");
        assertEquals(400, list.get(2).getGroupNumber(), "индекс 2");

        pass("Все чётные: полностью отсортировано");
    }

    /**
     * Все элементы с нечётным значением.
     */
    private static void testAllOdd() {
        System.out.println("\n[Тест] Все элементы нечётные");
        List<Student> list = new ArrayList<>();
        list.add(new StudentBuilder().groupNumber(301).averageGrade(3.0).recordBookNumber(1).build());
        list.add(new StudentBuilder().groupNumber(101).averageGrade(3.0).recordBookNumber(2).build());
        list.add(new StudentBuilder().groupNumber(501).averageGrade(3.0).recordBookNumber(3).build());

        int[] original = {301, 101, 501};
        EvenOddSortStrategy<Student> strategy = new EvenOddSortStrategy<>(
                new StudentByGroupNumberComparator(),
                Student::getGroupNumber
        );
        strategy.sort(list);

        // Нечётные не должны измениться
        assertEquals(original[0], list.get(0).getGroupNumber(), "индекс 0 (нечётный)");
        assertEquals(original[1], list.get(1).getGroupNumber(), "индекс 1 (нечётный)");
        assertEquals(original[2], list.get(2).getGroupNumber(), "индекс 2 (нечётный)");

        pass("Все нечётные: порядок не изменён");
    }

    /**
     * Нет чётных элементов.
     */
    private static void testNoEven() {
        System.out.println("\n[Тест] Нет чётных элементов");
        List<Student> list = new ArrayList<>();
        list.add(new StudentBuilder().groupNumber(7).averageGrade(3.0).recordBookNumber(1).build());
        list.add(new StudentBuilder().groupNumber(3).averageGrade(3.0).recordBookNumber(2).build());
        list.add(new StudentBuilder().groupNumber(5).averageGrade(3.0).recordBookNumber(3).build());

        EvenOddSortStrategy<Student> strategy = new EvenOddSortStrategy<>(
                new StudentByGroupNumberComparator(),
                Student::getGroupNumber
        );
        strategy.sort(list);

        assertEquals(7, list.get(0).getGroupNumber(), "индекс 0");
        assertEquals(3, list.get(1).getGroupNumber(), "индекс 1");
        assertEquals(5, list.get(2).getGroupNumber(), "индекс 2");

        pass("Нет чётных: порядок не изменён");
    }

    /**
     * Один элемент.
     */
    private static void testSingleElement() {
        System.out.println("\n[Тест] Один элемент");
        List<Student> list = new ArrayList<>();
        list.add(new StudentBuilder().groupNumber(200).averageGrade(4.5).recordBookNumber(100).build());

        EvenOddSortStrategy<Student> strategy = new EvenOddSortStrategy<>(
                new StudentByGroupNumberComparator(),
                Student::getGroupNumber
        );
        strategy.sort(list);

        assertEquals(200, list.get(0).getGroupNumber(), "единственный элемент");
        pass("Один элемент: не изменился");
    }

    /**
     * Пустой список.
     */
    private static void testEmptyList() {
        System.out.println("\n[Тест] Пустой список");
        List<Student> list = new ArrayList<>();

        EvenOddSortStrategy<Student> strategy = new EvenOddSortStrategy<>(
                new StudentByGroupNumberComparator(),
                Student::getGroupNumber
        );

        try {
            strategy.sort(list);
            pass("Пустой список: без исключений");
        } catch (Exception e) {
            fail("Пустой список: " + e.getMessage());
        }
    }

    /**
     * Сортировка по recordBookNumber (чётные/нечётные).
     */
    private static void testEvenOddSortByRecordBookNumber() {
        System.out.println("\n[Тест] Even-Odd Sort по recordBookNumber");
        List<Student> list = new ArrayList<>();
        // recordBookNumber: 101(нечёт), 200(чёт), 300(чёт), 401(нечёт)
        list.add(new StudentBuilder().groupNumber(1).averageGrade(3.0).recordBookNumber(101).build());
        list.add(new StudentBuilder().groupNumber(2).averageGrade(3.0).recordBookNumber(200).build());
        list.add(new StudentBuilder().groupNumber(3).averageGrade(3.0).recordBookNumber(300).build());
        list.add(new StudentBuilder().groupNumber(4).averageGrade(3.0).recordBookNumber(401).build());

        EvenOddSortStrategy<Student> strategy = new EvenOddSortStrategy<>(
                new comparator.StudentByRecordBookNumberComparator(),
                Student::getRecordBookNumber
        );
        strategy.sort(list);

        // Нечётные на месте
        assertEquals(101, list.get(0).getRecordBookNumber(), "индекс 0 (нечётный)");
        assertEquals(401, list.get(3).getRecordBookNumber(), "индекс 3 (нечётный)");

        // Чётные отсортированы: 200, 300
        assertEquals(200, list.get(1).getRecordBookNumber(), "индекс 1 (чётный)");
        assertEquals(300, list.get(2).getRecordBookNumber(), "индекс 2 (чётный)");

        pass("Even-Odd Sort по recordBookNumber прошла");
    }

    private static void assertEquals(int expected, int actual, String message) {
        if (expected != actual) {
            fail(message + ": expected " + expected + " but got " + actual);
        } else {
            pass(message + " == " + expected);
        }
    }

    private static void pass(String message) {
        passed++;
        System.out.println("  ✓ PASS: " + message);
    }

    private static void fail(String message) {
        failed++;
        System.out.println("  ✗ FAIL: " + message);
    }

    private static void assertEquals(boolean condition, String message) {
        if (condition) {
            pass(message);
        } else {
            fail(message);
        }
    }

    private static void printSummary() {
        System.out.println("\n========================================");
        System.out.println("ИТОГО: passed=" + passed + ", failed=" + failed);
        if (failed == 0) {
            System.out.println("Все тесты EvenOddSortStrategyTest пройдены!");
        } else {
            System.out.println("Есть неудачные тесты!");
            System.exit(1);
        }
        System.out.println("========================================");
    }
}
