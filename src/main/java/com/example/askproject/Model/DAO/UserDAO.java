package com.example.askproject.Model.DAO;

import com.example.askproject.Model.Entity.UserEntity;
import java.util.*;

public interface UserDAO {
    public void registUser(UserEntity userEntity);
    public void deleteUser(String userId);
    public void updateUser(UserEntity userEntity);
    public UserEntity findByUserId(String userId);
    public List<String> findAllUserId();
}
