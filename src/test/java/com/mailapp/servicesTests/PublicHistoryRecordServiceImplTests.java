package com.mailapp.servicesTests;

import com.mailapp.dto.PostalHistoryOfItem;
import com.mailapp.entities.PostalHistoryRecord;
import com.mailapp.entities.PostalItem;
import com.mailapp.entities.PostalOffice;
import com.mailapp.enums.PostalType;
import com.mailapp.repositories.PostalHistoryRecordRepository;
import com.mailapp.services.PostalHistoryRecordServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.Timestamp;
import java.util.*;
import static com.mailapp.enums.PostalStatus.IN_OFFICE;
import static com.mailapp.enums.PostalStatus.REGISTERED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PublicHistoryRecordServiceImplTests {

    @InjectMocks
    PostalHistoryRecordServiceImpl postalHistoryRecordServiceImpl;

    @Mock
    PostalHistoryRecordRepository postalHistoryRecordRepository;

    @Test
    void testCreatePostalHistory() {

        when(postalHistoryRecordRepository.save(postalHistoryRecord1)).thenReturn(postalHistoryRecord1);
        assertEquals(postalHistoryRecord1, postalHistoryRecordServiceImpl.createPostalHistory(postalHistoryRecord1));
        verify(postalHistoryRecordRepository, times(1)).save(postalHistoryRecord1);

        when(postalHistoryRecordRepository.save(postalHistoryRecord2)).thenReturn(postalHistoryRecord2);
        assertEquals(postalHistoryRecord2, postalHistoryRecordServiceImpl.createPostalHistory(postalHistoryRecord2));
        verify(postalHistoryRecordRepository, times(1)).save(postalHistoryRecord2);
    }

    @Test
    void testGetPostalHistory(){
        postalHistoryOfItem1.setHistoryRecordId(postalHistoryRecord1.getHistoryRecordId());
        postalHistoryOfItem1.setTimestamp(postalHistoryRecord1.getTimestamp());
        postalHistoryOfItem1.setPostalStatus(REGISTERED);
        postalHistoryOfItem1.setPostalOffice(postalOfficeNull);
        postalHistoryOfItem2.setHistoryRecordId(postalHistoryRecord2.getHistoryRecordId());
        postalHistoryOfItem2.setTimestamp(postalHistoryRecord2.getTimestamp());
        postalHistoryOfItem2.setPostalStatus(IN_OFFICE);
        postalHistoryOfItem2.setPostalOffice(postalOffice);
        itemList.add(postalHistoryOfItem1);
        itemList.add(postalHistoryOfItem2);
        itemObjects.add(new Object[] {
                postalHistoryRecord1.getHistoryRecordId(),
                postalHistoryRecord1.getTimestamp(),
                postalHistoryRecord1.getPostalStatus(),
                postalOfficeNull.getOfficeTitle(),
                postalOfficeNull.getOfficeIndex(),
                postalOfficeNull.getOfficeAddress()
        });
        itemObjects.add(new Object[] {
                postalHistoryRecord2.getHistoryRecordId(),
                postalHistoryRecord2.getTimestamp(),
                postalHistoryRecord2.getPostalStatus(),
                postalOffice.getOfficeTitle(),
                postalOffice.getOfficeIndex(),
                postalOffice.getOfficeAddress()
        });

        when(postalHistoryRecordRepository.getPostalHistory(postalItem.getPostalItemId())).thenReturn(itemObjects);
        assertEquals(itemList, postalHistoryRecordServiceImpl.getPostalHistory(postalItem.getPostalItemId()));
        verify(postalHistoryRecordRepository, times(1)).getPostalHistory(postalItem.getPostalItemId());
        
    }


    ////////////////// Test Data ///////////////

    PostalItem postalItem = new PostalItem(
            UUID.fromString("539d5184-7a5e-4e2f-bc80-3a81d1f45364"),
            PostalType.PARCEL,
            "202002",
            "г. Москва, ул. Вторая, дом 2",
            "Николаев Николай Николаевич"
    );

    PostalOffice postalOffice = new PostalOffice(
            "303003",
            "Третье почтовое отделение",
            "г. Смоленск, ул. Южная, д. 1"
    );

    PostalOffice postalOfficeNull = new PostalOffice(
            null,
            null,
            null
    );

    PostalHistoryRecord postalHistoryRecord1 = new PostalHistoryRecord(
            UUID.fromString("a9c73dd6-78bb-48ae-beae-ff4c1136793e"),
            REGISTERED,
            postalItem,
            postalOfficeNull,
            Timestamp.valueOf("2023-08-26 19:19:38.11161")
    );

    PostalHistoryRecord postalHistoryRecord2 = new PostalHistoryRecord(
            UUID.fromString("d1394e33-c225-49e5-a8f0-d2d8052b7af8"),
            IN_OFFICE,
            postalItem,
            postalOffice,
            Timestamp.valueOf("2023-08-27 12:29:38.22861")
    );

    PostalHistoryOfItem postalHistoryOfItem1 = new PostalHistoryOfItem();
    PostalHistoryOfItem postalHistoryOfItem2 = new PostalHistoryOfItem();
    List<Object[]> itemObjects = new ArrayList<>();
    List<PostalHistoryOfItem> itemList = new ArrayList<>();

}