package pl.abramczyk.model;

import java.util.Objects;

public class Book {
    private long id;
    private String firstName;
    private String lastName;
    private String title;
    private String isbn;
    private OrderType orderType;


    public Book() {
    }

    public Book(Book book) {
        this.id = book.id;
        this.firstName = book.firstName;
        this.lastName = book.lastName;
        this.title = book.title;
        this.isbn = book.isbn;
        this.orderType = book.orderType;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", firstName=" + firstName + ", lastName="
                + lastName + ", isbn=" + isbn + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                firstName.equals(book.firstName) &&
                lastName.equals(book.lastName) &&
                title.equals(book.title) &&
                isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, title, isbn);
    }
}
