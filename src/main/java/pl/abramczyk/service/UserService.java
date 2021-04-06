package pl.abramczyk.service;

import pl.abramczyk.dao.DaoFactory;
import pl.abramczyk.dao.UserDao;
import pl.abramczyk.model.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserService {

    public void addUser(String username, String firstName, String lastName, String email, String password) {
        User user = new User();
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        String md5Pass = encryptPassword(password);
        user.setPassword(md5Pass);
        user.setActive(true);
        DaoFactory factory = DaoFactory.getDaoFactory();
        UserDao userDao = factory.getUserDao();
        userDao.create(user);
    }

    private String encryptPassword(String password) {
        MessageDigest digest =null;
        try {
            digest=MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        digest.update(password.getBytes());
        String md5Password = new BigInteger(1, digest.digest()).toString(16);
        return md5Password;
    }

    public User getUserById(long userId) {
        DaoFactory factory = DaoFactory.getDaoFactory();
        UserDao userDao = factory.getUserDao();
        User user = userDao.read(userId);
        return user;
    }

    public User getUserByUsername(String username) {
        DaoFactory factory = DaoFactory.getDaoFactory();
        UserDao userDao = factory.getUserDao();
        User user = userDao.getUserByUsername(username);
        return user;
    }
}
