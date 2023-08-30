package com.mailapp.services;

import com.mailapp.dto.PostalHistoryOfItem;
import com.mailapp.entities.PostalHistoryRecord;

import java.util.List;
import java.util.UUID;

public interface PostalHistoryRecordService {

    PostalHistoryRecord createPostalHistory(PostalHistoryRecord postalHistoryRecord);

    List<PostalHistoryOfItem> getPostalHistory(UUID postalItemId);
}
