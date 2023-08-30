package com.mailapp.servicesTests;

import com.mailapp.entities.PostalOffice;
import com.mailapp.repositories.PostalOfficeRepository;
import com.mailapp.services.PostalOfficeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostalOfficeServiceImplTests {

    @InjectMocks
    PostalOfficeServiceImpl postalOfficeServiceImpl;

    @Mock
    PostalOfficeRepository postalOfficeRepository;

    @Test
    void testCreatePostalItem(){
        when(postalOfficeRepository.save(postalOffice1)).thenReturn(postalOffice1);
        assertEquals(postalOffice1, postalOfficeServiceImpl.createPostalOffice(postalOffice1));
    }

    @Test
    void testFindById(){
        when(postalOfficeRepository.findById(postalOffice1.getOfficeIndex())).thenReturn(Optional.of(postalOffice1));
        assertEquals(postalOffice1, postalOfficeServiceImpl.findById(postalOffice1.getOfficeIndex()).orElseThrow());
        verify(postalOfficeRepository, times(1)).findById(postalOffice1.getOfficeIndex());
    }


    ////////////////// Test Data ///////////////

    PostalOffice postalOffice1 = new PostalOffice(
            "223005",
            "Второе почтовое отделение",
            "г. Ярославль, ул. Северная, дом 3"
    );

}
