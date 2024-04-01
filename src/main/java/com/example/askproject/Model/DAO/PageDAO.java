package com.example.askproject.Model.DAO;

import java.util.*;
import com.example.askproject.Model.Entity.PageEntity;

public interface PageDAO {
    public void insertPage(PageEntity pageEntity)throws Exception;
    public void deletePage(String pageId)throws Exception;
    public void updatePage(PageEntity pageEntity)throws Exception;
    public List<PageEntity> findAllPage()throws Exception;
    public PageEntity findByPageId(String pageId)throws Exception;
    public List<PageEntity> findByPageIdContaining(String keyword)throws Exception;
    public void resetPageTodayCount()throws Exception;
}
