package pl.abramczyk.dao;

import pl.abramczyk.model.Book;

import java.util.List;

public interface BookDao extends GenericDao <Book, Long> {
    List<Book> getAll();
    List<Book> getAll(long userId);
    Book getBookByIsbn(String isbn);
}
