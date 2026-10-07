package comparator;

import model.Student;

import java.util.Comparator;

/**
 * Компаратор для сортировки студентов по номеру зачетной книжки.
 */
public class StudentByRecordBookNumberComparator implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {
        return Integer.compare(s1.getRecordBookNumber(), s2.getRecordBookNumber());
    }
}
