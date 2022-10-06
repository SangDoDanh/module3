package controller;

import model.model.Facility;
import model.service.IFacilityService;
import model.service.impl.FacilityService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "FacilityServlet", urlPatterns = "/facility")
public class FacilityServlet extends HttpServlet {
    private static final IFacilityService FACILITY_SERVICE = new FacilityService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Integer, String> facilityTypeMap = FACILITY_SERVICE.getFacilityTypeAll();
        Map<Integer, String> rentTypeMap = FACILITY_SERVICE.getrentTypeMapAll();

        List<Facility> facilityList = FACILITY_SERVICE.getAll();
        request.setAttribute("rentTypeMap",rentTypeMap);
        request.setAttribute("facilityList",facilityList);
        request.setAttribute("facilityTypeMap", facilityTypeMap);
        request.getRequestDispatcher("/facility/facility.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
