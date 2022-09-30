package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.CustomerServiceImpl;
import com.codegym.service.ICustomerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customers")
public class CustomerServlet extends HttpServlet {

    private ICustomerService customerService = new CustomerServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String name = request.getParameter("customerName");
        String email = request.getParameter("customerEmail");
        String address = request.getParameter("customerAddress");
        int id;
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                createCustomer(request, response, name, email, address);
                break;
            case "edit":
                id = Integer.parseInt(request.getParameter("customerId"));
                Customer customer = new Customer(id, name, email, address);
                editCustomer(request, response, customer);
                break;
            case "delete":
                id = Integer.parseInt(request.getParameter("customerId"));
                deleteCustomer(request, response, id);
                break;
            case "search":
                String search = request.getParameter("search");
                searchCustomer(request, response, search);
                break;
            default:
                List<Customer> customers = this.customerService.findAll();
                listCustomers(request, response, customers);
                break;
        }
    }

    private void searchCustomer(HttpServletRequest request, HttpServletResponse response, String search) {
        search = search.toLowerCase();
        List<Customer> customers = this.customerService.findAll();
        List<Customer> customersBySearch = new ArrayList<>();
        if(search.equals("")) {
            listCustomers(request, response, customers);
        } else {
            for(Customer customer : customers) {
                if(customer.getName().toLowerCase().contains(search)) {
                    customersBySearch.add(customer);
                }
            }
        }
        if(customersBySearch.size()> 0) {
            listCustomers(request, response, customersBySearch);
        } else {
            listCustomers(request, response, null);
        }
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response, int id) {
        this.customerService.remove(id);
        List<Customer> customers = this.customerService.findAll();
        listCustomers(request, response, customers);
    }

    private void editCustomer(HttpServletRequest request, HttpServletResponse response, Customer customer) {
        this.customerService.save(customer);
        List<Customer> customers = this.customerService.findAll();
        listCustomers(request, response, customers);
    }

    private void createCustomer(HttpServletRequest request, HttpServletResponse response, String name, String email, String address) {
        List<Customer> customers = this.customerService.findAll();
        int id = customers.get(customers.size() - 1).getId() + 1;
        customers.add(new Customer(id, name, email, address));
        listCustomers(request, response, customers);
    }

    private void listCustomers(HttpServletRequest request, HttpServletResponse response, List<Customer> customers) {
        request.setAttribute("customers", customers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/list.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
