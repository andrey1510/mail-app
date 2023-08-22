package com.mailapp.controllers;

import com.mailapp.entities.PostalHistory;
import com.mailapp.entities.PostalItem;
import com.mailapp.entities.PostalOffice;
import com.mailapp.enums.PostalStatus;
import com.mailapp.services.PostalItemService;
import com.mailapp.services.PostalHistoryService;
import com.mailapp.services.PostalOfficeService;
import io.swagger.v3.oas.annotations.Operation;
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

        return new ResponseEntity<>(postalItem.getPostalItemId(), HttpStatus.OK);
    }





    @PostMapping("get_post_to_office/{postal_item_id}&{office_index}")
    @Operation(description = "Внести запись о прибытии почтового отправления в почтовое отделение")
    public ResponseEntity<PostalStatus> getPostalItemToPostalOffice(@PathVariable("postal_item_id") UUID postalItemId,
                                                                    @PathVariable("office_index") String officeIndex) {

        PostalItem postalItem = postalItemService.findById(postalItemId).orElseThrow();
        PostalOffice postalOffice = postalOfficeService.findById(officeIndex).orElseThrow();
        PostalHistory postalHistory = new PostalHistory(PostalStatus.IN_OFFICE, postalItem, postalOffice);

        postalHistoryService.createPostalHistory(postalHistory);

        return new ResponseEntity<>(postalHistory.getPostalStatus(), HttpStatus.OK);
    }

    @PostMapping("send_post_from_office/{postal_item_id}&{office_index}")
    @Operation(description = "Внести запись об убытии почтового отправления из почтового отделения.")
    public ResponseEntity<PostalStatus> sendPostalItemFromPostalOffice(@PathVariable("postal_item_id") UUID postalItemId,
                                                                       @PathVariable("office_index") String officeIndex) {

        PostalItem postalItem = postalItemService.findById(postalItemId).orElseThrow();
        PostalOffice postalOffice = postalOfficeService.findById(officeIndex).orElseThrow();
        PostalHistory postalHistory = new PostalHistory(PostalStatus.OUT_OF_OFFICE, postalItem, postalOffice);

        postalHistoryService.createPostalHistory(postalHistory);

        return new ResponseEntity<>(postalHistory.getPostalStatus(), HttpStatus.OK);
    }

    @PostMapping("deliver_post_to_recipient/{postal_item_id}&{office_index}")
    @Operation(description = "Внести запись о получении почтового отправления адресатом.")
    public ResponseEntity<PostalStatus> deliverPostalItemToRecipient(@PathVariable("postal_item_id") UUID postalItemId){

        PostalItem postalItem = postalItemService.findById(postalItemId).orElseThrow();
        PostalHistory postalHistory = new PostalHistory(PostalStatus.RECEIVED, postalItem);

        postalHistoryService.createPostalHistory(postalHistory);

        return new ResponseEntity<>(postalHistory.getPostalStatus(), HttpStatus.OK);
    }

    @GetMapping ("postal_item_history/{postal_item_id}")
    @Operation(description = "Просмотреть статус и полную историю движения почтового отправления.")
    public List<PostalHistory> checkHistory(@PathVariable("postal_item_id") UUID postalItemId) {
        return postalHistoryService.getPostalHistoriesByPostalItem_Id(postalItemId);
    }

}
