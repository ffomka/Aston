package test;

import builder.StudentBuilder;
import model.Student;
import util.MultiThreadCounter;

import java.util.ArrayList;
import java.util.List;

/**
 * Тесты для MultiThreadCounter.
 */
public class MultiThreadCounterTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        System.out.println("========== MultiThreadCounterTest ==========");

        testEmptyList();
        testSingleElementMatch();
        testSingleElementNoMatch();
        testMultipleMatches();
        testNoMatches();
        testAllMatch();
        testMultipleThreads();
        testSingleThread();
        testNullTarget();
        testManyThreads();

        printSummary();
    }

    private static void testEmptyList() {
        System.out.println("\n[Тест] Пустой список");
        List<Student> list = new ArrayList<>();
        MultiThreadCounter<Student> counter = new MultiThreadCounter<>();
        int count = counter.countOccurrences(list, createStudent(100, 4.0, 1), 2);

        assertEquals(0, count, "пустой список");
    }

    private static void testSingleElementMatch() {
        System.out.println("\n[Тест] Один элемент — совпадение");
        Student target = createStudent(100, 4.0, 1);
        List<Student> list = new ArrayList<>();
        list.add(target);

        MultiThreadCounter<Student> counter = new MultiThreadCounter<>();
        int count = counter.countOccurrences(list, target, 1);

        assertEquals(1, count, "один элемент — совпадение");
    }

    private static void testSingleElementNoMatch() {
        System.out.println("\n[Тест] Один элемент — нет совпадения");
        Student target = createStudent(100, 4.0, 1);
        Student different = createStudent(200, 4.0, 1);
        List<Student> list = new ArrayList<>();
        list.add(different);

        MultiThreadCounter<Student> counter = new MultiThreadCounter<>();
        int count = counter.countOccurrences(list, target, 1);

        assertEquals(0, count, "один элемент — нет совпадения");
    }

    private static void testMultipleMatches() {
        System.out.println("\n[Тест] Несколько совпадений");
        Student target = createStudent(100, 4.0, 1);
        List<Student> list = new ArrayList<>();
        list.add(createStudent(100, 4.0, 1));
        list.add(createStudent(200, 3.5, 2));
        list.add(createStudent(100, 4.0, 1));
        list.add(createStudent(300, 5.0, 3));
        list.add(createStudent(100, 4.0, 1));

        MultiThreadCounter<Student> counter = new MultiThreadCounter<>();
        int count = counter.countOccurrences(list, target, 2);

        assertEquals(3, count, "несколько совпадений");
    }

    private static void testNoMatches() {
        System.out.println("\n[Тест] Нет совпадений");
        Student target = createStudent(999, 9.9, 999);
        List<Student> list = new ArrayList<>();
        list.add(createStudent(100, 4.0, 1));
        list.add(createStudent(200, 3.5, 2));
        list.add(createStudent(300, 5.0, 3));

        MultiThreadCounter<Student> counter = new MultiThreadCounter<>();
        int count = counter.countOccurrences(list, target, 2);

        assertEquals(0, count, "нет совпадений");
    }

    private static void testAllMatch() {
        System.out.println("\n[Тест] Все элементы совпадают");
        Student target = createStudent(100, 4.0, 1);
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(target);
        }

        MultiThreadCounter<Student> counter = new MultiThreadCounter<>();
        int count = counter.countOccurrences(list, target, 4);

        assertEquals(10, count, "все элементы совпадают");
    }

    private static void testMultipleThreads() {
        System.out.println("\n[Тест] Много потоков (больше элементов)");
        Student target = createStudent(42, 3.0, 42);
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (i % 7 == 0) {
                list.add(target);
            } else {
                list.add(createStudent(i, 2.0 + i, i + 1000));
            }
        }

        MultiThreadCounter<Student> counter = new MultiThreadCounter<>();
        int count = counter.countOccurrences(list, target, 10);

        // Ожидаем 14 совпадений (0, 7, 14, ..., 98)
        assertEquals(14, count, "много потоков (10)");
    }

    private static void testSingleThread() {
        System.out.println("\n[Тест] Один поток");
        Student target = createStudent(50, 4.5, 50);
        List<Student> list = new ArrayList<>();
        list.add(createStudent(50, 4.5, 50));
        list.add(createStudent(100, 3.0, 1));
        list.add(createStudent(50, 4.5, 50));

        MultiThreadCounter<Student> counter = new MultiThreadCounter<>();
        int count = counter.countOccurrences(list, target, 1);

        assertEquals(2, count, "один поток");
    }

    private static void testNullTarget() {
        System.out.println("\n[Тест] Null-список");
        MultiThreadCounter<Student> counter = new MultiThreadCounter<>();
        int count = counter.countOccurrences(null, createStudent(1, 1.0, 1), 2);

        assertEquals(0, count, "null-список");
    }

    private static void testManyThreads() {
        System.out.println("\n[Тест] Много потоков (100)");
        Student target = createStudent(777, 5.0, 777);
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            if (i % 50 == 0) {
                list.add(target);
            } else {
                list.add(createStudent(i, 1.0 + (i % 10), i));
            }
        }

        MultiThreadCounter<Student> counter = new MultiThreadCounter<>();
        int count = counter.countOccurrences(list, target, 100);

        // Ожидаем 20 совпадений (0, 50, 100, ..., 950)
        assertEquals(20, count, "100 потоков");
    }

    private static Student createStudent(int groupNumber, double averageGrade, int recordBookNumber) {
        return new StudentBuilder()
                .groupNumber(groupNumber)
                .averageGrade(averageGrade)
                .recordBookNumber(recordBookNumber)
                .build();
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
            System.out.println("Все тесты MultiThreadCounterTest пройдены!");
        } else {
            System.out.println("Есть неудачные тесты!");
            System.exit(1);
        }
        System.out.println("========================================");
    }
}
