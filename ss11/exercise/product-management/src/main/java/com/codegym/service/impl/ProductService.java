package com.codegym.service.impl;

import com.codegym.model.Customer;
import com.codegym.model.Product;
import com.codegym.repository.IProductRepository;
import com.codegym.repository.impl.ProductRepository;
import com.codegym.service.IProductService;

import java.util.List;

public class ProductService implements IProductService {
    private static IProductRepository productRepository = new ProductRepository();
    @Override
    public List<Product> findAll() {
       return productRepository.findAll();
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public Customer findById(int id) {
        return null;
    }

    @Override
    public void update(int id, Product product) {
        productRepository.update(id, product);
    }

    @Override
    public void remove(int id) {
        productRepository.remove(id);
    }

    @Override
    public void add(Product product) {
        productRepository.add(product);
    }
}
