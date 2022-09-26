import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "CalculateDiscountServlet", urlPatterns = "/display-discount")
public class CalculateDiscountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String description = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));
            double discount = Double.parseDouble(request.getParameter("discount"));
            double discountAmount = price * discount * 0.01;
            double discountPrice = price - discountAmount;
            String priceInvalid = "price invalid";
            String discountInvalid = "discount invalid";

            if(price < 0) {
                request.setAttribute("error", priceInvalid);
                request.getRequestDispatcher("/Error.jsp").forward(request, response);
            } else if(discount > 100 || discount < 0) {
                request.setAttribute("error", discountInvalid);
                request.getRequestDispatcher("/Error.jsp").forward(request, response);
            } else {
                request.setAttribute("description", description);
                request.setAttribute("price", price);
                request.setAttribute("discount", discount);
                request.setAttribute("discountAmount", discountAmount);
                request.setAttribute("discountPrice", discountPrice);
                request.getRequestDispatcher("/Result.jsp").forward(request, response);
            }

    }
}
