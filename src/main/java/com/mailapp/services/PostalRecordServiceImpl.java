package com.mailapp.services;

import com.mailapp.dto.PostalHistoryOfItem;
import com.mailapp.entities.PostalHistoryRecord;
import com.mailapp.repositories.PostalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import static com.mailapp.utilities.HistoryConverter.mapToPostalHistoryOfItemDTO;

@Service
public class PostalRecordServiceImpl implements PostalRecordService {

    @Autowired
    private PostalRecordRepository postalRecordRepository;

    @Override
    @Transactional
    public PostalHistoryRecord createPostalHistory(PostalHistoryRecord postalHistoryRecord) {
        return postalRecordRepository.save(postalHistoryRecord);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostalHistoryRecord> getPostalHistoriesByPostalItemId(UUID postalItemId){
        return postalRecordRepository.findAllByPostalItemPostalItemIdOrderByTimestampDesc(postalItemId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostalHistoryOfItem> getPostalHistory(UUID postalItemId){
        List<Object[]> postalHistory = postalRecordRepository.getPostalHistory(postalItemId);
        return mapToPostalHistoryOfItemDTO(postalHistory);
    }

}
