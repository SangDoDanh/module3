import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ConvertUsdToVndServlet", value = "/convert-usd-vnd")
public class ConvertUsdToVndServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        convertUsdToVnd(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        convertUsdToVnd(request, response);
    }

    private void convertUsdToVnd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        float rate = Float.parseFloat(request.getParameter("rate"));
        float usd = Float.parseFloat(request.getParameter("usd"));
        float vnd = rate * usd;

        request.setAttribute("rate", rate);
        request.setAttribute("usd", usd);
        request.setAttribute("vnd", vnd);

        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}
