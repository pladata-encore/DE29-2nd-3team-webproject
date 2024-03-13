package com.example.askproject.Model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.askproject.Model.DTO.UserDTO;
import com.example.askproject.Model.Entity.UserEntity;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByUserId(String userId);

    boolean existsByUserId(String userId);
    boolean existsByUserEmail(String userEmail);
    boolean existsByUserPassword(String userPassword);


    @Query("SELECT u.userId FROM UserEntity u")
    List<String> findAllUserId();

    void deleteByUserId(String userId);

    @Query(value = "select * from user where name = :name", nativeQuery = true)
    UserDTO getUserDtoByName(@Param("name") String name);
}
