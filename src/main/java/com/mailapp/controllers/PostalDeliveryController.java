package com.mailapp.controllers;

import com.mailapp.dto.PostalHistoryOfItem;
import com.mailapp.dto.PostalItemInfo;
import com.mailapp.entities.PostalHistoryRecord;
import com.mailapp.entities.PostalItem;
import com.mailapp.entities.PostalOffice;
import com.mailapp.enums.PostalStatus;
import com.mailapp.services.PostalItemService;
import com.mailapp.services.PostalHistoryRecordService;
import com.mailapp.services.PostalOfficeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/post_item_delivery/")
@RequiredArgsConstructor
public class PostalDeliveryController {

    private final PostalItemService postalItemService;
    private final PostalHistoryRecordService postalHistoryRecordService;
    private final PostalOfficeService postalOfficeService;

    @PostMapping("register")
    @Operation(description = "Зарегистрировать почтовое отправление.")
    public ResponseEntity<UUID> registerPostalItem(@RequestBody PostalItem postalItem) {

        PostalItem createdItem = postalItemService.createPostalItem(postalItem);
        PostalHistoryRecord postalHistoryRecord = new PostalHistoryRecord(PostalStatus.REGISTERED, createdItem);

        postalHistoryRecordService.createPostalHistory(postalHistoryRecord);

        return new ResponseEntity<>(createdItem.getPostalItemId(), HttpStatus.OK);
    }

    @PostMapping("get_to_postal_office/{postal_item_id}&{office_index}")
    @Operation(description = "Внести запись о прибытии почтового отправления в почтовое отделение")
    public ResponseEntity<PostalStatus> getPostalItemToPostalOffice(@PathVariable("postal_item_id") UUID postalItemId,
                                                                    @PathVariable("office_index") String officeIndex) {

        PostalItem postalItem = postalItemService.findById(postalItemId).orElseThrow();
        PostalOffice postalOffice = postalOfficeService.findById(officeIndex).orElseThrow();
        PostalHistoryRecord postalHistoryRecord = new PostalHistoryRecord(PostalStatus.IN_OFFICE, postalItem, postalOffice);

        postalHistoryRecordService.createPostalHistory(postalHistoryRecord);

        return new ResponseEntity<>(postalHistoryRecord.getPostalStatus(), HttpStatus.OK);
    }

    @PostMapping("send_from_postal_office/{postal_item_id}&{office_index}")
    @Operation(description = "Внести запись об убытии почтового отправления из почтового отделения.")
    public ResponseEntity<PostalStatus> sendPostalItemFromPostalOffice(@PathVariable("postal_item_id") UUID postalItemId,
                                                                       @PathVariable("office_index") String officeIndex) {

        PostalItem postalItem = postalItemService.findById(postalItemId).orElseThrow();
        PostalOffice postalOffice = postalOfficeService.findById(officeIndex).orElseThrow();
        PostalHistoryRecord postalHistoryRecord = new PostalHistoryRecord(PostalStatus.OUT_OF_OFFICE, postalItem, postalOffice);

        postalHistoryRecordService.createPostalHistory(postalHistoryRecord);

        return new ResponseEntity<>(postalHistoryRecord.getPostalStatus(), HttpStatus.OK);
    }

    @PostMapping("deliver_to_recipient/{postal_item_id}")
    @Operation(description = "Внести запись о получении почтового отправления адресатом.")
    public ResponseEntity<PostalStatus> deliverPostalItemToRecipient(@PathVariable("postal_item_id") UUID postalItemId){

        PostalItem postalItem = postalItemService.findById(postalItemId).orElseThrow();
        PostalHistoryRecord postalHistoryRecord = new PostalHistoryRecord(PostalStatus.RECEIVED, postalItem);

        postalHistoryRecordService.createPostalHistory(postalHistoryRecord);

        return new ResponseEntity<>(postalHistoryRecord.getPostalStatus(), HttpStatus.OK);
    }

    @GetMapping ("check_history/{postal_item_id}")
    @Operation(description = "Просмотреть статус и полную историю движения почтового отправления (сортировка от более позднего к более раннему).")
    public PostalItemInfo checkPostalItemHistory(@PathVariable("postal_item_id") UUID postalItemId) {

        PostalItem postalItem = postalItemService.findById(postalItemId).orElseThrow();
        List<PostalHistoryOfItem> postalHistory = postalHistoryRecordService.getPostalHistory(postalItemId);

        return new PostalItemInfo(postalItem, postalHistory);
    }

}
