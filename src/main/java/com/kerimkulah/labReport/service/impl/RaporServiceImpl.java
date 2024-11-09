package com.kerimkulah.labReport.service.impl;

import com.kerimkulah.labReport.entity.Rapor;
import com.kerimkulah.labReport.entity.Laborant;
import com.kerimkulah.labReport.repository.LaborantRepository;
import com.kerimkulah.labReport.repository.RaporRepository;
import com.kerimkulah.labReport.service.RaporService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor // Constructor injection için kullanıyorum yer kaplamasın diye
public class RaporServiceImpl implements RaporService {

    private final RaporRepository raporRepository;
    private final LaborantRepository laborantRepository;

    @Override
    public void createRapor(@Valid Rapor rapor, long laborantId) {
        Laborant laborant = laborantRepository.findById(laborantId)
                .orElseThrow(() -> new NoSuchElementException("Bu ID ile laborant bulunamadı."));
        rapor.setLaborant(laborant);
        rapor.setRaporTarihi(LocalDateTime.now());
        raporRepository.save(rapor);
    }

    @Override
    public void deleteRaporById(Long id) {
        if (!raporRepository.existsById(id)) {
            throw new NoSuchElementException("Bu ID ile rapor bulunamadı.");
        }
        raporRepository.deleteById(id);
    }
    @Override
    public List<Rapor> getAllRapor() {
        return raporRepository.findAll();
    }

    @Override
    public Rapor getRaporById(Long id) {
        return raporRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bu ID ile rapor bulunamadı."));
    }

    @Override
    public void updateRapor(Rapor yeniRapor, Long raporId) {
        Rapor eskiRapor = raporRepository.findById(raporId)
                .orElseThrow(() -> new NoSuchElementException("Bu ID ile rapor bulunamadı."));

        // Null ve blank kontrolü yaparak sadece değişen alanları güncelliyorum
        if (yeniRapor.getHastaAd() != null && !yeniRapor.getHastaAd().isBlank()) {
            eskiRapor.setHastaAd(yeniRapor.getHastaAd());
        }
        if (yeniRapor.getHastaSoyad() != null && !yeniRapor.getHastaSoyad().isBlank()) {
            eskiRapor.setHastaSoyad(yeniRapor.getHastaSoyad());
        }
        if (yeniRapor.getHastaTcNo() != null && !yeniRapor.getHastaTcNo().isBlank()) {
            eskiRapor.setHastaTcNo(yeniRapor.getHastaTcNo());
        }
        if (yeniRapor.getRaporTarihi() != null) {
            eskiRapor.setRaporTarihi(yeniRapor.getRaporTarihi());
        }

        // Yeni Raporda laborant bilgisi geçildi mi kontrolü ve ona göre diğer kontroller
        Laborant yeniLaborant = yeniRapor.getLaborant();
        if (yeniLaborant != null && !yeniLaborant.getAd().isBlank() && !yeniLaborant.getSoyad().isBlank()) {
           Laborant databaseLaborant = laborantRepository.findById(yeniLaborant.getId()).orElseThrow(()
                    -> new NoSuchElementException("Bu ID ile laborant bulunamadı."));
           if(!databaseLaborant.getAd().equals(yeniLaborant.getAd()) || !databaseLaborant.getSoyad().equals(yeniLaborant.getSoyad()))
            {
               throw new IllegalArgumentException("Laborant bilgileri hatalı.");
            }
            eskiRapor.setLaborant(yeniLaborant);
        }
        raporRepository.save(eskiRapor);
    }

    @Override
    public List<Rapor> searchRapor(String search) {
        return raporRepository.findByHastaAdContainingOrHastaSoyadContainingOrHastaTcNoContainingOrLaborantAdContainingOrLaborantSoyadContaining
                (search, search, search, search, search);
    }

    @Override
    public List<Rapor> getAllRaporOrderedByDate() {
        return raporRepository.findAllByOrderByRaporTarihiAsc();
    }
}
