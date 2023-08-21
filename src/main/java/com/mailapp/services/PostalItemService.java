package com.mailapp.services;


import com.mailapp.entities.PostalItem;
import java.util.UUID;

public interface PostalItemService {

    PostalItem createPostalItem(PostalItem postalItem);

    PostalItem getPostalItemByIdentifier(UUID identifier);

}
