package com.finance.dashboard.controller;

import com.finance.dashboard.service.UserService;
import com.financeswagger.Swaggerex.api.UserApi;
import com.financeswagger.Swaggerex.model.IdStatusBody;
import com.financeswagger.Swaggerex.model.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/finance")
@AllArgsConstructor
public class UserController implements UserApi {

    @Autowired
    private final UserService userService;

    @Override
    public ResponseEntity<String> addUser(UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @Override
    public ResponseEntity<String> deleteUser(Integer id) {
        return userService.deleteUser(id);
    }

    @Override
    public ResponseEntity<List<UserDTO>> getAllUser() {
        return userService.getAllUser();
    }

    @Override
    public ResponseEntity<UserDTO> getUser(Integer id) {
        return userService.getUser(id);
    }

    @Override
    public ResponseEntity<UserDTO> updateStatus(Integer id, IdStatusBody statusBody) {
        return userService.updateStatus(id,statusBody);
    }

    @Override
    public ResponseEntity<UserDTO> updateUser(Integer id, UserDTO userDTO) {
        return userService.updateUser(id,userDTO);
    }
}
