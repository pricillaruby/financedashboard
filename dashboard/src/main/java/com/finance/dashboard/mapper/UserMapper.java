package com.finance.dashboard.mapper;

import com.finance.dashboard.entity.UserEntity;
import com.financeswagger.Swaggerex.model.IdStatusBody;
import com.financeswagger.Swaggerex.model.UserDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserDTO entityToDto(UserEntity userEntity){
        return modelMapper.map(userEntity, UserDTO.class);
    }

    public UserEntity dtoToEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, UserEntity.class);
    }

    public UserEntity updateEntity(UserDTO userDTO , UserEntity userEntity){
        modelMapper.typeMap(UserDTO.class, UserEntity.class)
                .addMappings(mapper->mapper.skip(UserEntity::setId));
        modelMapper.map(userDTO,userEntity);
        return userEntity;
    }

    public void updateUserStatus(UserEntity userEntity, IdStatusBody statusBody){
        userEntity.setStatus(statusBody.getStatus());
    }
}
