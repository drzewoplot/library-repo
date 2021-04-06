package pl.abramczyk.dao;

import pl.abramczyk.model.Order;
import pl.abramczyk.model.OrderType;

import java.util.List;

public interface OrderDao extends GenericDao<Order, Long> {
    List<Order> getAll();
    public Order getOrderByBookIdUserId(long userId, long bookId);
}
