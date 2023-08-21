package com.mailapp.services;

import com.mailapp.entities.PostalHistory;
import java.util.List;
import java.util.UUID;

public interface PostalHistoryService {

    PostalHistory createPostalHistory(PostalHistory postalHistory);

    List<PostalHistory> getPostalHistoriesByPostalItem_Identifier(UUID identifier);
}
