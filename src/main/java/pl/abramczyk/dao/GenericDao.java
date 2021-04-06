package pl.abramczyk.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao <T, PK extends Serializable> {
    //Create
    T create(T newObject);

    //Read
    T read(PK primaryKey);

    //Update
    boolean update(T updateObject);

    //Delete
    boolean delete(PK key);

    List<T>getAll();
}
