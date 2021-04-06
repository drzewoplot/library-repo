package pl.abramczyk.dao;

import pl.abramczyk.exception.NoSuchDbTypeException;

public abstract class DaoFactory {
    public static final int MYSQL_DAO_FACTORY = 1;

    public abstract UserDao getUserDao();

    public abstract BookDao getBookDao();

    public abstract OrderDao getOrderDao();

    public static DaoFactory getDaoFactory() {
        DaoFactory factory = null;
        try {
            factory = getDaoFactory(MYSQL_DAO_FACTORY);
        } catch (NoSuchDbTypeException e) {
            e.printStackTrace();
        }
        return factory;
    }

    private static DaoFactory getDaoFactory(int type) throws NoSuchDbTypeException {
        switch (type) {
            case MYSQL_DAO_FACTORY:
                return new MySqlDaoFactory();
            default:
                throw new NoSuchDbTypeException();
        }
    }



}
