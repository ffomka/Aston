package ru.aston.hometask;

/**
 * Класс Book — книга.
 * Уникальность определяется по комбинации title + author + pages + year.
 */
public class Book {

    private String title;
    private String author;
    private int pages;
    private int year;

    public Book() {
        // нужен Jackson-у для десериализации
    }

    public Book(String title, String author, int pages, int year) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }

    public int getYear() {
        return year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (pages != book.pages) return false;
        if (year != book.year) return false;
        if (!title.equals(book.title)) return false;
        return author.equals(book.author);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + pages;
        result = 31 * result + year;
        return result;
    }

    @Override
    public String toString() {
        return String.format("\"%s\" (%s, %d стр., %d г.)",
                title, author, pages, year);
    }
}
