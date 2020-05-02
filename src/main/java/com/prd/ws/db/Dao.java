package com.prd.ws.db;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface Dao<T, ID extends Serializable> {

    public abstract T findById(ID id);
    
    public abstract void save(T object);
    
    public abstract void delete(T object);
    
    public abstract void flush();
    
    List<T> findAllBy(String property, List<?> values);
    
    List<T> findAllBy(String property, List<?> values, Object order);
    
    List<T> findAllBy(String property, Object value);
    
    List<T> findAllBy(String property, Object value, Object order);
    
    List<T> findAll();
    
    List<T> findAll(Object order);
    
    List<T> findAll(Map<String, Object> values, Map<String, String> orders);
    
    List<T> findAll(Map<String, String> aliases, Map<String, Object> values, Map<String, String> orders);
    
    T findBy(String property, Object value);

}