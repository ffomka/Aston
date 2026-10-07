package test;

import builder.StudentBuilder;
import comparator.StudentByAverageGradeComparator;
import comparator.StudentByGroupNumberComparator;
import comparator.StudentByRecordBookNumberComparator;
import model.Student;
import strategy.BubbleSortStrategy;
import strategy.InsertionSortStrategy;
import strategy.QuickSortStrategy;
import strategy.SortStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Тесты для SortStrategy (bubble, quick, insertion) на 3 полях.
 */
public class SortStrategyTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        System.out.println("========== SortStrategyTest ==========");

        testBubbleSortByGroupNumber();
        testBubbleSortByAverageGrade();
        testBubbleSortByRecordBookNumber();
        testQuickSortByGroupNumber();
        testQuickSortByAverageGrade();
        testQuickSortByRecordBookNumber();
        testInsertionSortByGroupNumber();
        testInsertionSortByAverageGrade();
        testInsertionSortByRecordBookNumber();
        testSortEmptyList();
        testSortSingleElement();
        testSortAlreadySorted();
        testSortReverseSorted();

        printSummary();
    }

    private static void testBubbleSortByGroupNumber() {
        System.out.println("\n[Тест] Bubble Sort — по groupNumber");
        List<Student> list = createUnsortedStudents();
        SortStrategy<Student> strategy = new BubbleSortStrategy<>(new StudentByGroupNumberComparator());
        strategy.sort(list);

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getGroupNumber() < list.get(i - 1).getGroupNumber()) {
                fail("Bubble Sort groupNumber: не отсортировано на индексе " + i);
                return;
            }
        }
        pass("Bubble Sort groupNumber: отсортировано");
    }

    private static void testBubbleSortByAverageGrade() {
        System.out.println("\n[Тест] Bubble Sort — по averageGrade");
        List<Student> list = createUnsortedStudents();
        SortStrategy<Student> strategy = new BubbleSortStrategy<>(new StudentByAverageGradeComparator());
        strategy.sort(list);

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getAverageGrade() < list.get(i - 1).getAverageGrade()) {
                fail("Bubble Sort averageGrade: не отсортировано на индексе " + i);
                return;
            }
        }
        pass("Bubble Sort averageGrade: отсортировано");
    }

    private static void testBubbleSortByRecordBookNumber() {
        System.out.println("\n[Тест] Bubble Sort — по recordBookNumber");
        List<Student> list = createUnsortedStudents();
        SortStrategy<Student> strategy = new BubbleSortStrategy<>(new StudentByRecordBookNumberComparator());
        strategy.sort(list);

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getRecordBookNumber() < list.get(i - 1).getRecordBookNumber()) {
                fail("Bubble Sort recordBookNumber: не отсортировано на индексе " + i);
                return;
            }
        }
        pass("Bubble Sort recordBookNumber: отсортировано");
    }

    private static void testQuickSortByGroupNumber() {
        System.out.println("\n[Тест] Quick Sort — по groupNumber");
        List<Student> list = createUnsortedStudents();
        SortStrategy<Student> strategy = new QuickSortStrategy<>(new StudentByGroupNumberComparator());
        strategy.sort(list);

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getGroupNumber() < list.get(i - 1).getGroupNumber()) {
                fail("Quick Sort groupNumber: не отсортировано на индексе " + i);
                return;
            }
        }
        pass("Quick Sort groupNumber: отсортировано");
    }

    private static void testQuickSortByAverageGrade() {
        System.out.println("\n[Тест] Quick Sort — по averageGrade");
        List<Student> list = createUnsortedStudents();
        SortStrategy<Student> strategy = new QuickSortStrategy<>(new StudentByAverageGradeComparator());
        strategy.sort(list);

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getAverageGrade() < list.get(i - 1).getAverageGrade()) {
                fail("Quick Sort averageGrade: не отсортировано на индексе " + i);
                return;
            }
        }
        pass("Quick Sort averageGrade: отсортировано");
    }

    private static void testQuickSortByRecordBookNumber() {
        System.out.println("\n[Тест] Quick Sort — по recordBookNumber");
        List<Student> list = createUnsortedStudents();
        SortStrategy<Student> strategy = new QuickSortStrategy<>(new StudentByRecordBookNumberComparator());
        strategy.sort(list);

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getRecordBookNumber() < list.get(i - 1).getRecordBookNumber()) {
                fail("Quick Sort recordBookNumber: не отсортировано на индексе " + i);
                return;
            }
        }
        pass("Quick Sort recordBookNumber: отсортировано");
    }

    private static void testInsertionSortByGroupNumber() {
        System.out.println("\n[Тест] Insertion Sort — по groupNumber");
        List<Student> list = createUnsortedStudents();
        SortStrategy<Student> strategy = new InsertionSortStrategy<>(new StudentByGroupNumberComparator());
        strategy.sort(list);

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getGroupNumber() < list.get(i - 1).getGroupNumber()) {
                fail("Insertion Sort groupNumber: не отсортировано на индексе " + i);
                return;
            }
        }
        pass("Insertion Sort groupNumber: отсортировано");
    }

    private static void testInsertionSortByAverageGrade() {
        System.out.println("\n[Тест] Insertion Sort — по averageGrade");
        List<Student> list = createUnsortedStudents();
        SortStrategy<Student> strategy = new InsertionSortStrategy<>(new StudentByAverageGradeComparator());
        strategy.sort(list);

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getAverageGrade() < list.get(i - 1).getAverageGrade()) {
                fail("Insertion Sort averageGrade: не отсортировано на индексе " + i);
                return;
            }
        }
        pass("Insertion Sort averageGrade: отсортировано");
    }

    private static void testInsertionSortByRecordBookNumber() {
        System.out.println("\n[Тест] Insertion Sort — по recordBookNumber");
        List<Student> list = createUnsortedStudents();
        SortStrategy<Student> strategy = new InsertionSortStrategy<>(new StudentByRecordBookNumberComparator());
        strategy.sort(list);

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getRecordBookNumber() < list.get(i - 1).getRecordBookNumber()) {
                fail("Insertion Sort recordBookNumber: не отсортировано на индексе " + i);
                return;
            }
        }
        pass("Insertion Sort recordBookNumber: отсортировано");
    }

    private static void testSortEmptyList() {
        System.out.println("\n[Тест] Сортировка пустого списка");
        List<Student> list = new ArrayList<>();
        SortStrategy<Student> strategy = new BubbleSortStrategy<>(new StudentByGroupNumberComparator());
        try {
            strategy.sort(list);
            pass("Сортировка пустого списка не вызвала исключений");
        } catch (Exception e) {
            fail("Сортировка пустого списка: " + e.getMessage());
        }
    }

    private static void testSortSingleElement() {
        System.out.println("\n[Тест] Сортировка из одного элемента");
        List<Student> list = new ArrayList<>();
        list.add(new StudentBuilder().groupNumber(100).averageGrade(4.5).recordBookNumber(1).build());
        SortStrategy<Student> strategy = new BubbleSortStrategy<>(new StudentByGroupNumberComparator());
        try {
            strategy.sort(list);
            pass("Сортировка из одного элемента прошла");
        } catch (Exception e) {
            fail("Сортировка из одного элемента: " + e.getMessage());
        }
    }

    private static void testSortAlreadySorted() {
        System.out.println("\n[Тест] Сортировка уже отсортированного списка");
        List<Student> list = new ArrayList<>();
        list.add(new StudentBuilder().groupNumber(100).averageGrade(3.0).recordBookNumber(100).build());
        list.add(new StudentBuilder().groupNumber(200).averageGrade(4.0).recordBookNumber(200).build());
        list.add(new StudentBuilder().groupNumber(300).averageGrade(5.0).recordBookNumber(300).build());

        SortStrategy<Student> strategy = new QuickSortStrategy<>(new StudentByGroupNumberComparator());
        strategy.sort(list);

        if (list.get(0).getGroupNumber() == 100 && list.get(1).getGroupNumber() == 200 && list.get(2).getGroupNumber() == 300) {
            pass("Уже отсортированный список остался отсортированным");
        } else {
            fail("Уже отсортированный список изменён");
        }
    }

    private static void testSortReverseSorted() {
        System.out.println("\n[Тест] Сортировка реверс-отсортированного списка");
        List<Student> list = new ArrayList<>();
        list.add(new StudentBuilder().groupNumber(300).averageGrade(5.0).recordBookNumber(300).build());
        list.add(new StudentBuilder().groupNumber(200).averageGrade(4.0).recordBookNumber(200).build());
        list.add(new StudentBuilder().groupNumber(100).averageGrade(3.0).recordBookNumber(100).build());

        SortStrategy<Student> strategy = new InsertionSortStrategy<>(new StudentByGroupNumberComparator());
        strategy.sort(list);

        if (list.get(0).getGroupNumber() == 100 && list.get(1).getGroupNumber() == 200 && list.get(2).getGroupNumber() == 300) {
            pass("Реверс-отсортированный список отсортирован");
        } else {
            fail("Реверс-отсортированный список не отсортирован");
        }
    }

    private static List<Student> createUnsortedStudents() {
        List<Student> list = new ArrayList<>();
        list.add(new StudentBuilder().groupNumber(301).averageGrade(3.2).recordBookNumber(500).build());
        list.add(new StudentBuilder().groupNumber(100).averageGrade(4.8).recordBookNumber(200).build());
        list.add(new StudentBuilder().groupNumber(500).averageGrade(2.5).recordBookNumber(800).build());
        list.add(new StudentBuilder().groupNumber(200).averageGrade(4.0).recordBookNumber(100).build());
        list.add(new StudentBuilder().groupNumber(400).averageGrade(3.7).recordBookNumber(600).build());
        list.add(new StudentBuilder().groupNumber(150).averageGrade(4.2).recordBookNumber(350).build());
        list.add(new StudentBuilder().groupNumber(600).averageGrade(1.8).recordBookNumber(900).build());
        list.add(new StudentBuilder().groupNumber(75).averageGrade(5.0).recordBookNumber(50).build());
        return list;
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
            System.out.println("Все тесты SortStrategyTest пройдены!");
        } else {
            System.out.println("Есть неудачные тесты!");
            System.exit(1);
        }
        System.out.println("========================================");
    }
}
