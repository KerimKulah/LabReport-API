package com.kerimkulah.labReport.repository;

import com.kerimkulah.labReport.entity.Laborant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaborantRepository extends JpaRepository<Laborant, Long> {
}
