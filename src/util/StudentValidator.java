package util;

import model.Student;

/**
 * Валидатор для объектов Student.
 */
public class StudentValidator {

    /**
     * Проверяет корректность объекта Student.
     *
     * @param student проверяемый студент
     * @throws IllegalArgumentException если студент не проходит валидацию
     */
    public static void validate(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Студент не может быть null");
        }

        if (student.getGroupNumber() <= 0) {
            throw new IllegalArgumentException("Номер группы должен быть больше 0");
        }

        if (student.getAverageGrade() < 0.0 || student.getAverageGrade() > 5.0) {
            throw new IllegalArgumentException("Средний балл должен быть в диапазоне [0..5]");
        }

        if (student.getRecordBookNumber() <= 0) {
            throw new IllegalArgumentException("Номер зачетной книжки должен быть больше 0");
        }
    }
}
