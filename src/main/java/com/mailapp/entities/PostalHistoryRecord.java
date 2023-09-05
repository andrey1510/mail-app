package com.mailapp.entities;

import com.mailapp.enums.PostalStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Builder
@Table(name = "postal_history_record")
public class PostalHistoryRecord {

    @Id
    @GeneratedValue
    @Column(name = "history_record_id", updatable = false, nullable = false)
    @Schema(requiredMode = REQUIRED, description = "ID записи в истории.")
    private UUID historyRecordId;

    @Column(name = "postal_status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Schema(requiredMode = REQUIRED, description = "Статус почтового отправления.")
    private PostalStatus postalStatus;

    @ManyToOne(cascade=CascadeType.ALL)
    private PostalItem postalItem;

    @ManyToOne(cascade=CascadeType.ALL)
    private PostalOffice postalOffice;

    @Column(name = "timestamp", updatable = false, nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp timestamp;

    public PostalHistoryRecord(PostalStatus postalStatus, PostalItem postalItem) {
        this.postalStatus = postalStatus;
        this.postalItem = postalItem;
    }

    public PostalHistoryRecord(PostalStatus postalStatus, PostalItem postalItem, PostalOffice postalOffice) {
        this.postalStatus = postalStatus;
        this.postalItem = postalItem;
        this.postalOffice = postalOffice;
    }

}
