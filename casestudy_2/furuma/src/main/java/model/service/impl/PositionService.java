package model.service.impl;

import model.repository.positon_repo.IPositionRepository;
import model.repository.positon_repo.impl.PositionRepository;
import model.service.IPositionService;

import java.util.Map;

public class PositionService implements IPositionService {
    private static final IPositionRepository POSITION_REPOSITORY = new PositionRepository();
    @Override
    public Map<Integer, String> getAll() {
       return POSITION_REPOSITORY.getAll();
    }

}
