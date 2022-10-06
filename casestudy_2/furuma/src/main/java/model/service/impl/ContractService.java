package model.service.impl;

import model.model.Contract;
import model.repository.contract_repo.IContractRepository;
import model.repository.contract_repo.impl.ContractRepository;
import model.service.IContractService;

import java.util.List;
import java.util.Map;

public class ContractService implements IContractService {
    private static final IContractRepository CONTRACT_REPOSITORY = new ContractRepository();
    @Override
    public List<Contract> getAll() {
        return CONTRACT_REPOSITORY.getAll();
    }

    @Override
    public Map<Integer, String> getEmployeeMap() {
        return CONTRACT_REPOSITORY.getEmployeeMap();
    }

    @Override
    public Map<Integer, String> getCustomerMap() {
        return CONTRACT_REPOSITORY.getCustomerMap();
    }

    @Override
    public Map<Integer, String> getFacilityMap() {
        return CONTRACT_REPOSITORY.getFacilityMap();
    }

    @Override
    public Map<Integer, String> getAttachMapMap() {
        return CONTRACT_REPOSITORY.getAttachMapMap();
    }
}
