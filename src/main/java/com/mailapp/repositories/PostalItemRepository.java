package com.mailapp.repositories;

import com.mailapp.entities.PostalItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostalItemRepository extends JpaRepository<PostalItem, UUID> {

   // Optional<PostalItem> findPostalItemByPostalItemId
  //  PostalItem findByIdentifier(@Param("identifier") UUID identifier);
  //  PostalItem getPostalItemByIdentifier(@Param("identifier") UUID identifier);

}
