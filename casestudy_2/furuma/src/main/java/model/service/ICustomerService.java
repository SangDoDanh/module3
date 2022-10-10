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

    List<Customer> search(String keySearch, String customerTypeSearch);

    List<Customer> search(String keySearch, String customerTypeSearch, int gender);

    int getCountPage(int amountCustomer);

    List<Customer> getAll(int indexPage, int amountCustomer);

    int getIndexPage(int countPage, String indexPageString);

    int getCountCustomer();

    Map<String, String> valid(Customer customer);
}
