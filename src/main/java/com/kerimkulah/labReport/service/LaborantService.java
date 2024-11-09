package com.kerimkulah.labReport.service;

import com.kerimkulah.labReport.entity.Laborant;
import java.util.List;

public interface LaborantService {
    void createLaborant(Laborant laborant);
    void deleteLaborantById(Long id);
    List<Laborant> getAllLaborant();
    Laborant getLaborantById(Long id);
}
