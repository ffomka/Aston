package io;

import builder.StudentBuilder;
import model.Student;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Заполнение списка студентов случайными данными через Stream.
 */
public class RandomStudentFiller implements DataFiller<Student> {

    private final Random random = new Random();

    @Override
    public List<Student> fill(int size) {
        if (size <= 0) {
            return List.of();
        }

        return IntStream.rangeClosed(1, size)
                .mapToObj(i -> generateRandomStudent())
                .collect(Collectors.toList());
    }

    private Student generateRandomStudent() {
        int groupNumber = random.nextInt(900) + 100; // 100-999
        double averageGrade = Math.round(random.nextDouble() * 5.0 * 10.0) / 10.0;
        int recordBookNumber = random.nextInt(900000) + 100000; // 100000-999999

        return new StudentBuilder()
                .groupNumber(groupNumber)
                .averageGrade(averageGrade)
                .recordBookNumber(recordBookNumber)
                .build();
    }
}
