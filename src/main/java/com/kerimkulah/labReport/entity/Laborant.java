package com.kerimkulah.labReport.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Laborant extends BaseEntity {

    // Pattern kullanmak için String seçtim.
    @NotNull(message = "Hastane Kimlik Numarası boş olamaz.")
    @Column(unique = true)
    @Pattern(regexp = "^[0-9]{7}$", message = "Hastane Kimlik Numarası 7 haneli rakamlardan oluşmalıdır.")
    private String hastaneKimlikNumarasi;

    @NotNull(message = "Laborant adı boş olamaz.") @NotBlank(message = "Laborant adı boş olamaz.")
    private String ad;

    @NotNull(message = "Laborant soyadı boş olamaz.") @NotBlank(message = "Laborant soyadı boş olamaz.")
    private String soyad;

    @JsonIgnore
    @OneToMany(mappedBy = "laborant")
    private List<Rapor> raporlar;
}
