import model.Customer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DisplayCustomerListServlet", urlPatterns = "/display-customers")
public class DisplayCustomerListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customerList = getCustomerList();
        request.setAttribute("customerList", customerList);
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private List<Customer> getCustomerList() {
        List<Customer> customerList = new ArrayList<>();
        String customerImageUrl = "images/customer.png";
        for(int i = 0; i < 15; i++) {
            customerList.add(new Customer(
                    "Customer " + i, LocalDate.now(), "Ha noi", customerImageUrl
            ));
        }
        return  customerList;
    }
}
