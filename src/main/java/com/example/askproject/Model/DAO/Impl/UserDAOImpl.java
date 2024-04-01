package com.example.askproject.Model.DAO.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.askproject.Model.DAO.UserDAO;
import com.example.askproject.Model.Entity.UserEntity;
import com.example.askproject.Model.Repository.UserRepository;

@Service
public class UserDAOImpl implements UserDAO{
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void deleteUser(String userId) throws Exception{
        // TODO Auto-generated method stub
        userRepository.deleteByUserId(userId);
    }

    @Override
    public void registUser(UserEntity userEntity) throws Exception{
        // TODO Auto-generated method stub
        userRepository.save(userEntity);
    }

    @Override
    public List<String> findAllUserId() throws Exception{
        // TODO Auto-generated method stub
        return userRepository.findAllUserId();
    }

    @Override
    public UserEntity findByUserId(String userId) throws Exception{
        // TODO Auto-generated method stub
        return userRepository.findByUserId(userId);
    }

    @Override
    public void updateUser(UserEntity userEntity) throws Exception{
        // TODO Auto-generated method stub
        userRepository.save(userEntity);
    }

    @Override
    public boolean existsByUserId(String userId) throws Exception{
        // TODO Auto-generated method stub
        return userRepository.existsByUserId(userId);
    }

    


    
}
