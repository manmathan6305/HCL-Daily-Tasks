package model;

import java.util.Objects;

/**
 * Domain model representing a book in the library catalogue.
 * Implements Comparable for natural ordering by ISBN, and overrides
 * equals() and hashCode() based on ISBN for proper de-duplication in Sets.
 */
public class Book implements Comparable<Book> {
    private final String isbn;
    private final String title;
    private final String author;
    private boolean isOverdue;
    private int overdueDays;

    public Book(String isbn, String title, String author) {
        this(isbn, title, author, false, 0);
    }

    public Book(String isbn, String title, String author, boolean isOverdue, int overdueDays) {
        this.isbn = Objects.requireNonNull(isbn, "ISBN cannot be null");
        this.title = Objects.requireNonNull(title, "Title cannot be null");
        this.author = Objects.requireNonNull(author, "Author cannot be null");
        this.isOverdue = isOverdue;
        this.overdueDays = overdueDays;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isOverdue() {
        return isOverdue;
    }

    public void setOverdue(boolean overdue) {
        isOverdue = overdue;
    }

    public int getOverdueDays() {
        return overdueDays;
    }

    public void setOverdueDays(int overdueDays) {
        this.overdueDays = overdueDays;
        this.isOverdue = overdueDays > 0;
    }

    @Override
    public int compareTo(Book other) {
        return this.isbn.compareTo(other.isbn);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public String toString() {
        return String.format("Book[ISBN=%s, Title='%s', Author='%s', Overdue=%s (%d days)]",
                isbn, title, author, isOverdue, overdueDays);
    }
}
