package controller;

import model.model.Contract;
import model.service.IContractService;
import model.service.impl.ContractService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ContractServlet", urlPatterns = "/contract")
public class ContractServlet extends HttpServlet {
    private static final IContractService CONTRACT_SERVICE = new ContractService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Contract> contractList = CONTRACT_SERVICE.getAll();
        Map<Integer, String> employeeMap = CONTRACT_SERVICE.getEmployeeMap();
        Map<Integer, String> customerMap = CONTRACT_SERVICE.getCustomerMap();
        Map<Integer, String> facilityMap = CONTRACT_SERVICE.getFacilityMap();
        Map<Integer, String> attachMap = CONTRACT_SERVICE.getAttachMapMap();
        request.setAttribute("contractList", contractList);
        request.setAttribute("employeeMap", employeeMap);
        request.setAttribute("customerMap", customerMap);
        request.setAttribute("facilityMap", facilityMap);
        request.setAttribute("attachMap", attachMap);
        request.getRequestDispatcher("/contract/contract.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
