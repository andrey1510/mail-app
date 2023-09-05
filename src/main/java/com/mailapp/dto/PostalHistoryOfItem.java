package com.mailapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mailapp.entities.PostalOffice;
import com.mailapp.enums.PostalStatus;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class PostalHistoryOfItem {

    @Schema(example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", description = "ID записи в истории.")
    private UUID historyRecordId;

    @Schema(description = "Время регистрации записи в истории.")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss a z")
    private Timestamp timestamp;

    @Schema(description = "Статус почтового отправления.")
    private PostalStatus postalStatus;

    @Schema(description = "Почтовое отделение.")
    private PostalOffice postalOffice;


}
