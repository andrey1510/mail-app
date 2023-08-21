package com.mailapp.repositories;

import com.mailapp.entities.PostalItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostalItemRepository extends JpaRepository<PostalItem, Integer> {

    PostalItem getPostalItemByIdentifier(@Param("identifier") UUID identifier);

}
