package model.service.impl;

import model.model.Customer;
import model.repository.customer_repo.ICustomerRepository;
import model.repository.customer_repo.impl.CustomerRepository;
import model.service.ICustomerService;

import java.util.List;
import java.util.Map;

public class CustomerService implements ICustomerService {
    private static final ICustomerRepository CUSTOMER_REPOSITORY = new CustomerRepository();
    @Override
    public List<Customer> getAll() {
        return CUSTOMER_REPOSITORY.getAll();
    }

    @Override
    public Map<Integer, String> getCustomerTypeAll() {
        return CUSTOMER_REPOSITORY.getCustomerTypeAll();
    }

    @Override
    public void delete(int id) {
        CUSTOMER_REPOSITORY.delete(id);
    }

    @Override
    public void create(Customer customer) {
        CUSTOMER_REPOSITORY.create(customer);
    }

    @Override
    public void update(Customer customer) {
        CUSTOMER_REPOSITORY.update(customer);
    }

    @Override
    public List<Customer> search(String keySearch, String customerTypeSearch) {
        return CUSTOMER_REPOSITORY.search(keySearch, customerTypeSearch);
    }

    @Override
    public List<Customer> search(String keySearch, String customerTypeSearch, int gender) {
        return CUSTOMER_REPOSITORY.search(keySearch, customerTypeSearch, gender);
    }

    @Override
    public String click() {
        return CUSTOMER_REPOSITORY.click();
    }

    @Override
    public String getSomething(int id) {
        return CUSTOMER_REPOSITORY.getSomething(id);
    }
}
