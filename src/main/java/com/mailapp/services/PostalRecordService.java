package com.mailapp.services;

import com.mailapp.dto.PostalHistoryOfItem;
import com.mailapp.entities.PostalHistoryRecord;

import java.util.List;
import java.util.UUID;

public interface PostalRecordService {

    PostalHistoryRecord createPostalHistory(PostalHistoryRecord postalHistoryRecord);

    List<PostalHistoryRecord> getPostalHistoriesByPostalItemId(UUID id);

    List<PostalHistoryOfItem> getPostalHistory(UUID postalItemId);
}
