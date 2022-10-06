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
}
