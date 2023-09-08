package com.mailapp.dtoTests;

import com.mailapp.dto.PostalHistoryOfItem;
import com.mailapp.dto.PostalItemInfo;
import com.mailapp.entities.PostalItem;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PostalItemInfoTests {

    @Test
    public void testCreation() {
        PostalItem postalItem = new PostalItem();
        List<PostalHistoryOfItem> postalHistoryOfItem = new ArrayList<>();
        PostalItemInfo postalItemInfo = new PostalItemInfo(postalItem, postalHistoryOfItem);

        assertNotNull(postalItemInfo);
        assertEquals(postalItem, postalItemInfo.getPostalItem());
        assertEquals(postalHistoryOfItem, postalItemInfo.getPostalHistoryOfItem());
    }

}
