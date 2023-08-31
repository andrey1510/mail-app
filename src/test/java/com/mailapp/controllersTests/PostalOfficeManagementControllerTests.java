package com.mailapp.controllersTests;

import com.mailapp.controllers.PostalOfficeManagementController;
import com.mailapp.entities.PostalOffice;
import com.mailapp.services.PostalOfficeServiceImpl;
import com.mailapp.testData.TestData;

import lombok.SneakyThrows;

import org.hamcrest.Matchers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(PostalOfficeManagementController.class)
class PostalOfficeManagementControllerTests extends TestData {

    @MockBean
    PostalOfficeServiceImpl postalOfficeServiceImpl;

    @Autowired
    private MockMvc mockMvc;

    private final PostalOffice postalOffice1 = createOffice1();

    @Test
    @SneakyThrows
    void getPostalOfficeTest() {

        when(postalOfficeServiceImpl.findById(postalOffice1.getOfficeIndex()))
                .thenReturn(Optional.of(postalOffice1));

        this.mockMvc.perform(get("/api/postal_office/{index}", postalOffice1.getOfficeIndex()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.notNullValue()))
                .andExpect(jsonPath("$.officeTitle", Matchers.is(postalOffice1.getOfficeTitle())))
                .andExpect(jsonPath("$.officeAddress", Matchers.is(postalOffice1.getOfficeAddress())))
                .andExpect(jsonPath("$.officeIndex", Matchers.is(postalOffice1.getOfficeIndex())));
    }

    @Test
    void testRegisterPostalOffice() throws Exception {

        when(postalOfficeServiceImpl.createPostalOffice(Mockito.any(PostalOffice.class)))
                .thenReturn(postalOffice1);
        RequestBuilder requestBuilder = post("/api/postal_office/register_post_office")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"officeIndex\":223005,\"officeTitle\":\"Второе почтовое отделение\",\"officeAddress\":\"г. Ярославль, ул. Северная, дом 3\"}")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

}
