package com.example.askproject.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.askproject.Model.DAO.PageDAO;
import com.example.askproject.Model.DTO.PageDTO;
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
    public List<PageDTO> findAllPage() {
        // TODO Auto-generated method stub
        List<PageEntity> entities = pageDAO.findAllPage();
        List<PageDTO> dtos = new ArrayList<>();
        for (PageEntity pageEntity : entities) {
            PageDTO pageDTO = new PageDTO();
            pageDTO.setPageComment(pageEntity.getPageComment());
            pageDTO.setPageId(pageEntity.getPageId());
            pageDTO.setPageTitle(pageEntity.getPageTitle());
            dtos.add(pageDTO);
        }
        return dtos;
    }

    @Override
    public void insertPage(PageDTO pageDTO) {
        // TODO Auto-generated method stub
        PageEntity pageEntity = new PageEntity();
        pageEntity.setPageComment(pageDTO.getPageComment());
        pageEntity.setPageId(pageDTO.getPageId());
        pageEntity.setPageTitle(pageDTO.getPageTitle());
        pageDAO.insertPage(pageEntity);
    }

    @Override
    public void updatePage(PageDTO pageDTO) {
        // TODO Auto-generated method stub
        PageEntity pageEntity = new PageEntity();
        pageEntity.setPageComment(pageDTO.getPageComment());
        pageEntity.setPageId(pageDTO.getPageId());
        pageEntity.setPageTitle(pageDTO.getPageTitle());
        pageDAO.insertPage(pageEntity);
    }
    

    public PageDTO getPageById(Long pageId) {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setPageTitle("페이지 제목");
        pageDTO.setPageComment("페이지 코멘트");
        return pageDTO;
    }
}
