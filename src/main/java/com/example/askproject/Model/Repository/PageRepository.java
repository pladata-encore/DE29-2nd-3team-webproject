package com.example.askproject.Model.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.askproject.Model.Entity.PageEntity;

public interface PageRepository extends JpaRepository<PageEntity, String>{
    @Query(value = "Select * from page", nativeQuery = true)
    public List<PageEntity> findAllPage();
    public PageEntity findByPageId(String pageId);
    public List<PageEntity> findByPageIdContaining(String keyword);

    @Transactional
    @Modifying
    @Query(value = "update page set page_today_count = 0", nativeQuery = true)
    public void resetPageTodayCount();
    public void deleteByPageId(String pageId);
}
