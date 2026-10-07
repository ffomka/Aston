package comparator;

import model.Student;

import java.util.Comparator;

/**
 * Компаратор для сортировки студентов по среднему баллу.
 */
public class StudentByAverageGradeComparator implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {
        return Double.compare(s1.getAverageGrade(), s2.getAverageGrade());
    }
}
