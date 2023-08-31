package com.mailapp.entities;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.*;

import lombok.*;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "postal_office")
public class PostalOffice {

    @Id
    @Column(name = "office_index", nullable = false)
    @Schema(requiredMode = REQUIRED, example = "125009", description = "Индекс почтового отделения.")
    private String officeIndex;

    @Column(name = "office_title", nullable = false)
    @Schema(requiredMode = REQUIRED, example = "Почтовое отделение № 125009", description = "Название почтового отделения.")
    private String officeTitle;

    @Column(name = "office_address", nullable = false)
    @Schema(requiredMode = REQUIRED, example = "г. Москва, ул. Тверская, дом 9, стр. 5", description = "Адрес почтового отделения.")
    private String officeAddress;

}
