package com.example.askproject.Service;

import java.util.List; // List import 추가

import com.example.askproject.Model.DTO.UserDTO;

public interface UserService {
    public void deleteUser(String userId);

    public void updateUser(UserDTO userDTO);

    public UserDTO findByUserId(String userId);

    public List<String> findAllUserId();

    public boolean existsByUserId(String userId);
}
