package com.example.askproject.Model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import com.example.askproject.Model.Entity.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, String>{
    public UserEntity findByUserId(String userId);
    @Query(value = "Select user_Id from user", nativeQuery = true)
    public List<String> findAllUserId();
    public void deleteByUserId(String userId);
}
