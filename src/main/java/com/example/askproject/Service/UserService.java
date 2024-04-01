package com.example.askproject.Service;

import java.util.List; // List import 추가

import com.example.askproject.Model.DTO.UserDTO;

public interface UserService {
    public void deleteUser(String userId)throws Exception;

    public void updateUser(UserDTO userDTO)throws Exception;

    public UserDTO findByUserId(String userId)throws Exception;

    public List<String> findAllUserId()throws Exception;

    public boolean existsByUserId(String userId)throws Exception;

    public String findNicknameByUserId(String userId)throws Exception;
}
