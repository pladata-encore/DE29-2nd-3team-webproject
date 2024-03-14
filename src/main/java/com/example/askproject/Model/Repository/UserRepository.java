package com.example.askproject.Model.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.askproject.Model.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByUserId(String userId);

    boolean existsByUserId(String userId);
    boolean existsByUserEmail(String userEmail);
    boolean existsByUserPassword(String userPassword);


    @Query(value = "SELECT user_id FROM user", nativeQuery = true)
    List<String> findAllUserId();

    void deleteByUserId(String userId);

}
