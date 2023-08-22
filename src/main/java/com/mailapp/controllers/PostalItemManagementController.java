package com.mailapp.controllers;

import com.mailapp.entities.PostalItem;
import com.mailapp.services.PostalItemService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/postal_item/")
@RequiredArgsConstructor
public class PostalItemManagementController {

    private final PostalItemService postalItemService;

    @GetMapping("{postal_item_id}")
    @Operation(description = "Найти почтовое отправление по идентификатору.")
    public Optional<PostalItem> findById (@PathVariable("postal_item_id") UUID postalItemId) {
        return postalItemService.findById(postalItemId);
    }

}
