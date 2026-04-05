package com.finance.dashboard.controller;

import com.finance.dashboard.service.RecordService;
import com.financeswagger.Swaggerex.api.RecordApi;
import com.financeswagger.Swaggerex.model.RecordDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.threeten.bp.LocalDate;

import java.util.List;

@RestController
@RequestMapping("/finance")
@AllArgsConstructor
public class RecordController implements RecordApi {

    @Autowired
    private final RecordService recordService;

    @Override
    public ResponseEntity<String> addRecord(RecordDTO recordDTO) {
        return recordService.addRecord(recordDTO);
    }

    @Override
    public ResponseEntity<String> deleteRecord(Integer id) {
        return recordService.deleteRecord(id);
    }

    @Override
    public ResponseEntity<List<RecordDTO>> getAllRecord(String recordType, String category, LocalDate startDate, LocalDate endDate) {
        return recordService.getAllRecord(recordType,category,startDate,endDate);
    }

    @Override
    public ResponseEntity<RecordDTO> getRecord(Integer id) {
        return recordService.getRecord(id);
    }

    @Override
    public ResponseEntity<RecordDTO> updateRecord(Integer id, RecordDTO recordDTO) {
        return recordService.updateRecord(id,recordDTO);
    }
}
