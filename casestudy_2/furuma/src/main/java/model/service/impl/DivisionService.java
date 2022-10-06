package model.service.impl;

import model.repository.division_repo.IDivisionRepository;
import model.repository.division_repo.impl.DivisionRepository;
import model.service.IDivisionService;

import java.util.Map;

public class DivisionService implements IDivisionService {
    private static final IDivisionRepository I_DIVISION_REPOSITORY = new DivisionRepository();
    @Override
    public Map<Integer, String> getAll() {
        return I_DIVISION_REPOSITORY.getAll();
    }
}
