package controller;

import model.model.Facility;
import model.service.IFacilityService;
import model.service.impl.FacilityService;

import java.util.List;

public class test {
    private static final IFacilityService FACILITY_SERVICE = new FacilityService();

    public static void main(String[] args) {
        List<Facility> facilityList = FACILITY_SERVICE.getAll();
        System.out.println("size: " +facilityList.size());
    }
}
