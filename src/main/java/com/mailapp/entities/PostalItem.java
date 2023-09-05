package com.mailapp.entities;

import com.mailapp.enums.PostalType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Builder
@Table(name = "postal_item")
public class PostalItem {

    @Id
    @GeneratedValue
    @Column(name = "postal_item_id", updatable = false, nullable = false)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", description = "Идентификатор почтового отправления.")
    private UUID postalItemId;

    @Column(name = "postal_type", nullable = false)
    @Enumerated(EnumType.STRING)
    @Schema(requiredMode = REQUIRED, example = "Письмо", description = "Тип почтового отправления")
    private PostalType postalType;

    @Column(name = "recipient_index", nullable = false)
    @Schema(requiredMode = REQUIRED, example = "123001", description = "Индекс получателя")
    private String recipientIndex;

    @Column(name = "recipient_address", nullable = false)
    @Schema(requiredMode = REQUIRED, example = "Москва, ул. Лесная, д. 4, кв. 10", description = "Адрес получателя")
    private String recipientAddress;

    @Column(name = "recipient_name", nullable = false)
    @Schema(requiredMode = REQUIRED, example = "Кузнецов Петр Михайлович", description = "ФИО получателя")
    private String recipientName;

}
