package com.example.askproject.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.askproject.Model.DAO.PageDAO;
import com.example.askproject.Model.Entity.PageEntity;
import com.example.askproject.Service.PageService;

@Service
public class PageServiceImpl implements PageService{

    @Autowired
    private PageDAO pageDAO;
    @Override
    public void deletePage(String pageId) {
        // TODO Auto-generated method stub
        pageDAO.deletePage(pageId);
        
    }

    @Override
    public List<PageEntity> findAllPage() {
        // TODO Auto-generated method stub
        return pageDAO.findAllPage();
    }

    @Override
    public void insertPage(PageEntity pageEntity) {
        // TODO Auto-generated method stub
        pageDAO.insertPage(pageEntity);
    }

    @Override
    public void updatePage(PageEntity pageEntity) {
        // TODO Auto-generated method stub
        pageDAO.updatePage(pageEntity);
    }
    
}
