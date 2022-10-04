package repository;

import java.util.List;

public interface IRepository<E> {
    int insert(E e);
    int delete(int id);
    int update(E e);
    List<E> select();
    List<E> select(String condition);

}
