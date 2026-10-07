package builder;

import model.Student;

/**
 * Отдельный класс-билдер для создания экземпляров Student.
 * Использует паттерн Builder для пошагового создания объекта.
 */
public class StudentBuilder {

    private int groupNumber;
    private double averageGrade;
    private int recordBookNumber;

    public StudentBuilder groupNumber(int groupNumber) {
        if (groupNumber <= 0) {
            throw new IllegalArgumentException("Номер группы должен быть больше 0");
        }
        this.groupNumber = groupNumber;
        return this;
    }

    public StudentBuilder averageGrade(double averageGrade) {
        if (averageGrade < 0.0 || averageGrade > 5.0) {
            throw new IllegalArgumentException("Средний балл должен быть в диапазоне [0..5]");
        }
        this.averageGrade = averageGrade;
        return this;
    }

    public StudentBuilder recordBookNumber(int recordBookNumber) {
        if (recordBookNumber <= 0) {
            throw new IllegalArgumentException("Номер зачетной книжки должен быть больше 0");
        }
        this.recordBookNumber = recordBookNumber;
        return this;
    }

    public Student build() {
        return new Student.Builder()
                .groupNumber(this.groupNumber)
                .averageGrade(this.averageGrade)
                .recordBookNumber(this.recordBookNumber)
                .build();
    }
}
