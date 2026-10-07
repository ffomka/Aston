package comparator;

import model.Student;

import java.util.Comparator;

/**
 * Компаратор для сортировки студентов по номеру группы.
 */
public class StudentByGroupNumberComparator implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {
        return Integer.compare(s1.getGroupNumber(), s2.getGroupNumber());
    }
}
