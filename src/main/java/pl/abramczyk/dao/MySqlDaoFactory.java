package pl.abramczyk.dao;

public class MySqlDaoFactory extends DaoFactory {
    @Override
    public UserDao getUserDao() {
        return new UserDaoImpl();
    }

    @Override
    public BookDao getBookDao() {
        return new BookDaoImpl();
    }

    @Override
    public OrderDao getOrderDao() {
        return new OrderDaoImpl();
    }
}
