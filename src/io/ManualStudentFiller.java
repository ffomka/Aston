package io;

import builder.StudentBuilder;
import model.Student;
import util.StudentValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Заполнение списка студентов с ручного ввода с консоли.
 */
public class ManualStudentFiller implements DataFiller<Student> {

    private final Scanner scanner;

    public ManualStudentFiller() {
        this.scanner = new Scanner(System.in);
    }

    protected ManualStudentFiller(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public List<Student> fill(int size) {
        List<Student> students = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            System.out.println("Введите данные студента #" + (i + 1));

            int groupNumber = readInt("Номер группы (должен быть > 0): ", "Некорректный номер группы. Должно быть целое число > 0");
            double averageGrade = readDouble("Средний балл (должен быть в диапазоне [0..5]): ", "Некорректный средний балл. Должно быть число в диапазоне [0..5]");
            int recordBookNumber = readInt("Номер зачетной книжки (должен быть > 0): ", "Некорректный номер зачетной книжки. Должно быть целое число > 0");

            Student student = new StudentBuilder()
                    .groupNumber(groupNumber)
                    .averageGrade(averageGrade)
                    .recordBookNumber(recordBookNumber)
                    .build();

            StudentValidator.validate(student);
            students.add(student);
            System.out.println("Студент добавлен: " + student + "\n");
        }

        return students;
    }

    private int readInt(String prompt, String errorMessage) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Поле не может быть пустым.");
                continue;
            }

            try {
                int value = Integer.parseInt(input);
                if (value <= 0) {
                    System.out.println(errorMessage);
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }

    private double readDouble(String prompt, String errorMessage) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Поле не может быть пустым.");
                continue;
            }

            try {
                double value = Double.parseDouble(input);
                if (value < 0.0 || value > 5.0) {
                    System.out.println(errorMessage);
                    continue;
                }
                return Math.round(value * 10.0) / 10.0;
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }
}
