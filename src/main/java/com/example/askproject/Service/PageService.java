package com.example.askproject.Service;

import java.util.List;

import com.example.askproject.Model.Entity.PageEntity;

public interface PageService {
    public void insertPage(PageEntity pageEntity);
    public void deletePage(String pageId);
    public void updatePage(PageEntity pageEntity);
    public List<PageEntity> findAllPage();
}
