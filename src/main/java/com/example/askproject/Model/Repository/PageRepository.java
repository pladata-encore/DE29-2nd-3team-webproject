package com.example.askproject.Model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import com.example.askproject.Model.Entity.PageEntity;

public interface PageRepository extends JpaRepository<PageEntity, String>{
    @Query(value = "Select * from page", nativeQuery = true)
    public List<PageEntity> findAllPage();
}
