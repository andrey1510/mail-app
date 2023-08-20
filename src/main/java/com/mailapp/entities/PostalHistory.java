package com.mailapp.entities;

import com.mailapp.enums.PostalStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "postal_history")
public class PostalHistory {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "postal_status", nullable = false)
    private PostalStatus postalStatus;

    @ManyToOne()
    private PostalItem postalItem;

    @ManyToOne()
    private PostalItem postalOffice;

}
