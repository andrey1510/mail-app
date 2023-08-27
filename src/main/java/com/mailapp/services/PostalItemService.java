package com.mailapp.services;

import com.mailapp.entities.PostalItem;
import java.util.Optional;
import java.util.UUID;

public interface PostalItemService {

    PostalItem createPostalItem(PostalItem postalItem);

    Optional<PostalItem> findById(UUID postalItemId);
}
