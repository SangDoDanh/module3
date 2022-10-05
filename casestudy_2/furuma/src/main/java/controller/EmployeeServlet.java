package controller;

import model.model.Employee;
import model.service.IEmployeeService;
import model.service.IPositionService;
import model.service.impl.EmployeeService;
import model.service.impl.PositionService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "EmployeeServlet", urlPatterns = "/employee")
public class EmployeeServlet extends HttpServlet {

    private static final IEmployeeService EMPLOYEE_SERVICE = new EmployeeService();
    private static final IPositionService POSITION_SERVICE = new PositionService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employeeList = EMPLOYEE_SERVICE.getAll();
        Map<Integer, String> positionMap = POSITION_SERVICE.getAll();
        request.setAttribute("employeeList", employeeList);
        request.setAttribute("positionMap", positionMap);

        request.getRequestDispatcher("/employee/employee.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
