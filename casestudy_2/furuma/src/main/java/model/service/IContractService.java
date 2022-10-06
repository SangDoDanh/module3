package model.service;

import model.model.Contract;

import java.util.List;
import java.util.Map;

public interface IContractService {
    List<Contract> getAll();

    Map<Integer, String> getEmployeeMap();

    Map<Integer, String> getCustomerMap();

    Map<Integer, String> getFacilityMap();

    Map<Integer, String> getAttachMapMap();
}
