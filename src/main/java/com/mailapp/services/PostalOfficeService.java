package com.mailapp.services;

import com.mailapp.entities.PostalOffice;

import java.util.Optional;

public interface PostalOfficeService {

    Optional<PostalOffice> findById(String officeIndex);

    PostalOffice createPostalOffice(PostalOffice postalOffice);
}
