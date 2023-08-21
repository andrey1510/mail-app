package com.mailapp.entities;

import com.mailapp.enums.PostalStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "postal_history")
public class PostalHistory {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    @Schema(requiredMode = REQUIRED, description = "ID записи в истории.")
    private UUID id;

    @Column(name = "postal_status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Schema(requiredMode = REQUIRED, description = "Статус почтового отправления.")
    private PostalStatus postalStatus;

    @OneToOne()
    private PostalItem postalItem;

    @OneToOne()
    private PostalOffice postalOffice;
//
//    @Column(name = "timestamp", updatable = false, nullable = false)
//    private Timestamp timestamp;

    public PostalHistory(PostalStatus postalStatus, PostalItem postalItem) {
        this.postalStatus = postalStatus;
        this.postalItem = postalItem;
    }

    public PostalHistory(PostalStatus postalStatus, PostalItem postalItem, PostalOffice postalOffice) {
        this.postalStatus = postalStatus;
        this.postalItem = postalItem;
        this.postalOffice = postalOffice;
    }
}
