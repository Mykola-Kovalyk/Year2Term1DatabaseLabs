package ua.iot.kovalyk.mysqlspringlab.dao;


import java.util.List;
import java.util.Optional;

public interface GeneralDao<T, ID> {

    public List<T> getAll();
    public Optional<T> getById(ID id);
    public int create(T entity);
    public int update(ID id, T entity);
    public int delete(ID id);
}
