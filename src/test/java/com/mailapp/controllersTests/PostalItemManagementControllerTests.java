package com.mailapp.controllersTests;


import com.mailapp.controllers.PostalItemManagementController;
import com.mailapp.entities.PostalItem;
import com.mailapp.services.PostalItemServiceImpl;
import com.mailapp.services.PostalOfficeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

import static com.mailapp.enums.PostalType.LETTER;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PostalItemManagementController.class)
class PostalItemManagementControllerTests {

    @MockBean
    PostalItemServiceImpl postalItemServiceImpl;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testFindById() throws Exception {

        when(postalItemServiceImpl.findById(itemUUID1))
                .thenReturn(Optional.of(postalItem1));

        this.mockMvc.perform(get("/api/postal_item/{itemUUID1}", itemUUID1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.notNullValue()))
                .andExpect(jsonPath("$.postalItemId", Matchers.is(postalItem1.getPostalItemId().toString())))
                .andExpect(jsonPath("$.postalType", Matchers.is(postalItem1.getPostalType().toString())))
                .andExpect(jsonPath("$.recipientIndex", Matchers.is(postalItem1.getRecipientIndex())))
                .andExpect(jsonPath("$.recipientAddress", Matchers.is(postalItem1.getRecipientAddress())))
                .andExpect(jsonPath("$.recipientName", Matchers.is(postalItem1.getRecipientName())));
    }

    ////////////////// Test Data ///////////////

    UUID itemUUID1 = UUID.fromString("4165272a-e9e9-40f6-b1c5-29ca31f0d1e2");
    PostalItem postalItem1 = new PostalItem(
            itemUUID1,
            LETTER,
            "101001",
            "г. Москва, ул. Первая, дом 1",
            "Петров Петр Петрович"
    );

}
