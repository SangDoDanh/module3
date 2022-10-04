package com.codegym.repository.impl;

import com.codegym.model.Customer;
import com.codegym.model.Product;
import com.codegym.repository.IProductRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository implements IProductRepository {
    private static Map<Integer, Product> productList = new HashMap<>();
    static {
        List<Product> data = inIt(100);
        for(int i = 0; i < data.size(); i++) {
            productList.put(i, data.get(i));
        }
    }

    private static List<Product> inIt(int amount) {
        List<Product> productsInit = new ArrayList<>();
        for(int i = 0; i < amount; i++) {
            Product product = new Product(i, "Product name " + (i + 1), "Product description " + (i + 1), "Product producer", 100 * (i + 1));
            productsInit.add(product);
        }
        return productsInit;
    }


    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productList.values());
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
        productList.put(id, product);
    }

    @Override
    public void remove(int id) {
        productList.remove(id);
    }

    @Override
    public void add(Product product) {
        productList.put(product.getId(),product);
    }
}
