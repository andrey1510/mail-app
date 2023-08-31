package com.mailapp.servicesTests;

import com.mailapp.dto.PostalHistoryOfItem;
import com.mailapp.entities.PostalHistoryRecord;
import com.mailapp.entities.PostalItem;
import com.mailapp.repositories.PostalHistoryRecordRepository;
import com.mailapp.services.PostalHistoryRecordServiceImpl;
import com.mailapp.testData.TestData;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PublicHistoryRecordServiceImplTests extends TestData {

    @InjectMocks
    PostalHistoryRecordServiceImpl postalHistoryRecordServiceImpl;

    @Mock
    PostalHistoryRecordRepository postalHistoryRecordRepository;

    private final PostalHistoryRecord postalHistoryRecord1 = createHistoryRecord1();
    private final PostalHistoryRecord postalHistoryRecord2 = createHistoryRecord2();
    private final PostalItem postalItem1 = createItem1();
    private final List<Object[]> objectsList = createItemObjects();
    private final List<PostalHistoryOfItem> itemList = createItemHistoryList();

    @Test
    void createPostalHistoryTest() {

        when(postalHistoryRecordRepository.save(postalHistoryRecord1))
                .thenReturn(postalHistoryRecord1);
        assertEquals(postalHistoryRecord1, postalHistoryRecordServiceImpl.createPostalHistory(postalHistoryRecord1));
        verify(postalHistoryRecordRepository, times(1)).save(postalHistoryRecord1);

        when(postalHistoryRecordRepository.save(postalHistoryRecord2))
                .thenReturn(postalHistoryRecord2);
        assertEquals(postalHistoryRecord2, postalHistoryRecordServiceImpl.createPostalHistory(postalHistoryRecord2));
        verify(postalHistoryRecordRepository, times(1)).save(postalHistoryRecord2);
    }

    @Test
    void getPostalHistoryTest(){
        when(postalHistoryRecordRepository.getPostalHistory(postalItem1.getPostalItemId())).thenReturn(objectsList);
        assertEquals(itemList, postalHistoryRecordServiceImpl.getPostalHistory(postalItem1.getPostalItemId()));
        verify(postalHistoryRecordRepository, times(1)).getPostalHistory(postalItem1.getPostalItemId());
    }

}