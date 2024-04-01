package com.example.askproject.Model.DAO;

import com.example.askproject.Model.Entity.UserEntity;
import java.util.*;

public interface UserDAO {
    public void registUser(UserEntity userEntity)throws Exception;
    public void deleteUser(String userId)throws Exception;
    public void updateUser(UserEntity userEntity)throws Exception;
    public UserEntity findByUserId(String userId)throws Exception;
    public List<String> findAllUserId()throws Exception;
    public boolean existsByUserId(String userId)throws Exception;
}
