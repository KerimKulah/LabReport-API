package com.kerimkulah.labReport.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rapor extends BaseEntity{

    @NotNull(message = "Dosya numarası boş olamaz.") @NotBlank(message = "Dosya numarası boş olamaz.")
    @Column(unique = true)
    private String dosyaNo;

    @NotNull(message = "Hasta adı boş olamaz.") @NotBlank(message = "Hasta adı boş olamaz.")
    private String hastaAd;

    @NotNull(message = "Hasta soyadı boş olamaz.") @NotBlank(message = "Hasta soyadı boş olamaz.")
    private String hastaSoyad;

    @NotNull(message = "Tanı başlığı boş olamaz.") @NotBlank(message = "Tanı başlığı boş olamaz.")
    private String taniBasligi;

    @NotNull(message = "Tanı detayları boş olamaz.") @NotBlank(message = "Tanı detayları boş olamaz.")
    private String taniDetaylari;

    @Pattern(regexp = "^[1-9][0-9]{10}$", message = "TC Kimlik Numarası 11 haneli olmalı ve ilk hane 0 olamaz.")
    private String hastaTcNo;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime raporTarihi; // Rapor oluşturulduğunda otomatik atanacak.

    private String fizikselRaporFotografUrl;

    @ManyToOne
    @JoinColumn(name = "Laborant_id")
    private Laborant laborant ;

}
