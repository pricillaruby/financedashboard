package com.finance.dashboard.service;

import com.finance.dashboard.entity.UserEntity;
import com.finance.dashboard.exception.ResourceNotFoundException;
import com.finance.dashboard.mapper.UserMapper;
import com.finance.dashboard.repository.UserRepository;
import com.financeswagger.Swaggerex.model.IdStatusBody;
import com.financeswagger.Swaggerex.model.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.threeten.bp.LocalDate;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserMapper userMapper;

    public ResponseEntity<String> addUser(UserDTO userDTO) {
        UserEntity userEntity = userMapper.dtoToEntity(userDTO);
        userRepository.save(userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Added Successfully");
    }

    public ResponseEntity<String> deleteUser(Integer id) {
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User Deleted Successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
    }

    public ResponseEntity<List<UserDTO>> getAllUser() {
        List<UserEntity> userEntity = userRepository.findAll();

        List<UserDTO> userDTO= userEntity.stream()
                .map(userMapper::entityToDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    public ResponseEntity<UserDTO> getUser(Integer id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User id not found"));

        return ResponseEntity.status(HttpStatus.OK).body(userMapper.entityToDto(userEntity));
    }

    public ResponseEntity<UserDTO> updateStatus(Integer id, IdStatusBody statusBody) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User id not found"));

        userMapper.updateUserStatus(userEntity,statusBody);
        userRepository.save(userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.entityToDto(userEntity));
    }

    public ResponseEntity<UserDTO> updateUser(Integer id, UserDTO userDTO) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User id not found"));

        userMapper.updateEntity(userDTO,userEntity);
        userRepository.save(userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.entityToDto(userEntity));
    }
}
