package com.mailapp.entitiesTests;

import com.mailapp.entities.PostalHistoryRecord;
import com.mailapp.entities.PostalItem;
import com.mailapp.entities.PostalOffice;
import com.mailapp.enums.PostalStatus;
import com.mailapp.enums.PostalType;
import com.mailapp.testData.TestData;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class PostalHistoryRecordTests extends TestData {

    @Test
    public void testCreation() {
        PostalStatus postalStatus = PostalStatus.IN_OFFICE;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        PostalItem postalItem = createItem1();
        PostalOffice postalOffice = createOffice1();

        PostalHistoryRecord postalHistoryRecord = new PostalHistoryRecord(postalStatus, postalItem, postalOffice);
        postalHistoryRecord.setTimestamp(timestamp);

        assertEquals(timestamp, postalHistoryRecord.getTimestamp());
        assertEquals(postalStatus, postalHistoryRecord.getPostalStatus());
        assertEquals(postalItem, postalHistoryRecord.getPostalItem());
        assertEquals(postalOffice, postalHistoryRecord.getPostalOffice());
    }

    @Test
    public void testSetters() {
        PostalStatus postalStatus = PostalStatus.IN_OFFICE;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        PostalItem postalItem = createItem1();
        PostalOffice postalOffice = createOffice1();

        PostalHistoryRecord postalHistoryRecord = new PostalHistoryRecord(postalStatus, postalItem, postalOffice);
        postalHistoryRecord.setTimestamp(timestamp);
        postalHistoryRecord.setPostalItem(postalItem);
        postalHistoryRecord.setPostalOffice(postalOffice);
        postalHistoryRecord.setPostalStatus(postalStatus);

        assertEquals(timestamp, postalHistoryRecord.getTimestamp());
        assertEquals(postalStatus, postalHistoryRecord.getPostalStatus());
        assertEquals(postalItem, postalHistoryRecord.getPostalItem());
        assertEquals(postalOffice, postalHistoryRecord.getPostalOffice());

    }

}
