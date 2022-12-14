package com.codegym.service;

import com.codegym.model.Customer;
import com.codegym.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    void save(Product product);
    Customer findById(int id);
    void update(int id, Product customer);
    void remove(int id);
    void add(Product product);
}
