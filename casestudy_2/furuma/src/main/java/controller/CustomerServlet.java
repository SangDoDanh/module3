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
            default:
                show(request, response);
                break;
        }
    }

    private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customerList = CUSTOMER_SERVICE.getAll();
        Map<Integer, String> customerTypeMap = CUSTOMER_SERVICE.getCustomerTypeAll();
        request.setAttribute("customerList", customerList);
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
            case "edit":
                edit(request, response);
                break;
            default:
                break;
        }
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
         CUSTOMER_SERVICE.create(customer);
         response.sendRedirect("/customer");
//         show(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        CUSTOMER_SERVICE.delete(id);
        response.sendRedirect("/customer");
    }
}
