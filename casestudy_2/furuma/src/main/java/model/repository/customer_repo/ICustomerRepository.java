package model.repository.customer_repo;

import model.model.Customer;

import java.util.List;
import java.util.Map;

public interface ICustomerRepository {
    List<Customer> getAll();

    Map<Integer, String> getCustomerTypeAll();

    void delete(int id);

    void create(Customer customer);

    void update(Customer customer);

    List<Customer> search(String keySearch, String customerTypeSearch);

    List<Customer> search(String keySearch, String customerTypeSearch, int gender);

    String click();
    List<Customer> findAll();

    String getSomething(int id);
}
