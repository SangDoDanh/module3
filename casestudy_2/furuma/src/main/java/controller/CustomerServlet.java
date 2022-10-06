package controller;

import model.model.Customer;
import model.service.ICustomerService;
import model.service.impl.CustomerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
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
                response.sendRedirect("/customer/add.jsp");
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
            default:
                show(request, response);
                break;
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        CUSTOMER_SERVICE.delete(id);
        response.sendRedirect("/customer");
    }
}
