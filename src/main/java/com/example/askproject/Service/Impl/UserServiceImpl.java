package com.example.askproject.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.askproject.Model.DAO.UserDAO;
import com.example.askproject.Model.DTO.UserDTO;
import com.example.askproject.Model.Entity.UserEntity;
import com.example.askproject.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public void deleteUser(String userId) {
        // TODO Auto-generated method stub
        userDAO.deleteUser(userId);
    }

    @Override
    public List<String> findAllUserId() {
        // TODO Auto-generated method stub
        return userDAO.findAllUserId();
    }

    @Override
    public UserDTO findByUserId(String userId) {
        // TODO Auto-generated method stub
        UserEntity entity = userDAO.findByUserId(userId);
        UserDTO dto = new UserDTO();
        dto.setUserId(entity.getUserId());
        dto.setUserEmail(entity.getUserEmail());
        dto.setUserNickname(entity.getUserNickname());
        dto.setUserRole(entity.getUserRole());
        dto.setUserPassword(entity.getUserPassword());
        return dto;
    }


    @Override
    public void updateUser(UserDTO userDTO) {
        // TODO Auto-generated method stub
        UserEntity entity = userDAO.findByUserId(userDTO.getUserId());
        entity.setUserEmail(userDTO.getUserEmail());
        entity.setUserNickname(userDTO.getUserNickname());
        entity.setUserPassword(userDTO.getUserPassword());
        userDAO.updateUser(entity);
    }

    @Override
    public boolean existsByUserId(String userId) {
        // TODO Auto-generated method stub
        return userDAO.existsByUserId(userId);
    }

}
