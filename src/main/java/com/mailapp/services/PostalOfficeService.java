package com.mailapp.services;

import com.mailapp.entities.PostalOffice;
public interface PostalOfficeService {

    PostalOffice getPostalOfficeByOfficeIndex(String officeIndex);

    PostalOffice createPostalOffice(PostalOffice postalOffice);
}
