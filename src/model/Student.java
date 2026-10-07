package model;

/**
 * Класс, представляющий студента.
 */
public class Student {

    private int groupNumber;
    private double averageGrade;
    private int recordBookNumber;

    private Student(Builder builder) {
        this.groupNumber = builder.groupNumber;
        this.averageGrade = builder.averageGrade;
        this.recordBookNumber = builder.recordBookNumber;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public int getRecordBookNumber() {
        return recordBookNumber;
    }

    public void setGroupNumber(int groupNumber) {
        if (groupNumber <= 0) {
            throw new IllegalArgumentException("Номер группы должен быть больше 0");
        }
        this.groupNumber = groupNumber;
    }

    public void setAverageGrade(double averageGrade) {
        if (averageGrade < 0.0 || averageGrade > 5.0) {
            throw new IllegalArgumentException("Средний балл должен быть в диапазоне [0..5]");
        }
        this.averageGrade = averageGrade;
    }

    public void setRecordBookNumber(int recordBookNumber) {
        if (recordBookNumber <= 0) {
            throw new IllegalArgumentException("Номер зачетной книжки должен быть больше 0");
        }
        this.recordBookNumber = recordBookNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "groupNumber=" + groupNumber +
                ", averageGrade=" + averageGrade +
                ", recordBookNumber=" + recordBookNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return groupNumber == student.groupNumber
                && Double.compare(student.averageGrade, averageGrade) == 0
                && recordBookNumber == student.recordBookNumber;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = groupNumber;
        temp = Double.doubleToLongBits(averageGrade);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + recordBookNumber;
        return result;
    }

    /**
     * Вложенный класс Builder для создания экземпляров Student.
     */
    public static class Builder {

        private int groupNumber;
        private double averageGrade;
        private int recordBookNumber;

        public Builder groupNumber(int groupNumber) {
            if (groupNumber <= 0) {
                throw new IllegalArgumentException("Номер группы должен быть больше 0");
            }
            this.groupNumber = groupNumber;
            return this;
        }

        public Builder averageGrade(double averageGrade) {
            if (averageGrade < 0.0 || averageGrade > 5.0) {
                throw new IllegalArgumentException("Средний балл должен быть в диапазоне [0..5]");
            }
            this.averageGrade = averageGrade;
            return this;
        }

        public Builder recordBookNumber(int recordBookNumber) {
            if (recordBookNumber <= 0) {
                throw new IllegalArgumentException("Номер зачетной книжки должен быть больше 0");
            }
            this.recordBookNumber = recordBookNumber;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}
