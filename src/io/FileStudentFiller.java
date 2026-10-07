package io;

import builder.StudentBuilder;
import model.Student;
import util.StudentValidator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Заполнение списка студентов из файла.
 * Формат строки: "groupNumber;averageGrade;recordBookNumber"
 */
public class FileStudentFiller implements DataFiller<Student> {

    @Override
    public List<Student> fill(int size) {
        throw new UnsupportedOperationException("Fill size is ignored for FileStudentFiller. Use fillFromFile(path).");
    }

    /**
     * Заполняет список студентов из файла.
     *
     * @param filePath путь к файлу
     * @return список студентов
     */
    public List<Student> fillFromFile(String filePath) {
        List<Student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.lines()
                    .filter(line -> !line.trim().isEmpty())
                    .map(String::trim)
                    .forEach(line -> {
                        Student student = parseLine(line);
                        StudentValidator.validate(student);
                        students.add(student);
                    });
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла: " + filePath, e);
        }

        return students;
    }

    private Student parseLine(String line) {
        String[] parts = line.split(";");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат строки: " + line + ". Ожидается: groupNumber;averageGrade;recordBookNumber");
        }

        int groupNumber = Integer.parseInt(parts[0].trim());
        double averageGrade = Double.parseDouble(parts[1].trim());
        int recordBookNumber = Integer.parseInt(parts[2].trim());

        return new StudentBuilder()
                .groupNumber(groupNumber)
                .averageGrade(averageGrade)
                .recordBookNumber(recordBookNumber)
                .build();
    }
}
