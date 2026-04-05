package com.finance.dashboard.mapper;

import com.finance.dashboard.entity.RecordEntity;
import com.financeswagger.Swaggerex.model.RecordDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RecordMapper {
    private final ModelMapper modelMapper;

    public RecordDTO entityToDto(RecordEntity recordEntity){
        return modelMapper.map(recordEntity, RecordDTO.class);
    }

    public RecordEntity dtoToEntity(RecordDTO recordDTO){
        return modelMapper.map(recordDTO, RecordEntity.class);
    }

    public RecordEntity updateEntity(RecordDTO recordDTO , RecordEntity recordEntity){
        modelMapper.typeMap(RecordDTO.class, RecordEntity.class)
                .addMappings(mapper->mapper.skip(RecordEntity::setId));
        modelMapper.map(recordDTO,recordEntity);
        return recordEntity;
    }
}
