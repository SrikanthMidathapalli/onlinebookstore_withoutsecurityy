package com.bookstore.onlinebookstore.controller;

import com.bookstore.onlinebookstore.dto.UserDTO;
import com.bookstore.onlinebookstore.model.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserControllerInterface {
    List<UserEntity> getAllUsers();
    ResponseEntity<UserEntity> getUserById(@PathVariable Long id);
    ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email);
    UserEntity createUser(@RequestBody UserEntity user);
    ResponseEntity<UserEntity> updateUserProfile(@PathVariable Long id, @RequestBody UserDTO userDTO);
    ResponseEntity<Void> deleteUser(@PathVariable Long id);
}
