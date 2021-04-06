package pl.abramczyk.model;

import java.sql.Timestamp;
import java.util.Objects;


public class Order {
    private long id;
    private OrderType orderType;
    private long bookId;
    private long userId;
    private Timestamp timestamp;

    public Order (){}

    public Order(Order order) {
        this.id = order.id;
        this.timestamp = new Timestamp(order.timestamp.getTime());
        this.orderType = order.orderType;
        this.bookId= order.bookId;
        this.userId=order.userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Timestamp getDate() {
        return timestamp;
    }

    public void setDate(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", orderType=" + orderType +
                ", bookId=" + bookId +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && bookId == order.bookId && userId == order.userId &&
                orderType == order.orderType && timestamp.equals(order.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderType, bookId, userId, timestamp);
    }
}

