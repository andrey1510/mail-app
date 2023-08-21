package com.mailapp.repositories;

import com.mailapp.entities.PostalItem;
import com.mailapp.entities.PostalOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostalOfficeRepository extends JpaRepository<PostalOffice, Integer> {

    PostalOffice getPostalOfficeByOfficeIndex(@Param("office_index") String officeIndex);

}
