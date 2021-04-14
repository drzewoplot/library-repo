package pl.abramczyk.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import pl.abramczyk.model.Order;
import pl.abramczyk.model.OrderType;
import pl.abramczyk.util.ConnectionProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {

    private static final String CREATE_ORDER =
            "INSERT INTO library.order(date, book_id, user_id, status) VALUES(:date, :book_id, :user_id, :status);";
    private static final String READ_ORDER =
            "SELECT order_id, date, book_id, user_id, status FROM order WHERE order_id = :order_id;";
    private static final String READ_ORDER_BY_BOOK_USER_IDS =
            "SELECT order_id, date, book_id, user_id, status FROM order" +
                    " WHERE user_id=:user_id AND book_id=:book_id;";
    private static final String UPDATE_ORDER = "UPDATE order SET date=:date, status=:status WHERE order_id=:order_id";
//    private static final String READ_ORDER_BY_USER_ID =
//            "SELECT order_id, date, first_name, last_name, title, isbn FROM library.order LEFT JOIN library.book" +
//                    " on library.order.book_id=library.book.book_id WHERE user_id=:user_id;";

    private NamedParameterJdbcTemplate template;

    public OrderDaoImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }

    @Override
    public Order create(Order order) {
        Order orderCopy = new Order(order);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("date", orderCopy.getDate());
        paramMap.put("user_id", orderCopy.getUserId());
        paramMap.put("book_id", orderCopy.getBookId());
        paramMap.put("status", orderCopy.getOrderType().toString());
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource(paramMap);
        int update = template.update(CREATE_ORDER, parameterSource, holder);
        if (update > 0) {
            orderCopy.setId(holder.getKey().longValue());
            // ponizsza metoda aktualizuje kolumne status w tabeli book
            updateBookStatus(orderCopy);
        }
        return orderCopy;
    }

    private boolean updateBookStatus(Order order) {
        final String bookStatusQuery = "UPDATE book SET status=:status WHERE book_id=:book_id;";
        boolean result = false;
        Order orderCopy = new Order(order);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("status", orderCopy.getOrderType().toString());
        paramMap.put("book_id", orderCopy.getBookId());
        SqlParameterSource parameterSource = new MapSqlParameterSource(paramMap);
        template.update(bookStatusQuery, parameterSource);
        int update = template.update(bookStatusQuery, parameterSource);
        if (update > 1) {
            result = true;
        }
        return result;
    }

    @Override
    public Order read(Long primaryKey) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("order_id", primaryKey);
        Order order = template.queryForObject(READ_ORDER, parameterSource, new OrderRowMapper());
        return order;
    }

    @Override
    public boolean update(Order order) {
        boolean result = false;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("date", order.getDate());
        paramMap.put("status", order.getOrderType().toString());
        paramMap.put("order_id", order.getId());
        SqlParameterSource parameterSource = new MapSqlParameterSource(paramMap);
        int update = template.update(UPDATE_ORDER, parameterSource);
        if (update > 1) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public Order getOrderByBookIdUserId(long userId, long bookId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("user_id", userId);
        paramMap.put("book_id", bookId);
        SqlParameterSource parameterSource = new MapSqlParameterSource(paramMap);
        Order order = null;
        try {
            order = template.queryForObject(READ_ORDER_BY_BOOK_USER_IDS, parameterSource, new OrderRowMapper());
        } catch (EmptyResultDataAccessException e) {
            //zamówienie nie odnalezione
        }
        return order;
    }

    private class OrderRowMapper implements RowMapper<Order> {
        @Override
        public Order mapRow(ResultSet resultSet, int row) throws SQLException {
            Order order = new Order();
            order.setId(resultSet.getLong("order_id"));
            order.setBookId(resultSet.getLong("book_id"));
            order.setUserId(resultSet.getLong("user_id"));
            order.setOrderType(OrderType.valueOf(resultSet.getString("status")));
            //w MySql date jest formatu TimeStamp
            order.setDate(resultSet.getTimestamp("date"));
            return order;
        }
    }
}
