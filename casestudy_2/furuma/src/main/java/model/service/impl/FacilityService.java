package model.service.impl;

import model.model.Facility;
import model.repository.facility_repo.IFacilityRepository;
import model.repository.facility_repo.impl.FacilityRepository;
import model.service.IFacilityService;

import java.util.List;
import java.util.Map;

public class FacilityService implements IFacilityService {
    private static final IFacilityRepository FACILITY_REPOSITORY = new FacilityRepository();
    @Override
    public List<Facility> getAll() {
        return FACILITY_REPOSITORY.getAll();
    }

    @Override
    public Map<Integer, String> getFacilityTypeAll() {
        return FACILITY_REPOSITORY.getFacilityTypeAll();
    }

    @Override
    public Map<Integer, String> getrentTypeMapAll() {
        return FACILITY_REPOSITORY.getrentTypeMapAll();
    }
}
