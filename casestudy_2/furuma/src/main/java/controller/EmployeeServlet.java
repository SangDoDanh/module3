package controller;

import model.model.Employee;
import model.service.IDivisionService;
import model.service.IEducationService;
import model.service.IEmployeeService;
import model.service.IPositionService;
import model.service.impl.DivisionService;
import model.service.impl.EducationService;
import model.service.impl.EmployeeService;
import model.service.impl.PositionService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@WebServlet(name = "EmployeeServlet", urlPatterns = "/employee")
public class EmployeeServlet extends HttpServlet {

    private static final IEmployeeService EMPLOYEE_SERVICE = new EmployeeService();
    private static final IPositionService POSITION_SERVICE = new PositionService();
    private static final IEducationService EDUCATION_SERVICE = new EducationService();
    private static final IDivisionService DIVISION_SERVICE = new DivisionService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if(action == null) {
            action = "";
        }
        switch (action) {
            default:
                show(request, response);
        }
    }

    private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employeeList = EMPLOYEE_SERVICE.getAll();
        Map<Integer, String> positionMap = POSITION_SERVICE.getAll();
        Map<Integer, String> educationMap = EDUCATION_SERVICE.getAll();
        Map<Integer, String> divisionMap = DIVISION_SERVICE.getAll();
        request.setAttribute("employeeList", employeeList);
        request.setAttribute("positionMap", positionMap);
        request.setAttribute("educationMap", educationMap);
        request.setAttribute("divisionMap", divisionMap);
        request.getRequestDispatcher("/employee/employee.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if(action == null) {
            action = "";
        }
        switch (action) {
            case "delete":
                delete(request, response);
                break;
            case "edit":
                edit(request, response);
                break;
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
         int id = Integer.parseInt(request.getParameter("id"));
         String name = request.getParameter("name");
         String idCard = request.getParameter("idCard");
         Date dateOfBirth = Date.valueOf(request.getParameter("dayOfBirth"));
         double salary = Double.parseDouble(request.getParameter("salary"));
         String email = request.getParameter("email");
         String phoneNumber = request.getParameter("phoneNumber");
         String address = request.getParameter("address");
         int positionId = Integer.parseInt(request.getParameter("positionId"));
         int educationDegreeId = Integer.parseInt(request.getParameter("educationID"));
         int divisionId = Integer.parseInt(request.getParameter("divisionID"));
         String userName = "";
         Employee employee = new Employee(name, dateOfBirth, idCard, salary, phoneNumber,email,address,positionId,educationDegreeId, divisionId);
         EMPLOYEE_SERVICE.edit(id, employee);
         response.sendRedirect("/employee");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idDelete = Integer.parseInt(request.getParameter("id"));
        EMPLOYEE_SERVICE.delete(idDelete);
        response.sendRedirect("/employee");
    }
}
