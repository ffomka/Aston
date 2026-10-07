package test;

import io.FileStudentFiller;
import model.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Тесты для FileStudentFiller (чтение + валидация).
 */
public class FileFillerTest {

    private static int passed = 0;
    private static int failed = 0;
    private static Path tempFile;

    public static void main(String[] args) {
        System.out.println("========== FileFillerTest ==========");

        createTempFile();
        testValidFileReading();
        testEmptyFile();
        testFileWithEmptyLines();
        testInvalidFormat();
        testNonExistentFile();
        testInvalidNumberFormat();

        cleanup();
        printSummary();
    }

    private static void createTempFile() {
        System.out.println("\n[Подготовка] Создание временного файла");
        try {
            tempFile = Files.createTempFile("students_test", ".txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile.toFile()))) {
                // Валидные строки
                writer.write("100;4.5;100001");
                writer.newLine();
                writer.write("200;3.8;200002");
                writer.newLine();
                writer.write("300;4.2;300003");
                writer.newLine();
                // Пустая строка
                writer.newLine();
                // Ещё валидные
                writer.write("400;5.0;400004");
                writer.newLine();
                writer.write("500;2.5;500005");
                writer.newLine();
            }
            pass("Временный файл создан: " + tempFile);
        } catch (IOException e) {
            fail("Создание временного файла: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void cleanup() {
        if (tempFile != null) {
            try {
                Files.deleteIfExists(tempFile);
                System.out.println("\n[Очистка] Временный файл удалён");
            } catch (IOException e) {
                System.out.println("Не удалось удалить временный файл: " + e.getMessage());
            }
        }
    }

    private static void testValidFileReading() {
        System.out.println("\n[Тест] Чтение валидного файла");
        try {
            FileStudentFiller filler = new FileStudentFiller();
            List<Student> students = filler.fillFromFile(tempFile.toString());

            if (students.size() != 5) {
                fail("Ожидалось 5 студентов, получено " + students.size());
                return;
            }
            pass("Количество студентов: 5");

            assertEquals(100, students.get(0).getGroupNumber(), "студент[0].groupNumber");
            assertEquals(4.5, students.get(0).getAverageGrade(), 0.001, "студент[0].averageGrade");
            assertEquals(100001, students.get(0).getRecordBookNumber(), "студент[0].recordBookNumber");

            assertEquals(500, students.get(4).getGroupNumber(), "студент[4].groupNumber");
            assertEquals(2.5, students.get(4).getAverageGrade(), 0.001, "студент[4].averageGrade");
            assertEquals(500005, students.get(4).getRecordBookNumber(), "студент[4].recordBookNumber");

            pass("Все поля всех студентов совпадают");
        } catch (Exception e) {
            fail("Чтение валидного файла: " + e.getMessage());
        }
    }

    private static void testEmptyFile() {
        System.out.println("\n[Тест] Пустой файл");
        Path emptyFile = null;
        try {
            emptyFile = Files.createTempFile("students_empty", ".txt");
            FileStudentFiller filler = new FileStudentFiller();
            List<Student> students = filler.fillFromFile(emptyFile.toString());

            if (students.isEmpty()) {
                pass("Пустой файл: список пуст");
            } else {
                fail("Пустой файл: ожидался пустой список, получено " + students.size());
            }
        } catch (Exception e) {
            fail("Пустой файл: " + e.getMessage());
        } finally {
            if (emptyFile != null) {
                try {
                    Files.deleteIfExists(emptyFile);
                } catch (IOException ignored) {
                }
            }
        }
    }

    private static void testFileWithEmptyLines() {
        System.out.println("\n[Тест] Файл с пустыми строками");
        Path fileWithEmptyLines = null;
        try {
            fileWithEmptyLines = Files.createTempFile("students_empty_lines", ".txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileWithEmptyLines.toFile()))) {
                writer.write("100;4.0;111111");
                writer.newLine();
                writer.newLine();
                writer.write("   ");
                writer.newLine();
                writer.write("200;3.5;222222");
                writer.newLine();
            }

            FileStudentFiller filler = new FileStudentFiller();
            List<Student> students = filler.fillFromFile(fileWithEmptyLines.toString());

            if (students.size() == 2) {
                pass("Файл с пустыми строками: 2 студента (пустые пропущены)");
            } else {
                fail("Файл с пустыми строками: ожидалось 2, получено " + students.size());
            }
        } catch (Exception e) {
            fail("Файл с пустыми строками: " + e.getMessage());
        } finally {
            if (fileWithEmptyLines != null) {
                try {
                    Files.deleteIfExists(fileWithEmptyLines);
                } catch (IOException ignored) {
                }
            }
        }
    }

    private static void testInvalidFormat() {
        System.out.println("\n[Тест] Неверный формат строки");
        Path invalidFile = null;
        try {
            invalidFile = Files.createTempFile("students_invalid_format", ".txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(invalidFile.toFile()))) {
                writer.write("100;4.0");  // пропущено поле
                writer.newLine();
            }

            FileStudentFiller filler = new FileStudentFiller();
            filler.fillFromFile(invalidFile.toString());
            fail("Ожидалось исключение для неверного формата");
        } catch (Exception e) {
            pass("Неверный формат: исключение — " + e.getClass().getSimpleName());
        } finally {
            if (invalidFile != null) {
                try {
                    Files.deleteIfExists(invalidFile);
                } catch (IOException ignored) {
                }
            }
        }
    }

    private static void testNonExistentFile() {
        System.out.println("\n[Тест] Несуществующий файл");
        try {
            FileStudentFiller filler = new FileStudentFiller();
            filler.fillFromFile("nonexistent_file_12345.txt");
            fail("Ожидалось исключение для несуществующего файла");
        } catch (Exception e) {
            pass("Несуществующий файл: исключение — " + e.getClass().getSimpleName());
        }
    }

    private static void testInvalidNumberFormat() {
        System.out.println("\n[Тест] Некорректный числовой формат");
        Path invalidNumberFile = null;
        try {
            invalidNumberFile = Files.createTempFile("students_invalid_number", ".txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(invalidNumberFile.toFile()))) {
                writer.write("abc;4.0;111111");  // groupNumber — не число
                writer.newLine();
            }

            FileStudentFiller filler = new FileStudentFiller();
            filler.fillFromFile(invalidNumberFile.toString());
            fail("Ожидалось исключение для некорректного числа");
        } catch (Exception e) {
            pass("Некорректное число: исключение — " + e.getClass().getSimpleName());
        } finally {
            if (invalidNumberFile != null) {
                try {
                    Files.deleteIfExists(invalidNumberFile);
                } catch (IOException ignored) {
                }
            }
        }
    }

    private static void assertEquals(int expected, int actual, String message) {
        if (expected != actual) {
            fail(message + ": expected " + expected + " but got " + actual);
        } else {
            pass(message + " == " + expected);
        }
    }

    private static void assertEquals(double expected, double actual, double epsilon, String message) {
        if (Math.abs(expected - actual) > epsilon) {
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
            System.out.println("Все тесты FileFillerTest пройдены!");
        } else {
            System.out.println("Есть неудачные тесты!");
            System.exit(1);
        }
        System.out.println("========================================");
    }
}
