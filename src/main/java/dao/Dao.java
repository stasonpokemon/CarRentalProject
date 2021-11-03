package dao;

import java.util.List;

public interface Dao<T> {
    void save(T t);
    T read(int id);
    void update(T t);
    void delete(T t);
    List<T> readAll();


}
