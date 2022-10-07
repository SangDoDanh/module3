package model.service;

import model.model.Customer;

import java.util.List;
import java.util.Map;

public interface ICustomerService {
    List<Customer> getAll();

    Map<Integer, String> getCustomerTypeAll();

    void delete(int id);

    void create(Customer customer);

    void update(Customer customer);
}
