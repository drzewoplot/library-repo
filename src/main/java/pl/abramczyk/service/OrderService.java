package pl.abramczyk.service;


import pl.abramczyk.dao.DaoFactory;
import pl.abramczyk.dao.OrderDao;
import pl.abramczyk.model.Order;
import pl.abramczyk.model.OrderType;


import java.sql.Timestamp;
import java.util.Date;


public class OrderService {
    public Order addOrder(long userId, long bookId, OrderType orderType) {
        Order order = new Order();
        order.setDate(new Timestamp(new Date().getTime()));
        order.setUserId(userId);
        order.setBookId(bookId);
        order.setOrderType(orderType);
        DaoFactory factory = DaoFactory.getDaoFactory();
        OrderDao orderDao = factory.getOrderDao();
        order = orderDao.create(order);
        return order;
    }

    public Order updateOrder(long bookId, long userId, OrderType orderType) {
        DaoFactory factory = DaoFactory.getDaoFactory();
        OrderDao orderDao = factory.getOrderDao();
        Order orderToUpdate = orderDao.getOrderByBookIdUserId(bookId, userId);
        System.out.println("metoda updateOrder z klasy Serwisu");
        if (orderToUpdate != null) {
            orderToUpdate.setOrderType(orderType);
            orderDao.update(orderToUpdate);
        }
        return orderToUpdate;
    }

    public Order addOrUpdateOrder(long bookId, long userId, OrderType orderType) {
        DaoFactory factory = DaoFactory.getDaoFactory();
        OrderDao orderDao = factory.getOrderDao();
        Order order = orderDao.getOrderByBookIdUserId(userId, bookId);
        Order resultOrder = null;
        if (order == null) {
            resultOrder = addOrder(userId, bookId, orderType);
        } else {
            resultOrder = updateOrder(userId, bookId, orderType);
        }
        return resultOrder;
    }

    public Order getOrderByBookUserIds(long bookId, long userId){
        DaoFactory factory = DaoFactory.getDaoFactory();
        OrderDao orderDao = factory.getOrderDao();
        Order order = orderDao.getOrderByBookIdUserId(userId,bookId);
        return order;
    }
}
