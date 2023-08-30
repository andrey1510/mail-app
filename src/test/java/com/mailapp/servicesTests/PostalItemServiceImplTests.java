package com.mailapp.servicesTests;

import com.mailapp.entities.PostalItem;
import com.mailapp.repositories.PostalItemRepository;
import com.mailapp.services.PostalItemServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.UUID;
import static com.mailapp.enums.PostalType.LETTER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostalItemServiceImplTests {

    @InjectMocks
    PostalItemServiceImpl postalItemServiceImpl;

    @Mock
    PostalItemRepository postalItemRepository;

    @Test
    void testCreatePostalItem(){
        when(postalItemRepository.save(postalItem1)).thenReturn(postalItem1);
        assertEquals(postalItem1, postalItemServiceImpl.createPostalItem(postalItem1));
    }

    @Test
    void testFindById(){
        when(postalItemRepository.findById(itemUUID1)).thenReturn(Optional.of(postalItem1));
        assertEquals(postalItem1, postalItemServiceImpl.findById(itemUUID1).orElseThrow());
        verify(postalItemRepository, times(1)).findById(itemUUID1);
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
