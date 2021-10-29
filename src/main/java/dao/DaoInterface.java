package dao;

import java.util.List;

public interface DaoInterface<T> {
    void save(T t);
    void update(T t);
    void delete(T t);
    List<T> readAll();


}
