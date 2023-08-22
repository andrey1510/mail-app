package com.mailapp.controllers;

import com.mailapp.entities.PostalOffice;
import com.mailapp.services.PostalOfficeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/postal_office/")
@RequiredArgsConstructor
public class PostalOfficeManagementController {

    private final PostalOfficeService postalOfficeService;

    @PostMapping("register_post_office")
    @Operation(description = "Внести в базу почтовое отделение.")
    public ResponseEntity<PostalOffice> registerPostalOffice(@RequestBody PostalOffice postalOffice) {

        postalOfficeService.createPostalOffice(postalOffice);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{office_index}")
    @Operation(description = "Найти почтовое отделение по индексу.")
    public PostalOffice getPostalOffice(@PathVariable("office_index") String officeIndex) {
        return postalOfficeService.findById(officeIndex).orElseThrow();
    }

}
