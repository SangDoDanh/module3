package model.service.impl;

import model.repository.education_repo.IEducationRepository;
import model.repository.education_repo.impl.EducationRepository;
import model.service.IEducationService;

import java.util.Map;

public class EducationService implements IEducationService {
    private static final IEducationRepository EDUCATION_REPOSITORY = new EducationRepository();
    @Override
    public Map<Integer, String> getAll() {
        return EDUCATION_REPOSITORY.getAll();
    }
}
