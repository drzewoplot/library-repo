package pl.abramczyk.dao;

import pl.abramczyk.model.User;

import java.util.List;

public interface UserDao extends GenericDao <User, Long> {
    List<User> getAll();
    User getUserByUsername(String username);
}
