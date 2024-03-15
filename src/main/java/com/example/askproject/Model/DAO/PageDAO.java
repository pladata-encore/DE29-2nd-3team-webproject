package com.example.askproject.Model.DAO;

import java.util.*;
import com.example.askproject.Model.Entity.PageEntity;

public interface PageDAO {
    public void insertPage(PageEntity pageEntity);
    public void deletePage(String pageId);
    public void updatePage(PageEntity pageEntity);
    public List<PageEntity> findAllPage();
    public PageEntity findByPageId(String pageId);
    public List<PageEntity> findByPageIdContaining(String keyword);
    public void resetPageTodayCount();
}
