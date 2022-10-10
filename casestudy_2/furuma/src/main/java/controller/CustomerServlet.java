package controller;

import model.model.Customer;
import model.service.ICustomerService;
import model.service.impl.CustomerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    private static final ICustomerService CUSTOMER_SERVICE = new CustomerService();
    private static final int AMOUNT_CUSTOMER = 5;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null)
            action ="";
        switch (action) {
            case "delete":
                delete(request, response);
                break;
            case"create":
                Map<Integer, String> customerTypeMap = CUSTOMER_SERVICE.getCustomerTypeAll();
                request.setAttribute("customerTypeMap", customerTypeMap);
                request.getRequestDispatcher("/customer/add.jsp").forward(request, response);
                break;
            case "search":
                search(request, response);
                break;
            default:
                show(request, response);
                break;
        }
    }

    private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int countPage = CUSTOMER_SERVICE.getCountPage(AMOUNT_CUSTOMER);
        String indexPageString = request.getParameter("indexPage");
        int indexPage = CUSTOMER_SERVICE.getIndexPage(countPage, indexPageString);
        int totalCustomer = CUSTOMER_SERVICE.getCountCustomer();
        List<Customer> customerListPage = CUSTOMER_SERVICE.getAll(indexPage, AMOUNT_CUSTOMER);
        Map<Integer, String> customerTypeMap = CUSTOMER_SERVICE.getCustomerTypeAll();
        request.setAttribute("indexPage", indexPage);
        request.setAttribute("countPage", countPage);
        request.setAttribute("now", customerListPage.size());
        request.setAttribute("total", totalCustomer);
        request.setAttribute("customerList", customerListPage);
        request.setAttribute("customerTypeMap", customerTypeMap);
        request.getRequestDispatcher("/customer/customer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null)
            action ="";
        switch (action) {
            case "delete":
                delete(request, response);
                break;
            case "create":
                create(request, response);
                break;
            case "edit":
                edit(request, response);
                break;
            case "search":
                search(request, response);
            default:
                break;
        }
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keySearch = request.getParameter("keySearch");
        String customerTypeSearch = request.getParameter("customerTypeSearch");
        String genderSearch = request.getParameter("genderSearch");
        List<Customer> customerListSearch;
        if(genderSearch.equals("")) {
            customerListSearch = CUSTOMER_SERVICE.search(keySearch, customerTypeSearch);
        } else {
            int gender = Integer.parseInt(genderSearch);
            customerListSearch = CUSTOMER_SERVICE.search(keySearch, customerTypeSearch, gender);
        }
        Map<Integer, String> customerTypeMap = CUSTOMER_SERVICE.getCustomerTypeAll();
        request.setAttribute("customerTypeMap", customerTypeMap);
        request.setAttribute("customerList", customerListSearch);
        request.getRequestDispatcher("/customer/customer.jsp").forward(request, response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
         int id = Integer.parseInt(request.getParameter("id"));
         String name = request.getParameter("name");
         int customerTypeId = Integer.parseInt(request.getParameter("customerTypeId"));
         Date dayOfBirth = Date.valueOf(request.getParameter("dayOfBirth"));
         int gender = Integer.parseInt(request.getParameter("gender"));
         String idCard = request.getParameter("idCard");
         String phoneNumber = request.getParameter("phoneNumber");
         String email = request.getParameter("email");
         String address = request.getParameter("address");
        Customer customer = new Customer(id, name,customerTypeId,dayOfBirth, gender, idCard,
                phoneNumber, email, address);
        CUSTOMER_SERVICE.update(customer);
        response.sendRedirect("/customer");
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
         String name = request.getParameter("name");
         int customerTypeId = Integer.parseInt(request.getParameter("customerTypeId"));
         Date date = Date.valueOf(request.getParameter("dayOfBirth"));
         int gender = Integer.parseInt(request.getParameter("gender"));
         String idCard = request.getParameter("idCard");
         String phoneNumber = request.getParameter("phoneNumber");
         String email = request.getParameter("email");
         String address =request.getParameter("address");
         Customer customer = new Customer(name, customerTypeId, date, gender, idCard, phoneNumber, email, address);
         Map<String, String> validCustomerMap = CUSTOMER_SERVICE.valid(customer);
         if(validCustomerMap.size() == 0) {
             CUSTOMER_SERVICE.create(customer);
             response.sendRedirect("/customer");
         } else {
             Map<Integer, String> customerTypeMap = CUSTOMER_SERVICE.getCustomerTypeAll();
             request.setAttribute("customerTypeMap", customerTypeMap);
             request.setAttribute("validCustomerMap", validCustomerMap);
             request.getRequestDispatcher("/customer/add.jsp").forward(request, response);
         }

    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        CUSTOMER_SERVICE.delete(id);
        response.sendRedirect("/customer");
    }
}
