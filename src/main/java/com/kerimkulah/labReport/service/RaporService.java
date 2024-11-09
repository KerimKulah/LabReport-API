package com.kerimkulah.labReport.service;

import com.kerimkulah.labReport.entity.Rapor;
import java.util.List;

public interface RaporService {
    void createRapor(Rapor rapor, long laborantId);
    void deleteRaporById(Long id);
    List<Rapor> getAllRapor();
    Rapor getRaporById(Long id);
    void updateRapor(Rapor rapor, Long raporId);
    List<Rapor> searchRapor(String search);
    List<Rapor> getAllRaporOrderedByDate();
}
