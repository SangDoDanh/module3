package com.codegym.service.impl;

import com.codegym.model.Customer;
import com.codegym.model.Product;
import com.codegym.repository.IProductRepository;
import com.codegym.repository.impl.ProductRepository;
import com.codegym.service.IProductService;

import java.util.List;

import static java.lang.Double.*;

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

    public boolean checkProduct(Product product) {
        boolean isProductName = checkProductName(product.getName());
        boolean isProductDescription = checkProductDescription(product.getDescription());
        boolean isProductProducer = checkProductProducer(product.getProducer());
        boolean isProductPrice = checkProductPrice(product.getPrice());
        return (isProductDescription && isProductName && isProductProducer && isProductPrice);
    }

    public boolean checkProductPrice(double price) {
        if(price < 0) {
            return false;
        }
        return true;
    }

    public boolean checkProductProducer(String producer) {
        if(isEmpty(producer)) {
            return  false;
        }
        return true;
    }

    public boolean checkProductDescription(String description) {
        if(isEmpty(description)) {
            return  false;
        }
        return true;
    }

    public boolean checkProductName(String name) {
        if(isEmpty(name)) {
            return  false;
        }
        return true;
    }

    private boolean isEmpty(String value) {
        if(value == null){
            return false;
        }
        return value.equals("");
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
