package com.ebru.dao;

import com.ebru.model.Agent;
import com.ebru.model.Seller;

import java.util.List;

public interface RealEstateDAO<T> {

    public void saveOrUpdate(T data);
    public T getFindById(Long id);
    public List<T> getFindAll();
    public void deleteFindById(Long id);

}
