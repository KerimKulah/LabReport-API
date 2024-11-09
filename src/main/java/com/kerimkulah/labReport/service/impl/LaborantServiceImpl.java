package com.kerimkulah.labReport.service.impl;

import com.kerimkulah.labReport.entity.Laborant;
import com.kerimkulah.labReport.repository.LaborantRepository;
import com.kerimkulah.labReport.service.LaborantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LaborantServiceImpl implements LaborantService {

    private final LaborantRepository laborantRepository;

    @Override
    public void createLaborant(@Valid Laborant laborant) {
        laborantRepository.save(laborant);
    }

    @Override
    public void deleteLaborantById(Long id) {
        if (!laborantRepository.existsById(id)) {
            throw new NoSuchElementException("Bu ID ile laborant bulunamadı.");
        }
        laborantRepository.deleteById(id);
    }

    @Override
    public List<Laborant> getAllLaborant() {
        return laborantRepository.findAll();
    }

    @Override
    public Laborant getLaborantById(Long id) {
        return laborantRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bu ID ile laborant bulunamadı."));
    }
}
