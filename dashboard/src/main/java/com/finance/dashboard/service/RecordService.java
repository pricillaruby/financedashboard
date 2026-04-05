package com.finance.dashboard.service;

import com.finance.dashboard.entity.RecordEntity;
import com.finance.dashboard.exception.ResourceNotFoundException;
import com.finance.dashboard.mapper.RecordMapper;
import com.finance.dashboard.repository.RecordRepository;
import com.financeswagger.Swaggerex.model.RecordDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.threeten.bp.LocalDate;

import java.util.List;

@Service
@AllArgsConstructor
public class RecordService {

    @Autowired
    private final RecordRepository recordRepository;

    @Autowired
    private final RecordMapper recordMapper;

    public ResponseEntity<String> addRecord(RecordDTO recordDTO) {
        RecordEntity recordEntity = recordMapper.dtoToEntity(recordDTO);
        recordRepository.save(recordEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body("Record created successfully");
    }

    public ResponseEntity<String> deleteRecord(Integer id) {
        if(recordRepository.existsById(id)){
            recordRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Record deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found");
    }

    public ResponseEntity<List<RecordDTO>> getAllRecord(String recordType, String category, LocalDate startDate, LocalDate endDate) {
        List<RecordEntity> recordsList = recordRepository.findAll();

        List<RecordDTO> recordDTO = recordsList.stream()
                .filter(r->recordType == null || r.getRecordType().equalsIgnoreCase(recordType))
                .filter(r->category == null || r.getCategory().equalsIgnoreCase(category))
                .map(recordMapper::entityToDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(recordDTO);
    }

    public ResponseEntity<RecordDTO> getRecord(Integer id) {
        RecordEntity recordEntity = recordRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Record not found"));

        return ResponseEntity.status(HttpStatus.OK).body(recordMapper.entityToDto(recordEntity));
    }

    public ResponseEntity<RecordDTO> updateRecord(Integer id, RecordDTO recordDTO) {
        RecordEntity recordEntity = recordRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Record not found"));

        recordMapper.updateEntity(recordDTO,recordEntity);
        recordRepository.save(recordEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(recordMapper.entityToDto(recordEntity));
    }
}
