package com.mailapp.controllersTests;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.mailapp.controllers.PostalDeliveryController;
import com.mailapp.dto.PostalHistoryOfItem;
import com.mailapp.dto.PostalItemInfo;

import com.mailapp.entities.PostalHistoryRecord;
import com.mailapp.entities.PostalItem;
import com.mailapp.entities.PostalOffice;
import com.mailapp.enums.PostalStatus;
import com.mailapp.services.PostalHistoryRecordServiceImpl;
import com.mailapp.services.PostalItemServiceImpl;
import com.mailapp.services.PostalOfficeServiceImpl;
import com.mailapp.testData.TestData;

import lombok.SneakyThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PostalDeliveryController.class)
class PostalDeliveryControllerTests extends TestData {

    @MockBean
    PostalHistoryRecordServiceImpl postalHistoryRecordServiceImpl;

    @MockBean
    PostalOfficeServiceImpl postalOfficeServiceImpl;

    @MockBean
    PostalItemServiceImpl postalItemServiceImpl;

    @Autowired
    private MockMvc mockMvc;

    private final PostalItem postalItem1 = createItem1();
    private final PostalOffice postalOffice1 = createOffice1();
    private final String postalItemJson1 = createItemJsonString1();
    private final List<PostalHistoryOfItem> postalHistory = createItemHistoryList();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @SneakyThrows
    void registerPostalItemTest(){

        when(postalItemServiceImpl.createPostalItem(any(PostalItem.class)))
                .thenReturn(postalItem1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/post_item_delivery/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postalItemJson1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("\"" + postalItem1.getPostalItemId().toString() + "\""));

        verify(postalItemServiceImpl).createPostalItem(any(PostalItem.class));
        verify(postalHistoryRecordServiceImpl).createPostalHistory(any(PostalHistoryRecord.class));

    }

    @Test
    @SneakyThrows
    void getPostalItemToPostalOfficeTest(){

        when(postalItemServiceImpl.findById(any(UUID.class)))
                .thenReturn(Optional.of(postalItem1));
        when(postalOfficeServiceImpl.findById(anyString()))
                .thenReturn(Optional.of(postalOffice1));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/post_item_delivery/get_to_postal_office/{postal_item_id}&{office_index}",
                                postalItem1.getPostalItemId().toString(), postalOffice1.getOfficeIndex())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("\"" + PostalStatus.IN_OFFICE + "\""));

        verify(postalItemServiceImpl).findById(any(UUID.class));
        verify(postalHistoryRecordServiceImpl).createPostalHistory(any(PostalHistoryRecord.class));
    }

    @Test
    @SneakyThrows
    void sendPostalItemFromPostalOfficeTest(){

        when(postalItemServiceImpl.findById(any(UUID.class)))
                .thenReturn(Optional.of(postalItem1));
        when(postalOfficeServiceImpl.findById(anyString()))
                .thenReturn(Optional.of(postalOffice1));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/post_item_delivery/send_from_postal_office/{postal_item_id}&{office_index}",
                                postalItem1.getPostalItemId().toString(), postalOffice1.getOfficeIndex())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("\"" + PostalStatus.OUT_OF_OFFICE + "\""));

        verify(postalItemServiceImpl).findById(any(UUID.class));
        verify(postalHistoryRecordServiceImpl).createPostalHistory(any(PostalHistoryRecord.class));
    }

    @Test
    @SneakyThrows
    void deliverPostalItemToRecipientTest(){

        when(postalItemServiceImpl.findById(any(UUID.class)))
                .thenReturn(Optional.of(postalItem1));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/post_item_delivery/deliver_to_recipient/{postal_item_id}",
                                postalItem1.getPostalItemId().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("\"" + PostalStatus.RECEIVED + "\""));

        verify(postalItemServiceImpl).findById(any(UUID.class));
        verify(postalHistoryRecordServiceImpl).createPostalHistory(any(PostalHistoryRecord.class));
    }

    @Test
    @SneakyThrows
    void checkPostalItemHistoryTest(){

        PostalItemInfo postalItemInfo = new PostalItemInfo(postalItem1, postalHistory);

        when(postalItemServiceImpl.findById(any(UUID.class)))
                .thenReturn(Optional.of(postalItem1));
        when(postalHistoryRecordServiceImpl.getPostalHistory(any(UUID.class)))
                .thenReturn(postalHistory);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/post_item_delivery/check_history/{postal_item_id}", postalItem1.getPostalItemId().toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$..postalItem").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$..postalHistoryOfItem").exists())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(postalItemInfo)));

        verify(postalItemServiceImpl).findById(any(UUID.class));
        verify(postalHistoryRecordServiceImpl).getPostalHistory(any(UUID.class));

    }

}
