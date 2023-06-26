package br.org.mnf.dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<T extends Serializable, I extends Serializable> {

    public T get(I id);
    
    public List<T> getAll();

    public T update(T entity);
    
    public void insert(T entity);

    public void delete(T entity);
    
    public void deleteById(I id);

}
