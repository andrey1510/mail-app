package com.mailapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "postal_office")
public class PostalOffice {

    @Id
    @Column(name = "office_index", nullable = false)
    private String officeIndex;

    @Column(name = "office_title", nullable = false)
    private String officeTitle;

    @Column(name = "office_address", nullable = false)
    private String officeAddress;

}
