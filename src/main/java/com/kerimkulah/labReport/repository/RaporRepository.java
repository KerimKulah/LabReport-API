package com.kerimkulah.labReport.repository;

import com.kerimkulah.labReport.entity.Rapor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RaporRepository extends JpaRepository<Rapor, Long> {
    // Raporlarda hasta adı, soyadı, kimlik numarası, laborant adı ve soyadı ile arama yapılabilmesi için
    List<Rapor> findByHastaAdContainingOrHastaSoyadContainingOrHastaTcNoContainingOrLaborantAdContainingOrLaborantSoyadContaining
            (String hastaAd, String hastaSoyad, String hastaKimlikNumarasi, String laborantAd, String laborantSoyad);

    // Raporlarda rapor tarihine göre sıralama yapılabilmesi için
    List<Rapor> findAllByOrderByRaporTarihiAsc();
}
