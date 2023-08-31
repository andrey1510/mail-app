package com.mailapp.repositories;

import com.mailapp.entities.PostalOffice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostalOfficeRepository extends JpaRepository<PostalOffice, String> {

}
