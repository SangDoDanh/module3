import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CalcServlet", urlPatterns = {"/calc", "/hautien-ngu"})
public class CalcServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String des = request.getParameter("des");
            double price = Double.parseDouble(request.getParameter("price"));
            double discount = Double.parseDouble(request.getParameter("discount"));

            double discountAmount = price * discount * 0.01;
            double discountPrice = price - discountAmount;
            String priceInvalid = "price invalid";
            String disInvalid = "discount invalid";
            if(price < 0) {
                request.setAttribute("error", priceInvalid);
                request.getRequestDispatcher("/Error.jsp").forward(request, response);
            } else if(discount > 100 || discount < 0) {
                request.setAttribute("error", disInvalid);
                request.getRequestDispatcher("/Error.jsp").forward(request, response);
            } else {
                request.setAttribute("des", des);
                request.setAttribute("price", price);
                request.setAttribute("discount", discount);
                request.setAttribute("discountAmount", discountAmount);
                request.setAttribute("discountPrice", discountPrice);
                request.getRequestDispatcher("/Result.jsp").forward(request, response);
            }

    }
}
