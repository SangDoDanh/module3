package model.service.impl;

import model.model.Customer;
import model.repository.customer_repo.ICustomerRepository;
import model.repository.customer_repo.impl.CustomerRepository;
import model.service.ICustomerService;

import java.util.HashMap;
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
    public int getCountPage(int amountPage) {
        int countPage = CUSTOMER_REPOSITORY.getCountPage();
        if(countPage == 0) {
            return 1;
        }
        if(countPage % amountPage == 0) {
            return countPage/ amountPage;
        }
        return (countPage/ amountPage) + 1;
    }

    @Override
    public List<Customer> getAll(int indexPage, int amountCustomer) {
        return CUSTOMER_REPOSITORY.getAll(indexPage, amountCustomer);
    }

    @Override
    public int getIndexPage(int countPage, String indexPageString) {
        int pageIndex = 1;
        if(indexPageString != null) {
            pageIndex = Integer.parseInt(indexPageString);
            if(pageIndex > countPage) {
                pageIndex = 1;
            } else if(pageIndex == 0) {
                pageIndex = countPage;
            }
        }
        return pageIndex;
    }

    @Override
    public int getCountCustomer() {
        return CUSTOMER_REPOSITORY.getCountPage();
    }

    @Override
    public Map<String, String> valid(Customer customer) {
        Map<String, String> mapError = new HashMap<>();
        String regexName = "/^[a-zA-ZAÁÀẢÃẠÂẤẦẨẪẬĂẮẰẲẴẶEÉÈẺẼẸÊẾỀỂỄỆIÍÌỈĨỊOÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢUÚÙỦŨỤƯỨỪỬỮỰYÝỲỶỸỴĐaáàảãạâấầẩẫậăắằẳẵặeéèẻẽẹêếềểễệiíìỉĩịoóòỏõọôốồổỗộơớờởỡợuúùủũụưứừửữựyýỳỷỹỵđ\\s]+$/";
        if(customer.getName().equals("") || !customer.getName().matches(regexName) || !checkGood(customer.getName())) {
            mapError.put("name", "Ten Khong duoc de trong , Khong duoc chua so, phai in hoa chu cai dau");
        }
        return mapError;
    }

    private boolean checkGood(String words) {
        String wordsArray[] = words.split(" ");
        for(String word : wordsArray) {
            String wordFirst = word.substring(0,1);
            if(!wordFirst.equals(wordFirst.toUpperCase()))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println( new CustomerService().checkGood("Do danh sang"));
        System.out.println(new CustomerService().checkGood("mot so nao do"));
    }
}
