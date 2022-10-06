package model.repository.facility_repo;

import model.model.Facility;

import java.util.List;
import java.util.Map;

public interface IFacilityRepository {
    List<Facility> getAll();

    Map<Integer, String> getFacilityTypeAll();

    Map<Integer, String> getrentTypeMapAll();
}
