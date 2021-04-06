package pl.abramczyk.dao;

import pl.abramczyk.model.Book;

import java.util.List;

public interface BookDao extends GenericDao <Book, Long> {
    List<Book> getAll();
    Book getBookByIsbn(String isbn);
}
