package com.bookstore.onlinebookstore.service;

import com.bookstore.onlinebookstore.dto.UserDTO;
import com.bookstore.onlinebookstore.model.UserEntity;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserEntity> findAllUsers();
    Optional<UserEntity> findUserById(Long id);
    UserDTO findUserByEmail(String email);
    UserEntity saveUser(UserEntity user);
    void deleteUser(Long id);
    UserEntity updateUserProfile(Long id, UserDTO userDTO);
}
