package model;

import java.util.Objects;

public class LibraryBook implements Comparable<LibraryBook> {
    private final String isbn;
    private final String title;
    private final String author;
    private boolean isAvailable;
    private int overdueDays;

    public LibraryBook(String isbn, String title, String author, boolean isAvailable, int overdueDays) {
        this.isbn = Objects.requireNonNull(isbn, "ISBN required");
        this.title = Objects.requireNonNull(title, "Title required");
        this.author = Objects.requireNonNull(author, "Author required");
        this.isAvailable = isAvailable;
        this.overdueDays = overdueDays;
    }

    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    public int getOverdueDays() { return overdueDays; }
    public void setOverdueDays(int days) { this.overdueDays = days; }

    @Override
    public int compareTo(LibraryBook o) {
        return this.isbn.compareTo(o.isbn);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LibraryBook b)) return false;
        return Objects.equals(isbn, b.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public String toString() {
        return String.format("[%s] '%s' by %s (Avail: %s, Overdue: %dd)", isbn, title, author, isAvailable, overdueDays);
    }
}
