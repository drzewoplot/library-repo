package pl.abramczyk.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import pl.abramczyk.model.Book;
import pl.abramczyk.model.OrderType;
import pl.abramczyk.util.ConnectionProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookDaoImpl implements BookDao {

    private static final String CREATE_BOOK =
            "INSERT INTO book(first_name, last_name, title, isbn) VALUES(:firstName, :lastName, :title, :isbn);";
    private static final String READ_ALL_BOOKS =
            "SELECT first_name, last_name, title, isbn, status, book_id FROM book;";
    //    private static final String READ_BOOK = "SELECT first_name, last_name, title, isbn, status, book_id FROM book " +
//            "LEFT JOIN order ON book.status=order.status AND book.book_id=order.book_id WHERE book_id=:book_id;";
    //chyba nie potrzebne jest laczenie tabel
    private static final String READ_BOOK =
            "SELECT first_name, last_name, title, isbn, status, book_id FROM book WHERE book_id=:book_id;";
    private static final String UPDATE_BOOK =
            "UPDATE book SET first_name=:firstName, last_name=:lastName, title=:title, isbn=:isbn, status=:status " +
                    "WHERE book_id=:book_id;";

    private NamedParameterJdbcTemplate template;

    public BookDaoImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }

    @Override
    public Book create(Book book) {
        Book resultBook = new Book(book);
        KeyHolder holder = new GeneratedKeyHolder();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("firstName", book.getFirstName());
        paramMap.put("lastName", book.getLastName());
        paramMap.put("title", book.getTitle());
        paramMap.put("isbn", book.getIsbn());
        //book.status jest ustawione w MySql na wartosc domyslna ='DOSTEPNA'
        SqlParameterSource parameterSource = new MapSqlParameterSource(paramMap);
        int update = template.update(CREATE_BOOK, parameterSource, holder);
        if (update > 0) {
            resultBook.setId(holder.getKey().longValue());
        }
        return resultBook;
    }

    @Override
    public Book read(Long primaryKey) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("book_id", primaryKey);
        Book book = template.queryForObject(READ_BOOK, parameterSource, new BookRowMapper());
        return book;
    }

    @Override
    public boolean update(Book book) {
        boolean result = false;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("firstName", book.getFirstName());
        paramMap.put("lastName", book.getLastName());
        paramMap.put("title", book.getTitle());
        paramMap.put("isbn", book.getIsbn());
        paramMap.put("status", book.getOrderType().toString());
        paramMap.put("book_id", book.getId());
        SqlParameterSource parameterSource = new MapSqlParameterSource(paramMap);
        int update = template.update(UPDATE_BOOK, parameterSource);
        if (update > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        return null;
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = template.query(READ_ALL_BOOKS, new BookRowMapper());
        return books;
    }

    private class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int row) throws SQLException {
            Book book = new Book();
            book.setId(resultSet.getLong("book_id"));
            book.setFirstName(resultSet.getString("first_name"));
            book.setLastName(resultSet.getString("last_name"));
            book.setTitle(resultSet.getString("title"));
            book.setIsbn(resultSet.getString("isbn"));
            book.setOrderType(OrderType.valueOf(resultSet.getString("status")));
            return book;
        }
    }


}
