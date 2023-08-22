package com.mailapp.services;

import com.mailapp.entities.PostalHistory;
import com.mailapp.repositories.PostalHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PostalHistoryServiceImpl implements PostalHistoryService {

    @Autowired
    private PostalHistoryRepository postalHistoryRepository;

    @Override
    @Transactional
    public PostalHistory createPostalHistory(PostalHistory postalHistory) {
        return postalHistoryRepository.save(postalHistory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostalHistory> getPostalHistoriesByPostalItem_Id(UUID postalItemId){
        return postalHistoryRepository.findAllByPostalItemPostalItemId(postalItemId);
    }



}
