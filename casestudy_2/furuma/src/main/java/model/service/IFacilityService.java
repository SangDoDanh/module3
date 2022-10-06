package model.service;

import model.model.Facility;

import java.util.List;
import java.util.Map;

public interface IFacilityService {
    List<Facility> getAll();

    Map<Integer, String> getFacilityTypeAll();

    Map<Integer, String> getrentTypeMapAll();
}
