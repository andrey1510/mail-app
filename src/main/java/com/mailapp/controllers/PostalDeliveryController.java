package com.mailapp.controllers;

import com.mailapp.entities.PostalHistory;
import com.mailapp.entities.PostalItem;
import com.mailapp.entities.PostalOffice;
import com.mailapp.enums.PostalStatus;
import com.mailapp.services.PostalItemService;
import com.mailapp.services.PostalHistoryService;
import com.mailapp.services.PostalOfficeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/delivery/")
@RequiredArgsConstructor
public class PostalDeliveryController {

    private final PostalItemService postalItemService;
    private final PostalHistoryService postalHistoryService;
    private final PostalOfficeService postalOfficeService;

    @PostMapping("register_post")
    @Operation(description = "Зарегистрировать почтовое отправление.")
    public ResponseEntity<UUID> registerPostalItem(@RequestBody PostalItem postalItem) {

        PostalItem createdItem = postalItemService.createPostalItem(postalItem);

        postalHistoryService.createPostalHistory(new PostalHistory(PostalStatus.REGISTERED, createdItem));

        return new ResponseEntity<>(postalItem.getIdentifier(), HttpStatus.OK);
    }

    @PostMapping("get_post_to_office")
    @Operation(description = "Внести запись о прибытии почтового отправления в почтовое отделение")
    public ResponseEntity<PostalStatus> getPostalItemToPostalOffice(UUID identifier, String officeIndex) {

        PostalItem postalItem = postalItemService.getPostalItemByIdentifier(identifier);
        PostalOffice postalOffice = postalOfficeService.getPostalOfficeByOfficeIndex(officeIndex);
        PostalHistory postalHistory = new PostalHistory(PostalStatus.IN_OFFICE, postalItem, postalOffice);

        postalHistoryService.createPostalHistory(postalHistory);

        return new ResponseEntity<>(postalHistory.getPostalStatus(), HttpStatus.OK);
    }

    @PostMapping("send_post_from_office")
    @Operation(description = "Внести запись об убытии почтового отправления из почтового отделения.")
    public ResponseEntity<PostalStatus> sendPostalItemFromPostalOffice(UUID identifier, String officeIndex) {

        PostalItem postalItem = postalItemService.getPostalItemByIdentifier(identifier);
        PostalOffice postalOffice = postalOfficeService.getPostalOfficeByOfficeIndex(officeIndex);
        PostalHistory postalHistory = new PostalHistory(PostalStatus.OUT_OF_OFFICE, postalItem, postalOffice);

        postalHistoryService.createPostalHistory(postalHistory);

        return new ResponseEntity<>(postalHistory.getPostalStatus(), HttpStatus.OK);
    }

    @PostMapping("deliver_post_to_recipient")
    @Operation(description = "Внести запись о получении почтового отправления адресатом.")
    public ResponseEntity<PostalStatus> deliverPostalItemToRecipient(UUID identifier){

        PostalItem postalItem = postalItemService.getPostalItemByIdentifier(identifier);
        PostalHistory postalHistory = new PostalHistory(PostalStatus.RECEIVED, postalItem);

        postalHistoryService.createPostalHistory(postalHistory);

        return new ResponseEntity<>(postalHistory.getPostalStatus(), HttpStatus.OK);
    }

    @GetMapping ("postal_item/{identifier}")
    @Operation(description = "Просмотреть статус и полную историю движения почтового отправления.")
    public List<PostalHistory> checkHistory(@RequestBody UUID identifier) {
        return postalHistoryService.getPostalHistoriesByPostalItem_Identifier(identifier);
    }

}
