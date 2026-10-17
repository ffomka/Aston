package ru.aston.hometask;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс Student — студент и его библиотека книг.
 */
public class Student {

    private String name;
    private List<Book> books;

    public Student() {
        // нужен Jackson-у для десериализации
    }

    public Student(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public String toString() {
        return String.format("Студент: %s (%d книг)", name, books.size());
    }
}
