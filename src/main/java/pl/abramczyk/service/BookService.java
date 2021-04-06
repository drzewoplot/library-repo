package pl.abramczyk.service;

import pl.abramczyk.dao.BookDao;
import pl.abramczyk.dao.DaoFactory;
import pl.abramczyk.model.Book;

import java.util.Comparator;
import java.util.List;

public class BookService {
    public void addBook(String firstName, String lastName, String title, String isbn) {
        Book book = createBookObject(firstName, lastName, title, isbn);
        DaoFactory factory = DaoFactory.getDaoFactory();
        BookDao bookDao = factory.getBookDao();
        bookDao.create(book);
    }

    private Book createBookObject(String firstName, String lastName, String title, String isbn) {
        Book book = new Book();
        book.setFirstName(firstName);
        book.setLastName(lastName);
        book.setTitle(title);
        book.setIsbn(isbn);
        return book;
    }

    public Book getBookById(long bookId) {
        DaoFactory factory = DaoFactory.getDaoFactory();
        BookDao bookDao = factory.getBookDao();
        Book book = bookDao.read(bookId);
        return book;
    }

    public boolean updateBook(Book book) {
        DaoFactory factory = DaoFactory.getDaoFactory();
        BookDao bookDao = factory.getBookDao();
        boolean result = bookDao.update(book);
        return result;
    }

    public List<Book> getAllBooks() {
        return getAllBooks(null);
    }

    public List<Book> getAllBooks(Comparator<Book> comparator) {
        DaoFactory factory = DaoFactory.getDaoFactory();
        BookDao bookDao = factory.getBookDao();
        List<Book> books = bookDao.getAll();
        if (comparator != null && books != null) {
            books.sort(comparator);
        }
        return books;
    }

}
