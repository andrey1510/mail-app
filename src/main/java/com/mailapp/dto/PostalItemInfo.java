package com.mailapp.dto;

import com.mailapp.entities.PostalItem;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostalItemInfo {

    @Schema(description = "Информация о почтовом отправлении.")
    private PostalItem postalItem;

    @Schema(description = "История почтового отправления.")
    private List<PostalHistoryOfItem> postalHistoryOfItem;

}
