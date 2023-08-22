package com.mailapp.repositories;

import com.mailapp.entities.PostalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface PostalHistoryRepository extends JpaRepository<PostalHistory, UUID> {

    List<PostalHistory> findAllByPostalItemPostalItemId(@Param("postal_item_id") UUID postalItemId);


}
