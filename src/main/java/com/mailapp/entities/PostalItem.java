package com.mailapp.entities;

import com.mailapp.enums.PostalType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "postal_item")
public class PostalItem {

    @Id
    @GeneratedValue
    @Column(name = "identifier", updatable = false, nullable = false)
    private UUID identifier;

    @Column(name = "postal_type", nullable = false)
    private PostalType postalType;

    @Column(name = "recipient_index", nullable = false)
    private String recipientIndex;

    @Column(name = "recipient_address", nullable = false)
    private String recipientAddress;

    @Column(name = "recipient_name", nullable = false)
    private String recipientName;

}
