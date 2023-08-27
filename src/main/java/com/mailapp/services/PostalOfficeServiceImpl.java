package com.mailapp.services;

import com.mailapp.entities.PostalOffice;
import com.mailapp.repositories.PostalOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class PostalOfficeServiceImpl implements PostalOfficeService {

    @Autowired
    private PostalOfficeRepository postalOfficeRepository;

    @Override
    @Transactional
    public PostalOffice createPostalOffice(PostalOffice postalOffice) {
        return postalOfficeRepository.save(postalOffice);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PostalOffice> findById(String officeIndex) {
        return postalOfficeRepository.findById(officeIndex);
    }

}
