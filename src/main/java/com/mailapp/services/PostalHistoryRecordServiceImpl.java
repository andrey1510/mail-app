package com.mailapp.services;

import com.mailapp.dto.PostalHistoryOfItem;
import com.mailapp.entities.PostalHistoryRecord;
import com.mailapp.repositories.PostalHistoryRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import static com.mailapp.utilities.HistoryConverter.mapToPostalHistoryOfItemDTO;

@Service
public class PostalHistoryRecordServiceImpl implements PostalHistoryRecordService {

    @Autowired
    private PostalHistoryRecordRepository postalHistoryRecordRepository;

    @Override
    @Transactional
    public PostalHistoryRecord createPostalHistory(PostalHistoryRecord postalHistoryRecord) {
        return postalHistoryRecordRepository.save(postalHistoryRecord);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostalHistoryOfItem> getPostalHistory(UUID postalItemId){
        List<Object[]> postalHistory = postalHistoryRecordRepository.getPostalHistory(postalItemId);
        return mapToPostalHistoryOfItemDTO(postalHistory);
    }

}
