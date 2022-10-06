package model.repository.contract_repo;

import model.model.Contract;

import java.util.List;
import java.util.Map;

public interface IContractRepository {
    List<Contract> getAll();

    Map<Integer, String> getEmployeeMap();

    Map<Integer, String> getCustomerMap();

    Map<Integer, String> getFacilityMap();

    Map<Integer, String> getAttachMapMap();
}
