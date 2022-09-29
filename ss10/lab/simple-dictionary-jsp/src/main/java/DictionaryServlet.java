import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(urlPatterns = "/dictionary.jsp")
public class DictionaryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> data = new HashMap<>();
        data.put("hello", "Xin chao");
        data.put("how", "the nao");
        data.put("book", "quyen sach");
        String searchInput = req.getParameter("search");
        String result = data.get(searchInput) != null ? data.get(searchInput) : "Chua cap nhat!";
        req.setAttribute("search", searchInput);
        req.setAttribute("result", result);
        req.getRequestDispatcher("result.jsp").forward(req, resp);
    }

}
