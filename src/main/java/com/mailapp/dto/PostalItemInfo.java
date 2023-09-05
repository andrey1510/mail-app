package com.mailapp.dto;

import com.mailapp.entities.PostalItem;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostalItemInfo {

    @Schema(description = "Информация о почтовом отправлении.")
    private PostalItem postalItem;

    @Schema(description = "История почтового отправления.")
    private List<PostalHistoryOfItem> postalHistoryOfItem;

}
