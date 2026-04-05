package com.finance.dashboard.mapper;

import com.finance.dashboard.entity.RecordEntity;
import com.financeswagger.Swaggerex.model.RecordDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DashboardMapper {
    @Autowired
    private ModelMapper modelMapper;

    public RecordDTO convertToDTO(RecordEntity record) {
        return modelMapper.map(record, RecordDTO.class);
    }
}
