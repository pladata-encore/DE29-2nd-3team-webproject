package com.example.askproject.Model.DAO.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.askproject.Model.DAO.UserDAO;
import com.example.askproject.Model.Entity.UserEntity;
import com.example.askproject.Model.Repository.UserRepository;

@Service
public class UserDAOImpl implements UserDAO{
    @Autowired
    private UserRepository userRepository;

    @Override
    public void deleteUser(String userId) {
        // TODO Auto-generated method stub
        userRepository.deleteByUserId(userId);
    }

    @Override
    public void registUser(UserEntity userEntity) {
        // TODO Auto-generated method stub
        userRepository.save(userEntity);
    }

    @Override
    public List<String> findAllUserId() {
        // TODO Auto-generated method stub
        return userRepository.findAllUserId();
    }

    @Override
    public UserEntity findByUserId(String userId) {
        // TODO Auto-generated method stub
        return userRepository.findByUserId(userId);
    }

    @Override
    public void updateUser(UserEntity userEntity) {
        // TODO Auto-generated method stub
        userRepository.save(userEntity);
    }

    @Override
    public boolean existsByUserId(String userId) {
        // TODO Auto-generated method stub
        return userRepository.existsByUserId(userId);
    }


    
}
