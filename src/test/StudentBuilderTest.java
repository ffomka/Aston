package test;

import builder.StudentBuilder;
import model.Student;
import util.StudentValidator;

/**
 * Тесты для StudentBuilder и StudentValidator.
 */
public class StudentBuilderTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        System.out.println("========== StudentBuilderTest ==========");

        testValidBuilder();
        testInvalidGroupNumber();
        testInvalidAverageGrade();
        testInvalidRecordBookNumber();
        testEqualsAndHashCode();
        testToString();
        testValidatorNull();
        testValidatorValid();

        printSummary();
    }

    private static void testValidBuilder() {
        System.out.println("\n[Тест] Создание валидного студента");
        try {
            Student student = new StudentBuilder()
                    .groupNumber(123)
                    .averageGrade(4.5)
                    .recordBookNumber(789)
                    .build();

            assertEquals(123, student.getGroupNumber(), "groupNumber");
            assertEquals(4.5, student.getAverageGrade(), 0.001, "averageGrade");
            assertEquals(789, student.getRecordBookNumber(), "recordBookNumber");
            pass("Создание валидного студента прошло");
        } catch (Exception e) {
            fail("Создание валидного студента: " + e.getMessage());
        }
    }

    private static void testInvalidGroupNumber() {
        System.out.println("\n[Тест] Неверный номер группы (<=0)");
        try {
            new StudentBuilder().groupNumber(0).averageGrade(4.0).recordBookNumber(1).build();
            fail("Ожидалось исключение для groupNumber=0");
        } catch (IllegalArgumentException e) {
            pass("Исключение для groupNumber=0: " + e.getMessage());
        }

        try {
            new StudentBuilder().groupNumber(-5).averageGrade(4.0).recordBookNumber(1).build();
            fail("Ожидалось исключение для groupNumber=-5");
        } catch (IllegalArgumentException e) {
            pass("Исключение для groupNumber=-5: " + e.getMessage());
        }
    }

    private static void testInvalidAverageGrade() {
        System.out.println("\n[Тест] Неверный средний балл (вне [0..5])");
        try {
            new StudentBuilder().groupNumber(1).averageGrade(-0.1).recordBookNumber(1).build();
            fail("Ожидалось исключение для averageGrade=-0.1");
        } catch (IllegalArgumentException e) {
            pass("Исключение для averageGrade=-0.1: " + e.getMessage());
        }

        try {
            new StudentBuilder().groupNumber(1).averageGrade(5.1).recordBookNumber(1).build();
            fail("Ожидалось исключение для averageGrade=5.1");
        } catch (IllegalArgumentException e) {
            pass("Исключение для averageGrade=5.1: " + e.getMessage());
        }
    }

    private static void testInvalidRecordBookNumber() {
        System.out.println("\n[Тест] Неверный номер зачетной книжки (<=0)");
        try {
            new StudentBuilder().groupNumber(1).averageGrade(4.0).recordBookNumber(0).build();
            fail("Ожидалось исключение для recordBookNumber=0");
        } catch (IllegalArgumentException e) {
            pass("Исключение для recordBookNumber=0: " + e.getMessage());
        }
    }

    private static void testEqualsAndHashCode() {
        System.out.println("\n[Тест] equals и hashCode");
        Student s1 = new StudentBuilder().groupNumber(100).averageGrade(4.0).recordBookNumber(200).build();
        Student s2 = new StudentBuilder().groupNumber(100).averageGrade(4.0).recordBookNumber(200).build();
        Student s3 = new StudentBuilder().groupNumber(101).averageGrade(4.0).recordBookNumber(200).build();

        if (!s1.equals(s2)) {
            fail("s1.equals(s2) должно быть true для одинаковых студентов");
        } else {
            pass("s1.equals(s2) == true");
        }

        if (s1.equals(s3)) {
            fail("s1.equals(s3) должно быть false для разных студентов");
        } else {
            pass("s1.equals(s3) == false");
        }

        if (s1.hashCode() != s2.hashCode()) {
            fail("hashCode одинаковых студентов должен совпадать");
        } else {
            pass("hashCode одинаковых студентов совпадает");
        }

        if (s1.equals(null)) {
            fail("equals(null) должно быть false");
        } else {
            pass("equals(null) == false");
        }

        if (s1.equals("not a student")) {
            fail("equals(не Student) должно быть false");
        } else {
            pass("equals(не Student) == false");
        }
    }

    private static void testToString() {
        System.out.println("\n[Тест] toString");
        Student student = new StudentBuilder().groupNumber(42).averageGrade(3.5).recordBookNumber(99).build();
        String str = student.toString();

        if (!str.contains("Student{")) {
            fail("toString должен содержать 'Student{'");
        } else {
            pass("toString содержит 'Student{'");
        }

        if (!str.contains("groupNumber=42")) {
            fail("toString должен содержать groupNumber=42");
        } else {
            pass("toString содержит groupNumber=42");
        }

        if (!str.contains("averageGrade=3.5")) {
            fail("toString должен содержать averageGrade=3.5");
        } else {
            pass("toString содержит averageGrade=3.5");
        }

        if (!str.contains("recordBookNumber=99")) {
            fail("toString должен содержать recordBookNumber=99");
        } else {
            pass("toString содержит recordBookNumber=99");
        }
    }

    private static void testValidatorNull() {
        System.out.println("\n[Тест] Валидатор — null");
        try {
            StudentValidator.validate(null);
            fail("Ожидалось исключение для null");
        } catch (IllegalArgumentException e) {
            pass("Исключение для null: " + e.getMessage());
        }
    }

    private static void testValidatorValid() {
        System.out.println("\n[Тест] Валидатор — валидный студент");
        Student student = new StudentBuilder().groupNumber(10).averageGrade(4.8).recordBookNumber(55).build();
        try {
            StudentValidator.validate(student);
            pass("Валидатор пропустил валидного студента");
        } catch (IllegalArgumentException e) {
            fail("Валидатор не пропустил валидного студента: " + e.getMessage());
        }
    }

    private static void assertEquals(int expected, int actual, String field) {
        if (expected != actual) {
            fail(field + ": expected " + expected + " but got " + actual);
        } else {
            pass(field + " == " + expected);
        }
    }

    private static void assertEquals(double expected, double actual, double epsilon, String field) {
        if (Math.abs(expected - actual) > epsilon) {
            fail(field + ": expected " + expected + " but got " + actual);
        } else {
            pass(field + " == " + expected);
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
            System.out.println("Все тесты StudentBuilderTest пройдены!");
        } else {
            System.out.println("Есть неудачные тесты!");
            System.exit(1);
        }
        System.out.println("========================================");
    }
}
