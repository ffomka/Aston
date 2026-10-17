import java.util.ArrayList;
import java.util.List;

/**
 * Класс Student — студент и его библиотека книг.
 */
public class Student {

    private String name;
    private List<Book> books;

    public Student(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    // --- геттеры ---

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }

    /** Добавить книгу в список. */
    public void addBook(Book book) {
        books.add(book);
    }

    // --- toString ---

    @Override
    public String toString() {
        return String.format("Студент: %s (%d книг)", name, books.size());
    }
}
