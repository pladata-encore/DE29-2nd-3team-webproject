package com.example.askproject.Model.DAO.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.askproject.Model.DAO.PageDAO;
import com.example.askproject.Model.Entity.PageEntity;
import com.example.askproject.Model.Repository.PageRepository;

@Service
public class PageDAOImpl implements PageDAO{
    @Autowired
    private PageRepository pageRepository;

    @Override
    public List<PageEntity> findAllPage() throws Exception{
        // TODO Auto-generated method stub
        return pageRepository.findAllPage();
    }

    @Override
    @Transactional
    public void deletePage(String pageId) throws Exception{
        // TODO Auto-generated method stub
        pageRepository.deleteByPageId(pageId);
    }

    @Override
    public void insertPage(PageEntity pageEntity) throws Exception{
        // TODO Auto-generated method stub
        pageRepository.save(pageEntity);
    }

    @Override
    public void updatePage(PageEntity pageEntity) throws Exception{
        // TODO Auto-generated method stub
        pageRepository.save(pageEntity);
        
    }

    @Override
    public PageEntity findByPageId(String pageId) throws Exception{
        // TODO Auto-generated method stub
        return pageRepository.findByPageId(pageId);
    }

    @Override
    public List<PageEntity> findByPageIdContaining(String keyword) throws Exception{
        // TODO Auto-generated method stub
        return pageRepository.findByPageIdContaining(keyword);
    }

    @Override
    public void resetPageTodayCount() throws Exception{
        // TODO Auto-generated method stub
        pageRepository.resetPageTodayCount();
    }

}
